package com.example.keki;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.example.keki.ui.BaseDeDatos;
import com.example.keki.ui.home.Evento;
import com.example.keki.ui.profile.Usuario;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class Formulario extends AppCompatActivity {

    Uri imageUri;
    EditText etDate;
    Button cargar;
    ImageView imageView;

    private static final int PICK_IMAGE = 100;

    public final Calendar c = Calendar.getInstance();

    private static final String CERO = "0";
    private static final String BARRA = "/";

    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    private String[] ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etDate = (EditText) findViewById(R.id.editText4);
        cargar = (Button) findViewById(R.id.button5);
        imageView = (ImageView) findViewById(R.id.imageView);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerFecha();
            }
        });

        EditText id = (EditText) findViewById(R.id.editText2);
        id.setText("@");

        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
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
            imageView.setImageURI(imageUri);
        }
    }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
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

    private void consultas(){
        ids = new String[5];
    }

    public void siguiente(View view){

        consultas();

        String id = String.valueOf(((EditText) findViewById(R.id.editText2)).getText());
        String nombre = String.valueOf(((EditText) findViewById(R.id.editText3)).getText());
        String fecha = String.valueOf(((EditText) findViewById(R.id.editText4)).getText());
        String descripcion = String.valueOf(((EditText) findViewById(R.id.editText9)).getText());

        boolean idDisponible = (Arrays.asList(ids).contains(id))? false: true;
        boolean idLongitud = (id.length()>5 && id.length()<15)? true: false;
        boolean idValido = true;

        for(int i=0; i<id.length(); i++){
            if(id.charAt(i)==' ' || id.charAt(i)=='ñ')
                idValido = false;
        }

        boolean idDef = idDisponible && idLongitud && idValido;

        TextView err1 = (TextView) findViewById(R.id.textView12);

        if(!idDisponible)
            err1.setText("El usuario ingresado no está disponible");
        else if(!idLongitud)
            err1.setText("El usuario ingresado tener más de 5 dígitos y menos de 15 dígitos");
        else if(!idValido)
            err1.setText("El usuario ingresado no puede contener espacio vacío ni el caracter 'ñ'");
        else
            err1.setText("");

        err1.setGravity(Gravity.CENTER);

        boolean nombreValido = (nombre.length()<15 && nombre.length()>2)? true: false;

        TextView err2 = (TextView) findViewById(R.id.textView13);

        if(!nombreValido)
            err2.setText("El nombre ingresado debe tener entre 3 y 15 dígitos");
        else
            err2.setText("");

        err2.setGravity(Gravity.CENTER);

        TextView err3 = (TextView) findViewById(R.id.textView14);

        boolean fechaValida = false;

        if(fecha.length() > 0){
            if (!fechaValida(fecha))
                err3.setText("Debe ser mayor de 18 años de edad");
            else {
                fechaValida = true;
                err3.setText("");
            }
        }
        else{
            err3.setText("Seleccione una fecha");
        }

        err3.setGravity(Gravity.CENTER);

        if(idDef && nombreValido && fechaValida){
            SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();

            String telefono = getIntent().getStringExtra("telefono");

            editor.putString("telefono", telefono);
            editor.commit();

            Intent i = new Intent(this, Index.class);
            BaseDeDatos.usuarios.add(new Usuario(nombre, id, descripcion, new LinkedList<Evento>(), R.drawable.pro_1));
            BaseDeDatos.usuarios.get(BaseDeDatos.usuarios.size()-1).setTelefono(telefono);
            BaseDeDatos.buscarUsuario(telefono);
            startActivity(i);
        }
    }

    private boolean fechaValida(String fecha){
        Date fec = new Date(Integer.parseInt(fecha.substring(6)) - 1900, Integer.parseInt(fecha.substring(3,5)) - 1, Integer.parseInt(fecha.substring(0,2)));

        Date acfec = new Date();

        if(acfec.getYear() - fec.getYear() > 18) {
            return true;
        }
        else if(acfec.getYear() - fec.getYear() == 18) {
            if (acfec.getMonth() < fec.getMonth()) {
                return false;
            }
            else if (acfec.getMonth() > fec.getMonth()) {
                return true;
            }
            else if (acfec.getDay() >= fec.getDay()) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}
