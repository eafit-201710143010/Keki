package com.example.keki.ui.chat;

import com.example.keki.ui.profile.Usuario;

import java.util.LinkedList;
import java.util.List;

public class Chat {

    Usuario u1;
    Usuario u2;
    List<Mensaje> mensajeList1;
    List<Mensaje> mensajeList2;

    public Chat(Usuario u1, Usuario u2){
        this.u1 = u1;
        this.u2 = u2;
        mensajeList1 = new LinkedList<Mensaje>();
        mensajeList2 = new LinkedList<Mensaje>();
    }

    public Usuario getU1(){ return u1; }
    public Usuario getU2(){ return u2; }
    public List<Mensaje> getMensajeList1(){ return mensajeList1; }
    public List<Mensaje> getMensajeList2(){ return mensajeList2; }
    
    public boolean newMesage(Mensaje mensaje, Usuario origen){
        if(origen.equals(u1)){
            mensajeList1.add(mensaje);
            return true;
        }else if(origen.equals(u2)){
            mensajeList2.add(mensaje);
            return true;
        }else{
            return false;
        }
    }
}


