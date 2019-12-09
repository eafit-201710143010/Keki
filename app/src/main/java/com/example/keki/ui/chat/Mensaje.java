package com.example.keki.ui.chat;

import java.util.Date;

public class Mensaje {

    String contenido;
    Date tiempo;

    public Mensaje(String contenido, Date tiempo){
        this.contenido = contenido;
        this.tiempo = tiempo;
    }

    public String getContenido(){ return contenido; }
    public Date getTiempo(){ return tiempo; }

}
