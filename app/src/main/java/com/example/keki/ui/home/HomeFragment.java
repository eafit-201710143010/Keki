package com.example.keki.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.keki.R;
import com.example.keki.VistaEventos;
import com.example.keki.ui.BaseDeDatos;

public class HomeFragment extends Fragment {

    private ListView lv1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        lv1 = root.findViewById(R.id.listView);
        AdaptadorEventos adap = new AdaptadorEventos(getActivity(), BaseDeDatos.getEventos());

        lv1.setAdapter(adap);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), VistaEventos.class);
                i.putExtra("id", (int) id);
                startActivity(i);
            }
        });

        return root;
    }
}