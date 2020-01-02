package com.example.keki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keki.ui.BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Registro extends AppCompatActivity {

    EditText nume, contra;
    Button comprobar, iniciar, editar;
    TextView tvContra, err2;
    String num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nume = findViewById(R.id.editText);
        contra = findViewById(R.id.editTextContra);
        comprobar = findViewById(R.id.botonComprobar);
        iniciar = findViewById(R.id.botonIniciar);
        editar = findViewById(R.id.botonEditar);
        tvContra = findViewById(R.id.textViewContra);
        err2 = findViewById(R.id.textViewError2);

        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();

        editor.putString("telefono", "");
        editor.commit();
    }

    public void comprobar(View v){
        num = String.valueOf(nume.getText());

        boolean longitud = (num.length()==10)? true: false;
        boolean caracteres = true;
        boolean inicio = true;

        if(longitud){
            inicio = (num.startsWith("3"))? true: false;
            if(inicio){
                for(int i=0; i<num.length(); i++){
                    if(!Character.isDigit(num.charAt(i))){
                        caracteres = false;
                    }
                }

            }
        }

        TextView err = findViewById(R.id.textView11);

        if(longitud && caracteres && inicio && !BaseDeDatos.buscarUsuario(num)) {
            Intent i = new Intent(this, Formulario.class);
            i.putExtra("telefono", num);
            startActivity(i);
        }else if(BaseDeDatos.buscarUsuario(num) && longitud && caracteres && inicio){
            contra.setText("");
            contra.setVisibility(View.VISIBLE);
            iniciar.setVisibility(View.VISIBLE);
            editar.setVisibility(View.VISIBLE);
            tvContra.setVisibility(View.VISIBLE);
            err2.setText("");
            err2.setVisibility(View.VISIBLE);
            comprobar.setVisibility(View.GONE);
            nume.setEnabled(false);
        }

        if(!longitud)
            err.setText("Su número telefónico debe contener 10 dígitos");
        else if(!caracteres)
            err.setText("Su número telefónico no debe contener símbolos diferentes a números");
        else if(!inicio)
            err.setText("Su número telefónico debe iniciar con el dígito 3");
        else
            err.setText("");

        err.setGravity(Gravity.CENTER);
    }

    public void editar(View view){
        contra.setVisibility(View.GONE);
        iniciar.setVisibility(View.GONE);
        editar.setVisibility(View.GONE);
        tvContra.setVisibility(View.GONE);
        err2.setVisibility(View.GONE);
        comprobar.setVisibility(View.VISIBLE);
        nume.setEnabled(true);
    }

    public void iniciar(View view){
        if(BaseDeDatos.comprobarContra(String.valueOf(contra.getText()), String.valueOf(nume.getText()))){

            SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();

            editor.putString("telefono", num);
            editor.commit();

            Intent i = new Intent(this, Index.class);
            startActivity(i);
        }else{
            err2.setText("La contraseña no coincide");
        }
    }
}
