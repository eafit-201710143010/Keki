package com.example.keki.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.keki.Filtros;
import com.example.keki.R;
import com.example.keki.VistaEventos;
import com.example.keki.ui.BaseDeDatos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class HomeFragment extends Fragment {

    private ListView lv1;
    private FloatingActionButton floatingActionButton;
    private Button sinFil;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        lv1 = root.findViewById(R.id.listView);
        AdaptadorEventos adap = new AdaptadorEventos(getActivity(), BaseDeDatos.getEventos());

        lv1.setAdapter(adap);
        sinFil = root.findViewById(R.id.button13);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), VistaEventos.class);
                i.putExtra("id", (int) id);
                startActivity(i);
            }
        });

        if(BaseDeDatos.costoMax != -1 || BaseDeDatos.distanciaMax != -1 || BaseDeDatos.musica.size()>0 || BaseDeDatos.categorias.size()>0)
            sinFil.setVisibility(View.VISIBLE);
        else
            sinFil.setVisibility(View.GONE);

        floatingActionButton = root.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Filtros.class);
                startActivity(i);
            }
        });

        sinFil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sinFil.setVisibility(View.GONE);
                BaseDeDatos.categorias = new LinkedList<>();
                BaseDeDatos.musica = new LinkedList<>();
                BaseDeDatos.costoMax = -1;
                BaseDeDatos.distanciaMax = -1;

                AdaptadorEventos adap = new AdaptadorEventos(getActivity(), BaseDeDatos.getEventos());

                lv1.setAdapter(adap);
            }
        });
        return root;
    }
}