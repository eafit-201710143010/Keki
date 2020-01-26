package com.example.keki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.keki.ui.BaseDeDatos;

public class EditarPerfil extends AppCompatActivity {

    EditText nombre, descripcion;
    TextView err;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        nombre = findViewById(R.id.editText10);
        descripcion = findViewById(R.id.editText11);

        nombre.setText(BaseDeDatos.usuario.getNombre());
        descripcion.setText(BaseDeDatos.usuario.getDescripcion());
    }

    public void guardar(View view){
        String nom = String.valueOf(nombre.getText());

        boolean nombreValido = (nombre.length()<15 && nombre.length()>2)? true: false;

        err = findViewById(R.id.textView22);

        if(!nombreValido)
            err.setText("El nombre ingresado debe tener entre 3 y 15 dígitos");
        else
            err.setText("");

        err.setGravity(Gravity.CENTER);

        if(nombreValido) {
            BaseDeDatos.usuario.setNombre(nom);
            BaseDeDatos.usuario.setDescripcion(String.valueOf(descripcion.getText()));

            BaseDeDatos.actualizarInfo(nom, String.valueOf(descripcion.getText()));

            Intent i = new Intent(this, Index.class);
            startActivity(i);
        }
    }
}
