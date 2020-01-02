package com.example.keki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
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

public class VistaEventos extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marker;
    private TextView titulo, creador, fecha, descripcion, costo;
    private Button asistencia, editar;
    private Evento evento;
    private View aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_eventos);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        int id = getIntent().getIntExtra("id",-1);

        evento = BaseDeDatos.buscar(id);

        titulo = findViewById(R.id.titulo);
        creador = findViewById(R.id.creador);
        fecha = findViewById(R.id.fecha);
        descripcion = findViewById(R.id.descripcion);
        costo = findViewById(R.id.costo);
        asistencia = findViewById(R.id.button6);
        editar = findViewById(R.id.button10);
        aux = findViewById(R.id.aux);

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

        if(evento.getIdCreador().equals(BaseDeDatos.usuario.getId())){
            editar.setVisibility(View.VISIBLE);
        }

        if(BaseDeDatos.vaAAsistir(evento)){
            asistencia.setText("Cancelar asistencia");
        }else{
            asistencia.setText("Asistir");
        }

        if(evento != null){
            titulo.setText(evento.getNombre());
            creador.setText("Creador:" + evento.getIdCreador());
            fecha.setText("Fecha: " + evento.getFecha().toString().substring(0,11) + " - " + evento.getHora().toString().substring(0,5));
            descripcion.setText(evento.getDescripcion());
            if(evento.getCosto() == 0){
                costo.setText("Gratis");
            }else{
                costo.setText("Costo: " + dinero(Integer.toString(evento.getCosto())));
            }
        }else{
            titulo.setText("Evento no encontrado");
            asistencia.setVisibility(View.INVISIBLE);
        }
    }

    String dinero(String s){
        String resultado = "";
        for(int i=s.length()-1; i>=0; i--){
            if(i%3 == 0){
                resultado = "." + resultado;
            }
            resultado = s.charAt(i) + resultado;
        }
        return "$" + resultado;
    }

    public void asistir(View view){
        if(asistencia.getText()=="Asistir"){
            BaseDeDatos.usuario.añadirEvento(evento);
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            asistencia.setText("Cancelar asistencia");
        }else{
            BaseDeDatos.usuario.borrarEvento(evento);
            Toast.makeText(this, "Cancelación exitosa", Toast.LENGTH_SHORT).show();
            asistencia.setText("Asistir");
        }
    }

    public void editar(View view){
        Intent i = new Intent(this, EditarEvento.class);
        i.putExtra("id", evento.getId());
        startActivity(i);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng normal = new LatLng(evento.getLat(), evento.getLng());
        marker = mMap.addMarker(new MarkerOptions().position(normal).draggable(false).title(evento.getNombre()));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(normal,15));
    }
}
