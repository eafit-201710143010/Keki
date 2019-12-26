package com.example.keki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keki.ui.BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void comprobar(View v){

        EditText nume = findViewById(R.id.editText);
        String num = String.valueOf(nume.getText());

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
            agregarCategoria();

            Intent i = new Intent(this, Formulario.class);
            i.putExtra("telefono", num);
            startActivity(i);
        }else if(BaseDeDatos.buscarUsuario(num)){
            agregarCategoria();

            SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();

            editor.putString("telefono", num);
            editor.commit();

            Intent i = new Intent(this, Index.class);
            startActivity(i);
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

    public Connection conexion(){
        Connection conexion = null;

        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:3306;databaseName=Keki;user=keki;password=wgnJLZ55;");
        }catch(Exception e){
            Toast.makeText(this,"UNO:" + e.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
        return conexion;
    }

    public void agregarCategoria(){
        // try{
            Connection pst = conexion();
            // .prepareStatement("INSERT INTO Categoria (categoria) values(\"fiesta\")");
            // pst.executeUpdate();
            /*
        }catch(SQLException e){
            //Toast.makeText(this,"DOS:" + e.getMessage().toString(),Toast.LENGTH_LONG).show();
        }*/
    }
}
