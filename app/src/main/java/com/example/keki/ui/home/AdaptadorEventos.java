package com.example.keki.ui.home;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.keki.R;

import java.util.List;

public class AdaptadorEventos extends BaseAdapter {

    private Context context;
    private List<Evento> eventoList;

    public AdaptadorEventos(Context context, List<Evento> eventoList){
        this.eventoList = eventoList;
        this.context = context;
    }

    @Override
    public int getCount(){
        return eventoList.size();
    }

    @Override
    public Object getItem(int position){
        return eventoList.get(position);
    }

    @Override
    public long getItemId(int position){
        return eventoList.get(position).getId();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Evento item = (Evento) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.list_item_evento, null);

        ImageView imv = convertView.findViewById(R.id.img);
        TextView tv1 = convertView.findViewById(R.id.titulo);
        TextView tv2 = convertView.findViewById(R.id.fecha);

        imv.setImageResource(item.getImg());
        tv1.setText(item.getNombre());
        tv1.setGravity(Gravity.CENTER_VERTICAL);
        tv1.setGravity(Gravity.CENTER);
        tv1.setTextSize(27);

        tv2.setText(item.getFecha().toString().substring(0,11));
        tv2.setGravity(Gravity.BOTTOM);
        tv2.setGravity(Gravity.CENTER);

        return convertView;
    }

}
