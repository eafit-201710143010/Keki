package com.example.keki.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.keki.R;

import java.util.Arrays;
import java.util.List;
import java.sql.Time;
import java.util.Date;

public class HomeFragment extends Fragment {

    private ListView lv1;
    private Evento[] event = {new Evento("Fiesta\n",new Date(2019, 12, 1), new Time(00,00,00),R.drawable.img_1),
                            new Evento("Llegada de papito Dios\n", new Date(2019,12,24), new Time(24, 59, 59),R.drawable.img_2),
                            new Evento("Año nuevo\n", new Date(2019, 12, 31), new Time(24,59,50),R.drawable.img_3),
                            new Evento("Año nuevo\n", new Date(2019, 12, 31), new Time(24,59,50),R.drawable.img_3),
                            new Evento("Año nuevo\n", new Date(2019, 12, 31), new Time(24,59,50),R.drawable.img_3),
                            new Evento("Año nuevo\n", new Date(2019, 12, 31), new Time(24,59,50),R.drawable.img_3),
                            new Evento("Año nuevo\n", new Date(2019, 12, 31), new Time(24,59,50),R.drawable.img_3)};
    private List<Evento> eventos = Arrays.asList(event);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        lv1 = (ListView) root.findViewById(R.id.listView);
        Adaptador adap = new Adaptador(getActivity(), eventos);

        lv1.setAdapter(adap);

        return root;
    }
}