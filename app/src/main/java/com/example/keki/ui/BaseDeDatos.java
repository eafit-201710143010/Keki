package com.example.keki.ui;

import com.example.keki.R;
import com.example.keki.ui.chat.Chat;
import com.example.keki.ui.home.Evento;
import com.example.keki.ui.profile.Usuario;

import java.sql.Time;
import java.util.Arrays;
import java.util.Date;

public class BaseDeDatos {
    public static Evento[] event = {new Evento("Fiesta\n",new Date(2019, 12, 1), new Time(00,00,00), R.drawable.img_1),
            new Evento("Llegada de papito Dios\n", new Date(2019,12,24), new Time(24, 59, 59),R.drawable.img_2),
            new Evento("Año nuevo\n", new Date(2019, 12, 31), new Time(24,59,50),R.drawable.img_3),
            new Evento("Año nuevo\n", new Date(2019, 12, 31), new Time(24,59,50),R.drawable.img_3),
            new Evento("Año nuevo\n", new Date(2019, 12, 31), new Time(24,59,50),R.drawable.img_3),
            new Evento("Año nuevo\n", new Date(2019, 12, 31), new Time(24,59,50),R.drawable.img_3),
            new Evento("Año nuevo\n", new Date(2019, 12, 31), new Time(24,59,50),R.drawable.img_3)};

    public static Evento[] evento = {new Evento("Fiesta",new Date(2019, 12, 1), new Time(00,00,00),R.drawable.img_1),
            new Evento("Llegada de papito Dios", new Date(2019,12,24), new Time(24, 59, 59),R.drawable.img_2)};

    public static Usuario[] usuarios = {new Usuario("Andrés Sánchez", "@andres_sanchezzzz", "A really sex machine", Arrays.asList(evento), R.drawable.pro_1),
                                        new Usuario("Isaac Castaño", "@isaaccr000", "A really big fool", Arrays.asList(evento), R.drawable.pro_2),
                                        new Usuario("Diana Sánchez", "@disanchezc", "La sister", Arrays.asList(evento), R.drawable.pro_1),
                                        new Usuario("Sara Castaño", "@scastanor", "Keki's lawyer", Arrays.asList(evento), R.drawable.pro_2)};

    public static void iniciar(){
        Chat chat = new Chat(usuarios[0], usuarios[1]);
        usuarios[0].newChat(chat);
    }
}
