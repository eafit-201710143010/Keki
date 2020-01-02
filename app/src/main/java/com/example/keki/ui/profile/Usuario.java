package com.example.keki.ui.profile;

import com.example.keki.ui.BaseDeDatos;
import com.example.keki.ui.home.Evento;

import java.util.LinkedList;
import java.util.List;

public class Usuario {

    String nombre, id, descripcion, telefono;
    List<Evento> eventos;
    int imagen;

    public Usuario(String nombre, String id, String descripcion, int imagen){
        this.nombre = nombre;
        this.id = id;
        this.descripcion = descripcion;
        this.eventos = new LinkedList<>();
        this.imagen = imagen;
        telefono = "";
    }

    public String getNombre(){ return nombre; }
    public String getId(){ return id; }
    public String getDescripcion(){ return descripcion; }
    public List<Evento> getEventos(){ return eventos; }
    public int getImagen(){ return imagen; }
    public String getTelefono(){ return telefono; }

    public void setNombre(String nombre){ this.nombre = nombre; }
    public void setDescripcion(String descripcion){ this.descripcion = descripcion; }
    public void setTelefono(String telefono){ this.telefono = telefono; }

    public boolean equals(Usuario usuario){
        if(this.id == usuario.id){
            return true;
        }
        return false;
    }

    public void añadirEvento(Evento e){
        BaseDeDatos.asistirAEventos(e.getId());
    }

    public void borrarEvento(Evento e){
        BaseDeDatos.cancelarAsistencia(e.getId());
    }
}
