package com.example.keki.ui.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.keki.R;

import java.util.List;

public class AdaptadorChats extends BaseAdapter {
//La Clase adaptadorChats sirve para adoptar chats a las lista
    private Context context;
    private List<Chat> chatList;

    public AdaptadorChats(Context context, List<Chat> chatList){
        //Metodo constructor del adaptador que recibe como parametros el contexto actual y la lista de chats
        this.context = context;
        this.chatList = chatList;
    }

    @Override
    //Metodo para obtener la cantidad de chats
    public int getCount() {
        return chatList.size();
    }

    @Override
    //obtener un chat dentro de la lista een la posición asignada
    public Object getItem(int position) {
        return chatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    //generador de las vistas de cada chat
    public View getView(int position, View convertView, ViewGroup parent) {
        Chat item = (Chat) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.list_item_chat, null);

        ImageView imUsuario = (ImageView) convertView.findViewById(R.id.imagenUsuario);
        TextView tvNombreUsuario = (TextView) convertView.findViewById(R.id.usuarioChat);

        imUsuario.setImageResource(item.getU2().getImagen());
        tvNombreUsuario.setText(item.getU2().getNombre());

        return convertView;
    }
}
