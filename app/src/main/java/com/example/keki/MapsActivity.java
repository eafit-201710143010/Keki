package com.example.keki;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keki.ui.BaseDeDatos;
import com.example.keki.ui.home.Evento;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;
    private Marker marker;
    private Button button;
    private TextView tv, err1, err2;
    private EditText etDescricion, etCosto;
    private RadioGroup radioGroup;
    Evento evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        button = findViewById(R.id.button7);
        tv = findViewById(R.id.textView17);
        etDescricion = findViewById(R.id.editText8);
        etCosto = findViewById(R.id.editText12);
        err1 = findViewById(R.id.textView23);
        err2 = findViewById(R.id.textView24);
        radioGroup = findViewById(R.id.opcionesCosto);

        evento = BaseDeDatos.buscar(getIntent().getIntExtra("id", -1));

        etDescricion.setText(evento.getDescripcion());
        radioGroup.check((evento.getCosto()==0)? R.id.gratis: R.id.pago);
        if(evento.getCosto()!=0)
            etCosto.setText(Integer.toString(evento.getCosto()));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.gratis){
                    etCosto.setVisibility(View.INVISIBLE);
                    err2.setText("");
                }else{
                    etCosto.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng normal = new LatLng(evento.getLat(), evento.getLng());
        marker = mMap.addMarker(new MarkerOptions().position(normal).draggable(true).title(evento.getNombre()));

        tv.setText("Ubicación: " + marker.getPosition().latitude + ", " + marker.getPosition().longitude);
        tv.setGravity(Gravity.CENTER);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(normal,15));
        googleMap.setOnMarkerDragListener(this);
    }

    public void selecionarUbicacion(View view){
        String descripcion = String.valueOf(etDescricion.getText());
        String costo = String.valueOf(etDescricion.getText());

        boolean aux = false;

        if(descripcion.length()==0){
            err1.setText("Ingrese una descripción");
        }else{
            err1.setText("");
        }

        if(costo.length()==0 && radioGroup.getCheckedRadioButtonId() ==R.id.pago){
            err2.setText("Ingrese un costo");
        }else{
            err2.setText("");
            aux = true;
        }

        if(aux && descripcion.length()>0) {
            evento.setLat(marker.getPosition().latitude);
            evento.setLng(marker.getPosition().longitude);
            evento.setDescripcion(String.valueOf(etDescricion.getText()));
            evento.setIdCreador(BaseDeDatos.usuario.getId());
            if(radioGroup.getCheckedRadioButtonId() == R.id.gratis){
                evento.setCosto(0);
            }else{
                evento.setCosto(Integer.valueOf(String.valueOf(etCosto.getText())));
            }

            Intent i = new Intent(this, Index.class);
            startActivity(i);
        }
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {
        tv.setText("Ubicación: " + this.marker.getPosition().latitude + ", " + this.marker.getPosition().longitude);
        tv.setGravity(Gravity.CENTER);
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        tv.setText("Ubicación: " + this.marker.getPosition().latitude + ", " + this.marker.getPosition().longitude);
        tv.setGravity(Gravity.CENTER);
    }
}
