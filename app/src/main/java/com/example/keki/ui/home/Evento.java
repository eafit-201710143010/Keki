package com.example.keki.ui.home;

import androidx.annotation.NonNull;

import java.sql.Time;
import java.util.Date;

public class Evento {

    int img;
    String nombre;
    Date fecha;
    Time hora;

    public Evento(String nombre, Date fecha, Time hora, int img){
        this.fecha = fecha;
        this.hora = hora;
        this.nombre = nombre;
        this.img = img;
    }

    @NonNull
    @Override
    public String toString() {
        return nombre;
    }

    int getImg(){ return img; }
    String getNombre(){ return nombre; }
    Date getFecha(){ return fecha; }
    Time getHora(){ return hora; }
}
