package com.example.keki;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.keki.ui.BaseDeDatos;
import com.example.keki.ui.home.Evento;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class EditarEvento extends AppCompatActivity {

    Uri imageUri;
    EditText etTitulo, etFecha, etHora;
    TextView error1, error3, error4;
    Button button, crear;
    Evento evento;
    ImageView foto;

    private static final int PICK_IMAGE = 100;

    public final Calendar c = Calendar.getInstance();

    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final String Dos_Puntos = ":";

    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_evento);

        etTitulo = findViewById(R.id.editText5);
        etFecha = findViewById(R.id.editText6);
        etHora = findViewById(R.id.editText7);
        button = findViewById(R.id.button3);
        crear = findViewById(R.id.button4);
        error1 = findViewById(R.id.error1);
        error3 = findViewById(R.id.error3);
        error4 = findViewById(R.id.error4);
        foto = findViewById(R.id.prueba);

        evento = BaseDeDatos.buscar(getIntent().getIntExtra("id",-1));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerFecha();
            }
        });

        etHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerHora();
            }
        });

        final Context aux = this;

        etTitulo.setText(evento.getNombre());

        String fecha = "";
        if(evento.getFecha().getDate()<10){
            fecha += "0";
        }
        fecha += evento.getFecha().getDate() + "/";
        if(evento.getFecha().getMonth() + 1<10){
            fecha += "0";
        }
        fecha += (evento.getFecha().getMonth() + 1) + "/" + evento.getFecha().getYear();
        etFecha.setText(fecha);

        if(Integer.parseInt(evento.getHora().toString().substring(0,2)) < 13)
            etHora.setText(evento.getHora().toString().substring(0,5) + " a.m.");
        else
            etHora.setText(evento.getHora().toString().substring(0,5) + " p.m.");
    }

    public void siguiente(View view){
        String fecha = String.valueOf(etFecha.getText());
        String hora = String.valueOf(etHora.getText());
        String titulo = String.valueOf(etTitulo.getText());

        Date date = null;
        Time time = null;

        if(titulo.length()==0 || titulo.length()>15){
            error1.setText("Ingrese un título menor a 15 caracteres y mayor a 0 caracteres");
        }else{
            error1.setText("");
        }

        if(fecha.length()>0){
            date = new Date(Integer.parseInt(fecha.substring(6)), Integer.parseInt(fecha.substring(3, 5)), Integer.parseInt(fecha.substring(0, 2)));
            error3.setText("");
        }else{
            error3.setText("Seleccione una fecha");
        }

        if(hora.length()>0){
            boolean aux = hora.contains("p");
            time = new Time(Integer.valueOf(hora.substring(0,2)) + ((aux)? 0 :12), Integer.valueOf(hora.substring(3,5)), 0);
            error4.setText("");
        }else{
            error4.setText("Seleccione una hora");
        }

        error1.setGravity(Gravity.CENTER);
        error3.setGravity(Gravity.CENTER);
        error4.setGravity(Gravity.CENTER);

        if(fecha.length()>0 && hora.length()>0 && titulo.length()>0) {
            BaseDeDatos.buscar(evento.getId()).setNombre(titulo);
            BaseDeDatos.buscar(evento.getId()).setFecha(date);
            BaseDeDatos.buscar(evento.getId()).setHora(time);
            Intent i = new Intent(this, MapsActivity.class);
            i.putExtra("id", BaseDeDatos.eventos.get(BaseDeDatos.eventos.size()-1).getId());
            startActivity(i);
        }
    }

    private void obtenerHora(){
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);

                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }

                etHora.setText(horaFormateada + Dos_Puntos + minutoFormateado + " " + AM_PM);
            }
        }, hora, minuto, false);
        recogerHora.show();
    }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final int mesActual = month + 1;
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);

                etFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
        },anio, mes, dia);

        recogerFecha.show();
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            foto.setImageURI(imageUri);
        }
    }
}
