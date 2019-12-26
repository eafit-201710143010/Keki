package com.example.keki.ui.profile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;

import com.example.keki.EditarPerfil;
import com.example.keki.R;
import com.example.keki.VistaEventos;
import com.example.keki.ui.BaseDeDatos;
import com.example.keki.ui.home.AdaptadorEventos;

public class ProfileFragment extends Fragment {

    ImageView ivFoto;
    TextView tvNombre;
    TextView tvDescripcion;
    ListView lvEventos;
    Button editarPerfil, porAsistir, creados;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        ivFoto = root.findViewById(R.id.imageView2);
        tvNombre = root.findViewById(R.id.nombre);
        tvDescripcion = root.findViewById(R.id.proDescripcion);
        lvEventos = root.findViewById(R.id.eventosUsuario);
        editarPerfil = root.findViewById(R.id.button8);
        porAsistir = root.findViewById(R.id.button11);
        creados = root.findViewById(R.id.button12);

        AdaptadorEventos adap = new AdaptadorEventos(getActivity(), BaseDeDatos.usuario.getEventos());

        Drawable originalDrawable = getResources().getDrawable(BaseDeDatos.usuario.getImagen());
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        roundedDrawable.setCornerRadius(originalBitmap.getHeight());

        ivFoto.setImageDrawable(roundedDrawable);
        tvNombre.setText(BaseDeDatos.usuario.getNombre());
        tvDescripcion.setText(BaseDeDatos.usuario.getDescripcion());
        lvEventos.setAdapter(adap);

        editarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EditarPerfil.class);
                startActivity(i);
            }
        });

        porAsistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creados.setBackgroundColor(getResources().getColor(R.color.gris));
                creados.setTextColor(getResources().getColor(R.color.negro));
                porAsistir.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                porAsistir.setTextColor(getResources().getColor(R.color.blanco));
                AdaptadorEventos adap = new AdaptadorEventos(getActivity(), BaseDeDatos.usuario.getEventos());
                lvEventos.setAdapter(adap);
            }
        });

        creados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creados.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                creados.setTextColor(getResources().getColor(R.color.blanco));
                porAsistir.setBackgroundColor(getResources().getColor(R.color.gris));
                porAsistir.setTextColor(getResources().getColor(R.color.negro));
                AdaptadorEventos adap = new AdaptadorEventos(getActivity(), BaseDeDatos.getEventosPorIdCreador(BaseDeDatos.usuario.getId()));
                lvEventos.setAdapter(adap);
            }
        });

        lvEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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