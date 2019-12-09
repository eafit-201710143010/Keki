package com.example.keki.ui.profile;

import com.example.keki.ui.chat.Chat;
import com.example.keki.ui.home.Evento;

import java.util.LinkedList;
import java.util.List;

public class Usuario {

    String nombre;
    String id;
    String descripcion;
    List<Evento> eventos;
    List<Chat> chats;
    int imagen;

    public Usuario(String nombre, String id, String descripcion, List<Evento> eventos, int imagen){
        this.nombre = nombre;
        this.id = id;
        this.descripcion = descripcion;
        this.eventos = eventos;
        this.chats = new LinkedList<Chat>();
        this.imagen = imagen;
    }

    public String getNombre(){ return nombre; }
    public String getId(){ return id; }
    public String getDescripcion(){ return descripcion; }
    public List<Evento> getEventos(){ return eventos; }
    public List<Chat> getChats(){ return chats; }
    public int getImagen(){ return imagen; }

    public boolean newChat(Chat chat){
        if(chat.getU1().equals(this)){
            chats.add(chat);
            return true;
        }else if(chat.getU2().equals(this)){
            chats.add(chat);
            return true;
        }else{
            return false;
        }
    }

    public boolean equals(Usuario usuario){
        if(this.id == usuario.id){
            return true;
        }
        return false;
    }

}
