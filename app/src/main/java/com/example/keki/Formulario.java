package com.example.keki;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.keki.ui.dialog.DatePickerFragment;

public class Formulario extends AppCompatActivity {

    EditText etDate;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        tv = (TextView) findViewById(R.id.textView4);
        etDate = (EditText) findViewById(R.id.editText4);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.editText4:
                    showDatePickerDialog();
                    break;
                }
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                String selectedDate = "hola";
                selectedDate = day + " / " + (month+1) + " / " + year;
                tv.setText(selectedDate);
                etDate.setText(selectedDate);
            }
        });

        newFragment.show(this.getSupportFragmentManager(), "datePicker");
    }

    public void siguiente(View view){
        Intent i = new Intent(this, Index.class);
        startActivity(i);
    }
}
