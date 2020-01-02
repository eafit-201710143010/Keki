package com.example.keki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.keki.ui.BaseDeDatos;

public class MainActivity extends AppCompatActivity {

    long DURACION = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        final String telefono = preferencias.getString("telefono", "");

        final boolean aux = (telefono.equals(""))? false: true;

        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(MainActivity.this, Inicio.class);
                intent.putExtra("usuario", aux);
                intent.putExtra("telefono", telefono);
                startActivity(intent);
                finish();
            };
        }, DURACION);
    }
}
