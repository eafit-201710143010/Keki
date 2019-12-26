package com.example.keki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.keki.ui.BaseDeDatos;

public class Inicio extends AppCompatActivity {

    long DURACION = 750;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        boolean aux = getIntent().getBooleanExtra("usuario", false);
        boolean aux2 = false;
        String telefono = getIntent().getStringExtra("telefono");

        if(aux)
             aux2 = BaseDeDatos.buscarUsuario(telefono);

        if(!aux2) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(Inicio.this, Registro.class);
                    startActivity(intent);
                    finish();
                }

                ;
            }, DURACION);
        }else{
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(Inicio.this, Index.class);
                    startActivity(intent);
                    finish();
                }

                ;
            }, DURACION);
        }
    }
}
