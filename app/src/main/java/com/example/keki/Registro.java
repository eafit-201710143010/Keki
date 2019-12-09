package com.example.keki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void comprobar(View v){

        EditText nume = (EditText) findViewById(R.id.editText);
        String num = String.valueOf(nume.getText());

        boolean confir = true;

        if(num.charAt(0)=='3' && num.length()==10 ){
            for(int i=1; i!=10;i++){
                if(!Character.isDigit(num.charAt(i))){
                    confir=false;
                }
            }

        }
        else{
            confir = false;
        }

        if(confir) {
            Intent i = new Intent(this, Formulario.class);
            startActivity(i);
        }else{
            TextView err = (TextView) findViewById(R.id.textView11);
            err.setVisibility(TextView.VISIBLE);
        }
    }
}
