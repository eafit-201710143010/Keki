package com.example.keki;

import android.location.Address;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdaptadorUsuarios extends BaseAdapter {

    List<String> elementos;
    Context c;

    public AdaptadorUsuarios(List<String> l, Context co){
        c = co;
        elementos = l;
    }

    @Override
    public int getCount() {
        return elementos.size();
    }

    @Override
    public Object getItem(int position) {
        return elementos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String item = (String) getItem(position);

        convertView = LayoutInflater.from(c).inflate(R.layout.list_item_resultado, null);

        TextView tv = convertView.findViewById(R.id.titulo);

        tv.setText(item);

        return convertView;
    }
}

