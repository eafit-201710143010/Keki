package com.example.keki.ui.create;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.keki.MapsActivity;
import com.example.keki.R;
import com.example.keki.ui.BaseDeDatos;
import com.example.keki.ui.home.Evento;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class CreateFragment extends Fragment {

    Uri imageUri;
    ImageView foto;
    Button button, crear;
    EditText etTitulo, etDate, etTime;
    TextView error1, error3, error4;

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_create, container, false);

        super.onCreate(savedInstanceState);

        foto = root.findViewById(R.id.prueba);
        button = root.findViewById(R.id.button3);
        etTitulo = root.findViewById(R.id.editText5);
        etDate = root.findViewById(R.id.editText6);
        etTime = root.findViewById(R.id.editText7);
        crear = root.findViewById(R.id.button4);
        error1 = root.findViewById(R.id.error1);
        error3 = root.findViewById(R.id.error3);
        error4 = root.findViewById(R.id.error4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerFecha();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerHora();
            }
        });

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha = String.valueOf(etDate.getText());
                String hora = String.valueOf(etTime.getText());
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
                    Evento e = new Evento(String.valueOf(etTitulo.getText()), date, time, R.drawable.eventos);
                    BaseDeDatos.añadirEvento1(e);
                    Intent i = new Intent(getActivity(), MapsActivity.class);
                    i.putExtra("id", -1);
                    startActivity(i);
                }
            }
        });

        return root;
    }

    private void obtenerHora(){
        TimePickerDialog recogerHora = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
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

                etTime.setText(horaFormateada + Dos_Puntos + minutoFormateado + " " + AM_PM);
            }
        }, hora, minuto, false);
        recogerHora.show();
    }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final int mesActual = month + 1;
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);

                etDate.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
        },anio, mes, dia);

        recogerFecha.show();
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            foto.setImageURI(imageUri);
        }
    }
}
