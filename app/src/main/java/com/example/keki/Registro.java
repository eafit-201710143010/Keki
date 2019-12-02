package com.example.keki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void comprobar(View v){
        //TO DO código para verificar usuarios
        Intent i = new Intent(this, Formulario.class);
        startActivity(i);
    }
}
