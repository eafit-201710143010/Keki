package com.example.keki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Inicio extends AppCompatActivity {

    long DURACION = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(Inicio.this, Registro.class);
                startActivity(intent);
                finish();
            };
        }, DURACION);
    }
}
