package com.example.keki.ui;

import com.example.keki.R;
import com.example.keki.ui.home.Evento;
import com.example.keki.ui.profile.Usuario;

import java.util.LinkedList;
import java.util.List;

public class BaseDeDatos {
    public static Usuario usuario;

    public static List<Usuario> usuarios = new LinkedList<>();
    public static List<Evento> eventos = new LinkedList<>();

    public static List<Evento> getEventosPorIdCreador(String id){
        LinkedList<Evento> resultado = new LinkedList<>();
        for(Evento evento: eventos){
            if(evento.getIdCreador().equals(id)){
                resultado.add(evento);
            }
        }
        return resultado;
    }

    public static Evento buscar(int id){
        for(Evento evento: BaseDeDatos.eventos){
            if(evento.getId() == id){
                return evento;
            }
        }
        return null;
    }

    public static void iniciar(){
        usuarios.add(new Usuario("Andrés Sánchez", "@asanchezc", "Estudiante de ingeninería Universidad EAFIT", new LinkedList<Evento>(), R.drawable.pro_1));
        usuarios.add(new Usuario("Isaac Castaño", "@isaaccr000", "Estudiante de ingeninería Universidad EIA", new LinkedList<Evento>(), R.drawable.pro_1));
        usuarios.add(new Usuario("Jesucristo", "@yisuscrist", "Hijo de diosito y salvador de todos ustedes", new LinkedList<Evento>(), R.drawable.pro_1));
        usuarios.get(0).setTelefono("3017012250");
        usuarios.get(1).setTelefono("3015299907");
        usuarios.get(2).setTelefono("3002062250");
    }

    public static boolean buscarUsuario(String telefono){
        for(Usuario usuari: usuarios){
            if(usuari.getTelefono().equals(telefono)){
                usuario = usuari;
            }
        }
        return usuario != null;
    }
}
