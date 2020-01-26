package com.example.keki;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
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

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;
    private Marker marker;
    private Button button, buscar;
    private TextView tv, err1, err2;
    private EditText etDescricion, etCosto, etUbicacion, etUsuario;
    private RadioGroup radioGroup, rgInvitados;
    private Evento evento;
    private Geocoder geo;
    private final int maxResultados = 1;
    private List<Address> adress;
    private ListView lv, lv1;
    private View aux;
    private ScrollView sv;
    AdaptadorResultados adap;
    int id;
    LinkedList<String> usuarios;
    LinkedList<String> usuariosInvitados = new LinkedList<>();


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
        radioGroup = findViewById(R.id.radioGroup);
        etUbicacion = findViewById(R.id.busqueda);
        lv = findViewById(R.id.listView);
        sv = findViewById(R.id.sv);
        geo = new Geocoder(this);
        aux = findViewById(R.id.aux);
        rgInvitados = findViewById(R.id.radioGroup1);
        etUsuario = findViewById(R.id.busquedaUsuario);
        buscar = findViewById(R.id.button3);
        lv1 = findViewById(R.id.listView1);

        aux.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                    default:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                }

                return false;
            }
        });

        id = getIntent().getIntExtra("id", -1);
        evento = BaseDeDatos.buscar(id);

        if(evento==null)
            evento = BaseDeDatos.aux;

        lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sv.requestDisallowInterceptTouchEvent(true);
                int action = event.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_UP:
                        sv.requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });

        final Context c = this;

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                etUsuario.setText("");
                usuariosInvitados.add(usuarios.get((int) id));
                AdaptadorUsuarios adap = new AdaptadorUsuarios(new LinkedList<String>(), c);

                lv1.setAdapter(adap);
                Toast.makeText(c, "Usuario invitado", Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                marker.remove();

                LatLng normal = new LatLng(((Address) adap.getItem(position)).getLatitude(), ((Address) adap.getItem(position)).getLongitude());
                marker = mMap.addMarker(new MarkerOptions().position(normal).draggable(true).title(evento.getNombre()));

                tv.setText("Ubicación: " + marker.getPosition().latitude + ", " + marker.getPosition().longitude);
                tv.setGravity(Gravity.CENTER);

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(normal,15));
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.gratis){
                    etCosto.setVisibility(View.GONE);
                    err2.setVisibility(View.GONE);
                    err2.setText("");
                }else{
                    etCosto.setVisibility(View.VISIBLE);
                    err2.setVisibility(View.VISIBLE);
                }
            }
        });

        rgInvitados.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton1){
                    etUsuario.setVisibility(View.GONE);
                    buscar.setVisibility(View.GONE);
                }else{
                    etUsuario.setVisibility(View.VISIBLE);
                    buscar.setVisibility(View.VISIBLE);
                }
            }
        });

        etDescricion.setText(evento.getDescripcion());
        radioGroup.check((evento.getCosto()==0)? R.id.gratis: R.id.pago);
        if(evento.getCosto()!=0)
            etCosto.setText(Integer.toString(evento.getCosto()));
    }

    public void buscar(View view){
        try {
            adress = geo.getFromLocationName(String.valueOf(etUbicacion.getText()), maxResultados);
            lv.setVisibility(View.VISIBLE);

            adap = new AdaptadorResultados(adress, this);
            lv.setAdapter(adap);
        } catch (IOException e) {
            Toast.makeText(this,"no se encontró ubicación", Toast.LENGTH_SHORT).show();
            lv.setVisibility(View.GONE);
        }
    }

    public void buscarUsuario(View view){
        String usuario = String.valueOf(etUsuario.getText());

        usuarios = BaseDeDatos.buscarUsuarios(usuario);

        AdaptadorUsuarios adap = new AdaptadorUsuarios(usuarios, this);

        lv1.setAdapter(adap);

        if(usuarios.size()>0){
            lv1.setVisibility(View.VISIBLE);
        }else{
            lv1.setVisibility(View.GONE);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {

            }
        });

        LatLng normal = new LatLng(evento.getLat(), evento.getLng());
        marker = mMap.addMarker(new MarkerOptions().position(normal).draggable(true).title(evento.getNombre()));

        tv.setText("Ubicación: " + marker.getPosition().latitude + ", " + marker.getPosition().longitude);
        tv.setGravity(Gravity.CENTER);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(normal,10));
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

            Intent i = new Intent(this, Categorias.class);
            if(id == -1) {
                if(rgInvitados.getCheckedRadioButtonId() == R.id.radioButton1) {
                    BaseDeDatos.añadirEvento2(evento, false, new LinkedList<String>());
                }else{
                    BaseDeDatos.añadirEvento2(evento, true, usuariosInvitados);
                }
            }
            else {
                BaseDeDatos.editarEvento2(evento);
                i.putExtra("id", id);
            }
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
