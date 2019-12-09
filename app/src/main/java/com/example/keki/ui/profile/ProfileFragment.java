package com.example.keki.ui.profile;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;

import com.example.keki.R;
import com.example.keki.ui.BaseDeDatos;
import com.example.keki.ui.home.AdaptadorEventos;

public class ProfileFragment extends Fragment {

    ImageView ivFoto;
    TextView tvNombre;
    TextView tvDescripcion;
    ListView lvEventos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        ivFoto = (ImageView) root.findViewById(R.id.imageView2);
        tvNombre = (TextView) root.findViewById(R.id.nombre);
        tvDescripcion = (TextView) root.findViewById(R.id.proDescripcion);
        lvEventos = (ListView) root.findViewById(R.id.eventosUsuario);

        AdaptadorEventos adap = new AdaptadorEventos(getActivity(), BaseDeDatos.usuarios[0].getEventos());

        Drawable originalDrawable = getResources().getDrawable(BaseDeDatos.usuarios[0].getImagen());
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        roundedDrawable.setCornerRadius(originalBitmap.getHeight());

        ivFoto.setImageDrawable(roundedDrawable);
        tvNombre.setText(BaseDeDatos.usuarios[0].getNombre());
        tvDescripcion.setText(BaseDeDatos.usuarios[0].getDescripcion());
        lvEventos.setAdapter(adap);

        return root;
    }
}