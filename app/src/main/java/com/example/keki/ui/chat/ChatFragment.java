package com.example.keki.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.keki.R;
import com.example.keki.ui.BaseDeDatos;

public class ChatFragment extends Fragment {

    ListView lvChats;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_chat, container, false);

        AdaptadorChats adap = new AdaptadorChats(getActivity(), BaseDeDatos.usuarios[0].getChats());

        lvChats = root.findViewById(R.id.chatList);
        lvChats.setAdapter(adap);

        return root;
    }
}