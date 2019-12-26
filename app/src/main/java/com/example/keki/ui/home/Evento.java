package com.example.keki.ui.home;

import androidx.annotation.NonNull;

import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Evento {

    int img, costo, id;
    String nombre, descripcion, idCreador;
    Date fecha;
    Time hora;
    double lat, lng;
    List<String> asistentes;
    static int aux = 0;

    public Evento(String nombre, Date fecha, Time hora, int img){
        this.fecha = fecha;
        this.hora = hora;
        this.nombre = nombre;
        this.img = img;
        this.id = aux;
        costo = 0;
        descripcion = "";
        idCreador = "";
        lat = 6.246445607314125;
        lng = -75.57117663323879;
        asistentes = new LinkedList<>();
        aux++;
    }

    @NonNull
    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        return ((Evento) o).getId() == id;
    }

    public int getImg(){ return img; }
    public String getNombre(){ return nombre; }
    public Date getFecha(){ return fecha; }
    public Time getHora(){ return hora; }
    public int getId(){ return id; }
    public String getIdCreador(){ return idCreador; }
    public String getDescripcion(){ return descripcion; }
    public int getCosto(){ return costo; }
    public double getLat(){ return lat; }
    public double getLng(){ return lng; }

    public void setImg(int img) { this.img = img; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public void setHora(Time hora) { this.hora = hora; }
    public void setLat(double lat){ this.lat = lat; }
    public void setLng(double lng){ this.lng = lng; }
    public void setDescripcion(String descripcion){ this.descripcion = descripcion; }
    public void setCosto(int costo){ this.costo = costo; }
    public void setIdCreador(String idCreador){ this.idCreador = idCreador; }
    public void setId(int id){ this.id = id; }
}