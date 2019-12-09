package com.example.keki.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.keki.R;
import com.example.keki.ui.BaseDeDatos;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private ListView lv1;
    private List<Evento> eventos = BaseDeDatos.eventos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        lv1 = (ListView) root.findViewById(R.id.listView);
        AdaptadorEventos adap = new AdaptadorEventos(getActivity(), eventos);

        lv1.setAdapter(adap);



        return root;
    }
}