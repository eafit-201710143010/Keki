package com.example.keki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.keki.ui.BaseDeDatos;
import com.google.android.material.chip.ChipGroup;

import java.util.LinkedList;
import java.util.List;

public class Filtros extends AppCompatActivity {

    Button buttonCosto, buttonUbicacion, buttonMusica, buttonCategoria;
    TextView tvCosto;
    SeekBar sbRango, sbDistancia;
    RadioGroup rgCosto, rgDist;
    boolean costo = true;
    CheckBox cbBlues, cbJazz, cbRhythm, cbRock, cbRockEsp, cbMetal, cbDisco, cbTechno, cbPop, cbSka, cbReggae, cbHip,
            cbSalsa, cbReggaeton, cbVallenato, cbBachata, cbCrossover, cbRap, cbMusicaVivo, cbBaile,
            cbPintura, cbGastronomia, cbDeporte, cbAcademia, cbClases, cbFiesta, cbRecorrido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);

        buttonCategoria = findViewById(R.id.button4);
        buttonCosto = findViewById(R.id.button1);
        buttonMusica = findViewById(R.id.button3);
        buttonUbicacion = findViewById(R.id.button2);

        tvCosto = findViewById(R.id.textView1);
        sbRango = findViewById(R.id.seekBar);
        sbDistancia = findViewById(R.id.seekBarDistancia);
        rgCosto = findViewById(R.id.radioGroup);

        rgDist = findViewById(R.id.radioGroup1);

        cbBlues = findViewById(R.id.checkBoxblues);
        cbJazz = findViewById(R.id.checkBoxjazz);
        cbRhythm = findViewById(R.id.checkBoxrhythm);
        cbRock = findViewById(R.id.checkBoxrock);
        cbRockEsp = findViewById(R.id.checkBoxrockEsp);
        cbMetal = findViewById(R.id.checkBoxmetal);
        cbDisco = findViewById(R.id.checkBoxdisco);
        cbTechno = findViewById(R.id.checkBoxtechno);
        cbPop = findViewById(R.id.checkBoxpop);
        cbSka = findViewById(R.id.checkBoxska);
        cbReggae = findViewById(R.id.checkBoxreggae);
        cbHip = findViewById(R.id.checkBoxhip);
        cbSalsa = findViewById(R.id.checkBoxsalsa);
        cbReggaeton = findViewById(R.id.checkBoxreggaeton);
        cbVallenato = findViewById(R.id.checkBoxvallenato);
        cbBachata = findViewById(R.id.checkBoxbachata);
        cbCrossover = findViewById(R.id.checkBoxcrossover);
        cbRap = findViewById(R.id.checkBoxrap);
        cbMusicaVivo = findViewById(R.id.checkBoxmusicaVivo);

        cbBaile = findViewById(R.id.checkBoxbaile);
        cbPintura = findViewById(R.id.checkBoxpintura);
        cbGastronomia = findViewById(R.id.checkBoxgastronomia);
        cbDeporte = findViewById(R.id.checkBoxdeporte);
        cbAcademia = findViewById(R.id.checkBoxacademia);
        cbClases = findViewById(R.id.checkBoxclases);
        cbFiesta = findViewById(R.id.checkBoxfiesta);
        cbRecorrido = findViewById(R.id.checkBoxrecorrido);

        buttonUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonUbicacion.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                buttonUbicacion.setTextColor(getResources().getColor(R.color.blanco));

                buttonCategoria.setBackgroundColor(getResources().getColor(R.color.blanco));
                buttonCategoria.setTextColor(getResources().getColor(R.color.negro));
                buttonCosto.setBackgroundColor(getResources().getColor(R.color.blanco));
                buttonCosto.setTextColor(getResources().getColor(R.color.negro));
                buttonMusica.setBackgroundColor(getResources().getColor(R.color.blanco));
                buttonMusica.setTextColor(getResources().getColor(R.color.negro));

                costo = false;

                tvCosto.setVisibility(View.VISIBLE);
                sbRango.setVisibility(View.GONE);
                sbDistancia.setVisibility(View.VISIBLE);
                rgDist.setVisibility(View.VISIBLE);

                tvCosto.setText("Se mostrarán eventos en " + sbRango.getProgress() + " metros cerca de ti");
                tvCosto.setGravity(Gravity.CENTER);

                rgCosto.setVisibility(View.GONE);

                cbBlues.setVisibility(View.GONE);
                cbJazz.setVisibility(View.GONE);
                cbRhythm.setVisibility(View.GONE);
                cbRock.setVisibility(View.GONE);
                cbRockEsp.setVisibility(View.GONE);
                cbMetal.setVisibility(View.GONE);
                cbDisco.setVisibility(View.GONE);
                cbTechno.setVisibility(View.GONE);
                cbPop.setVisibility(View.GONE);
                cbSka.setVisibility(View.GONE);
                cbReggae.setVisibility(View.GONE);
                cbHip.setVisibility(View.GONE);
                cbSalsa.setVisibility(View.GONE);
                cbReggaeton.setVisibility(View.GONE);
                cbVallenato.setVisibility(View.GONE);
                cbBachata.setVisibility(View.GONE);
                cbCrossover.setVisibility(View.GONE);
                cbRap.setVisibility(View.GONE);
                cbMusicaVivo.setVisibility(View.GONE);

                cbBaile.setVisibility(View.GONE);
                cbPintura.setVisibility(View.GONE);
                cbGastronomia.setVisibility(View.GONE);
                cbDeporte.setVisibility(View.GONE);
                cbAcademia.setVisibility(View.GONE);
                cbClases.setVisibility(View.GONE);
                cbFiesta.setVisibility(View.GONE);
                cbRecorrido.setVisibility(View.GONE);
            }
        });

        buttonMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonMusica.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                buttonMusica.setTextColor(getResources().getColor(R.color.blanco));

                buttonCategoria.setBackgroundColor(getResources().getColor(R.color.blanco));
                buttonCategoria.setTextColor(getResources().getColor(R.color.negro));
                buttonCosto.setBackgroundColor(getResources().getColor(R.color.blanco));
                buttonCosto.setTextColor(getResources().getColor(R.color.negro));
                buttonUbicacion.setBackgroundColor(getResources().getColor(R.color.blanco));
                buttonUbicacion.setTextColor(getResources().getColor(R.color.negro));

                tvCosto.setVisibility(View.VISIBLE);
                tvCosto.setText("Seleccione las categorías musicales que desea buscar");

                sbRango.setVisibility(View.GONE);
                rgCosto.setVisibility(View.GONE);
                sbDistancia.setVisibility(View.GONE);

                rgDist.setVisibility(View.GONE);

                cbBlues.setVisibility(View.VISIBLE);
                cbJazz.setVisibility(View.VISIBLE);
                cbRhythm.setVisibility(View.VISIBLE);
                cbRock.setVisibility(View.VISIBLE);
                cbRockEsp.setVisibility(View.VISIBLE);
                cbMetal.setVisibility(View.VISIBLE);
                cbDisco.setVisibility(View.VISIBLE);
                cbTechno.setVisibility(View.VISIBLE);
                cbPop.setVisibility(View.VISIBLE);
                cbSka.setVisibility(View.VISIBLE);
                cbReggae.setVisibility(View.VISIBLE);
                cbHip.setVisibility(View.VISIBLE);
                cbSalsa.setVisibility(View.VISIBLE);
                cbReggaeton.setVisibility(View.VISIBLE);
                cbVallenato.setVisibility(View.VISIBLE);
                cbBachata.setVisibility(View.VISIBLE);
                cbCrossover.setVisibility(View.VISIBLE);
                cbRap.setVisibility(View.VISIBLE);
                cbMusicaVivo.setVisibility(View.VISIBLE);

                cbBaile.setVisibility(View.GONE);
                cbPintura.setVisibility(View.GONE);
                cbGastronomia.setVisibility(View.GONE);
                cbDeporte.setVisibility(View.GONE);
                cbAcademia.setVisibility(View.GONE);
                cbClases.setVisibility(View.GONE);
                cbFiesta.setVisibility(View.GONE);
                cbRecorrido.setVisibility(View.GONE);
            }
        });

        buttonCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCategoria.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                buttonCategoria.setTextColor(getResources().getColor(R.color.blanco));

                buttonUbicacion.setBackgroundColor(getResources().getColor(R.color.blanco));
                buttonUbicacion.setTextColor(getResources().getColor(R.color.negro));
                buttonCosto.setBackgroundColor(getResources().getColor(R.color.blanco));
                buttonCosto.setTextColor(getResources().getColor(R.color.negro));
                buttonMusica.setBackgroundColor(getResources().getColor(R.color.blanco));
                buttonMusica.setTextColor(getResources().getColor(R.color.negro));

                tvCosto.setText("Seleccione las categorías que desea buscar");

                tvCosto.setVisibility(View.VISIBLE);

                sbRango.setVisibility(View.GONE);
                sbDistancia.setVisibility(View.GONE);

                rgDist.setVisibility(View.GONE);

                cbBlues.setVisibility(View.GONE);
                cbJazz.setVisibility(View.GONE);
                cbRhythm.setVisibility(View.GONE);
                cbRock.setVisibility(View.GONE);
                cbRockEsp.setVisibility(View.GONE);
                cbMetal.setVisibility(View.GONE);
                cbDisco.setVisibility(View.GONE);
                cbTechno.setVisibility(View.GONE);
                cbPop.setVisibility(View.GONE);
                cbSka.setVisibility(View.GONE);
                cbReggae.setVisibility(View.GONE);
                cbHip.setVisibility(View.GONE);
                cbSalsa.setVisibility(View.GONE);
                cbReggaeton.setVisibility(View.GONE);
                cbVallenato.setVisibility(View.GONE);
                cbBachata.setVisibility(View.GONE);
                cbCrossover.setVisibility(View.GONE);
                cbRap.setVisibility(View.GONE);
                cbMusicaVivo.setVisibility(View.GONE);

                cbBaile.setVisibility(View.VISIBLE);
                cbPintura.setVisibility(View.VISIBLE);
                cbGastronomia.setVisibility(View.VISIBLE);
                cbDeporte.setVisibility(View.VISIBLE);
                cbAcademia.setVisibility(View.VISIBLE);
                cbClases.setVisibility(View.VISIBLE);
                cbFiesta.setVisibility(View.VISIBLE);
                cbRecorrido.setVisibility(View.VISIBLE);
            }
        });

        buttonCosto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCosto.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                buttonCosto.setTextColor(getResources().getColor(R.color.blanco));

                buttonCategoria.setBackgroundColor(getResources().getColor(R.color.blanco));
                buttonCategoria.setTextColor(getResources().getColor(R.color.negro));
                buttonUbicacion.setBackgroundColor(getResources().getColor(R.color.blanco));
                buttonUbicacion.setTextColor(getResources().getColor(R.color.negro));
                buttonMusica.setBackgroundColor(getResources().getColor(R.color.blanco));
                buttonMusica.setTextColor(getResources().getColor(R.color.negro));

                costo = true;

                switch (rgCosto.getCheckedRadioButtonId()){
                    case R.id.radioButton1:
                        tvCosto.setText("Se mostrarán eventos sin costo");
                        sbRango.setVisibility(View.GONE);
                        break;
                    case R.id.radioButton2:
                        sbRango.setVisibility(View.VISIBLE);
                        tvCosto.setText("Se mostrarán eventos desde $0 hasta $" + sbRango.getProgress());
                        tvCosto.setGravity(Gravity.CENTER);
                        break;
                    case R.id.radioButton3:
                        tvCosto.setText("No se aplicarán filtros de precio");
                        sbRango.setVisibility(View.GONE);
                        break;
                }

                tvCosto.setVisibility(View.VISIBLE);
                rgCosto.setVisibility(View.VISIBLE);

                rgDist.setVisibility(View.GONE);
                sbDistancia.setVisibility(View.GONE);

                cbBlues.setVisibility(View.GONE);
                cbJazz.setVisibility(View.GONE);
                cbRhythm.setVisibility(View.GONE);
                cbRock.setVisibility(View.GONE);
                cbRockEsp.setVisibility(View.GONE);
                cbMetal.setVisibility(View.GONE);
                cbDisco.setVisibility(View.GONE);
                cbTechno.setVisibility(View.GONE);
                cbPop.setVisibility(View.GONE);
                cbSka.setVisibility(View.GONE);
                cbReggae.setVisibility(View.GONE);
                cbHip.setVisibility(View.GONE);
                cbSalsa.setVisibility(View.GONE);
                cbReggaeton.setVisibility(View.GONE);
                cbVallenato.setVisibility(View.GONE);
                cbBachata.setVisibility(View.GONE);
                cbCrossover.setVisibility(View.GONE);
                cbRap.setVisibility(View.GONE);
                cbMusicaVivo.setVisibility(View.GONE);

                cbBaile.setVisibility(View.GONE);
                cbPintura.setVisibility(View.GONE);
                cbGastronomia.setVisibility(View.GONE);
                cbDeporte.setVisibility(View.GONE);
                cbAcademia.setVisibility(View.GONE);
                cbClases.setVisibility(View.GONE);
                cbFiesta.setVisibility(View.GONE);
                cbRecorrido.setVisibility(View.GONE);
            }
        });

        rgDist.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton4:
                        tvCosto.setText("Se mostrarán eventos sin importar su ubicación");
                        sbRango.setVisibility(View.GONE);
                        break;
                    case R.id.radioButton5:
                        sbRango.setVisibility(View.VISIBLE);
                        tvCosto.setText("Se mostrarán eventos en " + sbRango.getProgress() + " metros cerca de ti");
                        tvCosto.setGravity(Gravity.CENTER);
                        break;
                }
            }
        });

        rgCosto.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton1:
                        tvCosto.setText("Se mostrarán eventos sin costo");
                        sbRango.setVisibility(View.GONE);
                        break;
                    case R.id.radioButton2:
                        sbRango.setVisibility(View.VISIBLE);
                        tvCosto.setText("Se mostrarán eventos desde $0 hasta $" + sbRango.getProgress());
                        tvCosto.setGravity(Gravity.CENTER);
                        break;
                    case R.id.radioButton3:
                        tvCosto.setText("No se aplicarán filtros de precio");
                        sbRango.setVisibility(View.GONE);
                        break;
                }
            }
        });

        sbDistancia.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvCosto.setText("Se mostrarán eventos en " + sbRango.getProgress() + " metros cerca de ti");

                tvCosto.setGravity(Gravity.CENTER);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbRango.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                tvCosto.setText("Se mostrarán eventos desde $0 hasta $" + sbRango.getProgress());

                tvCosto.setGravity(Gravity.CENTER);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void aplicar(View view){
        int costoMax;
        int distanciaMax;
        List<Integer> musica = new LinkedList<>();
        List<Integer> categorias = new LinkedList<>();

        if(rgCosto.getCheckedRadioButtonId() == R.id.radioButton1)
            costoMax = 0;
        else if(rgCosto.getCheckedRadioButtonId() == R.id.radioButton2)
            costoMax = sbRango.getProgress();
        else
            costoMax = -1;

        if(rgDist.getCheckedRadioButtonId() == R.id.radioButton4)
            distanciaMax = -1;
        else
            distanciaMax = sbRango.getProgress();

        if(cbBlues.isChecked())
            musica.add(1);
        if(cbJazz.isChecked())
            musica.add(2);
        if(cbRhythm.isChecked())
            musica.add(3);
        if(cbRock.isChecked())
            musica.add(4);
        if(cbRockEsp.isChecked())
            musica.add(5);
        if(cbMetal.isChecked())
            musica.add(6);
        if(cbDisco.isChecked())
            musica.add(7);
        if(cbTechno.isChecked())
            musica.add(8);
        if(cbPop.isChecked())
            musica.add(9);
        if(cbSka.isChecked())
            musica.add(10);
        if(cbReggae.isChecked())
            musica.add(11);
        if(cbHip.isChecked())
            musica.add(12);
        if(cbSalsa.isChecked())
            musica.add(13);
        if(cbReggaeton.isChecked())
            musica.add(14);
        if(cbVallenato.isChecked())
            musica.add(15);
        if(cbBachata.isChecked())
            musica.add(16);
        if(cbCrossover.isChecked())
            musica.add(17);
        if(cbRap.isChecked())
            musica.add(18);
        if(cbMusicaVivo.isChecked())
            musica.add(19);

        if(cbBaile.isChecked())
            categorias.add(20);
        if(cbPintura.isChecked())
            categorias.add(21);
        if(cbGastronomia.isChecked())
            categorias.add(22);
        if(cbDeporte.isChecked())
            categorias.add(23);
        if(cbAcademia.isChecked())
            categorias.add(24);
        if(cbClases.isChecked())
            categorias.add(25);
        if(cbFiesta.isChecked())
            categorias.add(26);
        if(cbRecorrido.isChecked())
            categorias.add(27);

        BaseDeDatos.costoMax = costoMax;
        BaseDeDatos.distanciaMax = distanciaMax;
        BaseDeDatos.musica = musica;
        BaseDeDatos.categorias = categorias;

        Intent i = new Intent(this, Index.class);
        startActivity(i);
    }
}

