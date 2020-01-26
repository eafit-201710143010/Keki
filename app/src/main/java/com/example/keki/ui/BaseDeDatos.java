package com.example.keki.ui;

import android.os.StrictMode;
import android.util.Log;

import com.example.keki.R;
import com.example.keki.ui.home.Evento;
import com.example.keki.ui.profile.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class BaseDeDatos {
    public static Usuario usuario = null;
    public static Evento aux = null;

    public static Connection conexion = conexion();

    public static int costoMax = -1;
    public static int distanciaMax = -1;
    public static List<Integer> musica = new LinkedList<>();
    public static List<Integer> categorias = new LinkedList<>();

    public static List<Evento> getEventosPorIdCreador(String id){
        LinkedList<Evento> resultado = new LinkedList<>();
        Statement comm;
        try {
            comm = conexion.createStatement();
            ResultSet rs = comm.executeQuery("SELECT * FROM [dbo].[Evento] WHERE id_creador = '" + id + "'");

            while(rs.next()){
                String f = rs.getString("fecha_Hora");

                Date d = new Date(Integer.parseInt(f.substring(0,4)), Integer.parseInt(f.substring(5,7)), Integer.parseInt(f.substring(8,10)));
                Time t = new Time(Integer.parseInt(f.substring(11,13)), Integer.parseInt(f.substring(14,16)), 0);

                Evento e = new Evento(rs.getString("titulo"), d, t, R.drawable.eventos);

                e.setId(rs.getInt("id_evento"));
                e.setLat(rs.getDouble("lat"));
                e.setLng(rs.getDouble("lng"));
                e.setCosto(rs.getInt("costo"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setIdCreador(id);

                resultado.add(e);
            }

        } catch (SQLException e) {  }
        return resultado;
    }

    public static List<Evento> getEventos(){
        LinkedList<Evento> resultado = new LinkedList<>();
        Statement comm;
        try {
            comm = conexion.createStatement();

            String consulta = "Select * FROM [dbo].[Evento]";

            if(musica.size() > 0 && categorias.size() > 0){
                consulta = "Select * FROM [dbo].[Evento] INNER JOIN [dbo].[Evento_Categoria] " +
                        "ON [dbo].[Evento].id_evento = [dbo].[Evento_Categoria].id_evento and (";
                for(int i=0; i<musica.size(); i++){
                    consulta += " [dbo].[Evento_Categoria].id_categoria = " + musica.get(i);
                    consulta += " or ";
                }

                for(int i=0; i<categorias.size(); i++){
                    consulta += " [dbo].[Evento_Categoria].id_categoria = " + categorias.get(i);
                    if(i != categorias.size()-1)
                        consulta += " or ";
                    else
                        consulta += " ) ";
                }
            }else if(musica.size() > 0){
                consulta = "Select * FROM [dbo].[Evento] INNER JOIN [dbo].[Evento_Categoria] " +
                        "ON [dbo].[Evento].id_evento = [dbo].[Evento_Categoria].id_evento and (";
                for(int i=0; i<musica.size(); i++){
                    consulta += " [dbo].[Evento_Categoria].id_categoria = " + musica.get(i);
                    if(i != musica.size()-1)
                        consulta += " or ";
                    else
                        consulta += " ) ";
                }
            }else if(categorias.size() > 0){
                consulta = "Select * FROM [dbo].[Evento] INNER JOIN [dbo].[Evento_Categoria] " +
                        "ON [dbo].[Evento].id_evento = [dbo].[Evento_Categoria].id_evento and (";

                for(int i=0; i<categorias.size(); i++){
                    consulta += " [dbo].[Evento_Categoria].id_categoria = " + categorias.get(i);
                    if(i != categorias.size()-1)
                        consulta += " or ";
                    else
                        consulta += " ) ";
                }
            }

            if(costoMax == 0)
                consulta += " WHERE costo = 0";
            else if(costoMax > 0)
                consulta += " WHERE costo < " + costoMax;

            if(distanciaMax != -1 && costoMax != -1)
                consulta += " and SQRT(POWER(6.246445607314125 - [dbo].[Evento].lat, 2) + " +
                        "POWER(-75.57117663323879 - [dbo].[Evento].lng, 2)) < " + distanciaMax;
            else if(distanciaMax != -1)
                consulta += " WHERE SQRT(POWER(6.246445607314125 - [dbo].[Evento].lat, 2) + " +
                        "POWER(-75.57117663323879 - [dbo].[Evento].lng, 2)) < " + distanciaMax;

            ResultSet rs = comm.executeQuery(consulta);

            while(rs.next()){
                String f = rs.getString("fecha_Hora");

                Date d = new Date(Integer.parseInt(f.substring(0,4)), Integer.parseInt(f.substring(5,7)), Integer.parseInt(f.substring(8,10)));
                Time t = new Time(Integer.parseInt(f.substring(11,13)), Integer.parseInt(f.substring(14,16)), 0);

                Evento e = new Evento(rs.getString("titulo"), d, t, R.drawable.eventos);
                e.setId(rs.getInt("id_evento"));
                e.setLat(rs.getDouble("lat"));
                e.setLng(rs.getDouble("lng"));
                e.setCosto(rs.getInt("costo"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setIdCreador(rs.getString("id_creador"));

                resultado.add(e);
            }

        } catch (SQLException e) {  }
        return resultado;
    }

    public static Evento buscar(int id){
        Evento ev = null;
        Statement comm;
        try {
            comm = conexion.createStatement();
            ResultSet rs = comm.executeQuery("SELECT * FROM [dbo].[Evento] WHERE id_evento = " + id);
            if(rs.next()) {
                String f = rs.getString("fecha_Hora");

                Date d = new Date(Integer.parseInt(f.substring(0, 4)), Integer.parseInt(f.substring(5, 7)), Integer.parseInt(f.substring(8, 10)));
                Time t = new Time(Integer.parseInt(f.substring(11, 13)), Integer.parseInt(f.substring(14, 16)), 0);

                ev = new Evento(rs.getString("titulo"), d, t, R.drawable.eventos);
                ev.setId(rs.getInt("id_evento"));
                ev.setLat(rs.getDouble("lat"));
                ev.setLng(rs.getDouble("lng"));
                ev.setCosto(rs.getInt("costo"));
                ev.setDescripcion(rs.getString("descripcion"));
                ev.setIdCreador(rs.getString("id_creador"));
            }
        } catch (SQLException e) {  }
        return ev;
    }

    public static boolean comprobarContra(String contra, String tel){
        Statement comm;
        try {
            comm = conexion.createStatement();
            ResultSet rs = comm.executeQuery("SELECT * FROM Usuario WHERE tel = '" + tel + "' and PWDCOMPARE('" + contra + "', contra) = 1");

            boolean aux = rs.next();

            iniciarSesion(new Usuario(rs.getString("nombre"),rs.getString("id_usuario"),rs.getString("descripcion"),
                                  R.drawable.pro_1));

            usuario.setTelefono(rs.getString("tel"));

            return aux;
        } catch (SQLException e) {
            return false;
        }
    }

    public static Connection conexion(){
        Connection conexion = null;

        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://servidorsit.database.windows.net/Keki;user=isaaccr000;password=Kekiisan1;");
        }catch(Exception e){

        }
        return conexion;
    }

    public static boolean buscarUsuario(String telefono){
        Statement comm;
        try {
            comm = conexion.createStatement();
            ResultSet rs = comm.executeQuery("SELECT * FROM Usuario WHERE tel = '" + telefono + "'");

            boolean aux = rs.next();

            return aux;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void añadirUsuario(Usuario u, String contra, int edad){
        PreparedStatement comm;
        try {
            comm = conexion.prepareStatement("INSERT INTO Usuario(id_usuario, nombre, edad, tel, descripcion, contra)" +
                    "VALUES ('" + u.getId() + "', '" + u.getNombre() + "', " + edad + ", '" + u.getTelefono() + "', '" + u.getDescripcion() +
                    "',PWDENCRYPT('" + contra + "'))");

            comm.executeUpdate();

            iniciarSesion(u);
        } catch (SQLException e) {
        }
    }

    public static void iniciarSesion(Usuario u){
        usuario = u;
    }

    public static void editarEvento1(String titulo, Date d, Time t, int id){
        PreparedStatement comm;
        try {
            comm = conexion.prepareStatement("UPDATE Evento" +
                    "SET titulo='" + titulo + "', fecha_Hora = '" + d.getYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate() + " " +
                    t.getHours() + ":" + t.getMinutes() + ":00'" +
                    "WHERE id_evento = " + id);

            comm.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public static void añadirEvento1(Evento e){
        aux = e;
    }

    public static void añadirEvento2(Evento e, boolean auxi, LinkedList<String> lista) {
        PreparedStatement comm;
        try {
            comm = conexion.prepareStatement("INSERT INTO Evento (descripcion, lat, lng, fecha_Hora, titulo, costo, id_creador)" +
                    "VALUES ('" + e.getDescripcion() + "', " + e.getLat() + ", " + e.getLng() + ", '" + e.getFecha().getYear() + "-" +
                    (e.getFecha().getMonth() + 1) + "-" + e.getFecha().getDate() + " " + e.getHora().getHours() + ":" +
                    e.getHora().getMinutes() + ":00', '" + e.getNombre() + "', " + e.getCosto() + ", '" + e.getIdCreador() + "')");

            comm.executeUpdate();
            aux = null;

            if(auxi){
                invitar(lista);
            }

        } catch (SQLException ex) {
        }
    }

    public static void invitar(LinkedList<String> lista) {
        PreparedStatement comm;
        try {
            for(String string: lista) {
                comm = conexion.prepareStatement("INSERT INTO UsuarioInvitado (id_usuario, id_evento) VALUES" +
                        "('" + string + "', " + getUltimoId() + ")");

                comm.executeUpdate();
            }
        } catch (SQLException ex) {
        }
    }

    public static void editarEvento2(Evento e) {
        PreparedStatement comm;
        try {
            comm = conexion.prepareStatement("UPDATE Evento SET descripcion = '" + e.getDescripcion() + "', costo = " + e.getCosto() +
            ", lat = " + e.getLat() + ", lng = " + e.getLng() + "WHERE id_evento = " + e.getId());

            comm.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public static void asistirAEventos(int id){
        PreparedStatement comm;
        try {
            comm = conexion.prepareStatement("INSERT INTO Usuario_Evento (id_usuario, id_evento) VALUES ('" + usuario.getId() +
                    "', " + id + ")");

            comm.executeUpdate();
            Log.i("insert", "SE HIZO");
        } catch (SQLException ex) {
        }
    }

    public static void cancelarAsistencia(int id){
        PreparedStatement comm;
        try {
            comm = conexion.prepareStatement("DELETE FROM Usuario_Evento WHERE id_usuario='" + usuario.getId() + "' and id_" +
                    "evento = " + id);

            comm.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public static boolean vaAAsistir(Evento e){
        Statement comm;
        try {
            comm = conexion.createStatement();
            ResultSet rs = comm.executeQuery("SELECT * FROM Usuario_Evento WHERE id_usuario = '" + usuario.getId() + "' and id_evento = "
            + e.getId());

            boolean aux = rs.next();

            return aux;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean idDisponible(String id){
        return true;
    }

    public static List<Evento> porAsistir(){
        List<Evento> resultado = new LinkedList<>();
        Statement comm;
        try {
            comm = conexion.createStatement();
            ResultSet rs = comm.executeQuery("SELECT * FROM [dbo].[Evento] INNER JOIN [dbo].[Usuario_Evento] " +
                    "ON [dbo].[Evento].id_evento = [dbo].[Usuario_Evento].id_evento and [dbo].[Usuario_Evento].id_usuario = '" +
                    usuario.getId() + "'");

            while(rs.next()){
                String f = rs.getString("fecha_Hora");

                Date d = new Date(Integer.parseInt(f.substring(0,4)), Integer.parseInt(f.substring(5,7)), Integer.parseInt(f.substring(8,10)));
                Time t = new Time(Integer.parseInt(f.substring(11,13)), Integer.parseInt(f.substring(14,16)), 0);

                Evento e = new Evento(rs.getString("titulo"), d, t, R.drawable.eventos);

                e.setId(rs.getInt("id_evento"));
                e.setLat(rs.getDouble("lat"));
                e.setLng(rs.getDouble("lng"));
                e.setCosto(rs.getInt("costo"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setIdCreador(rs.getString("id_creador"));

                resultado.add(e);
            }
        } catch (SQLException e) {
        }
        return resultado;
    }

    public static void iniciarSesion(String telefono) {
        Statement comm;
        try {
            comm = conexion.createStatement();
            ResultSet rs = comm.executeQuery("SELECT * FROM Usuario WHERE tel = '" + telefono + "'");

            rs.next();

            iniciarSesion(new Usuario(rs.getString("nombre"),rs.getString("id_usuario"),rs.getString("descripcion"),
                    R.drawable.pro_1));

            usuario.setTelefono(rs.getString("tel"));
        } catch (SQLException e) {
        }
    }

    public static int getUltimoId(){
        Statement comm;
        int i = 0;
        try {
            comm = conexion.createStatement();
            ResultSet rs = comm.executeQuery("SELECT * FROM Evento");

            while(rs.next()){
                i++;
            }
        } catch (SQLException e) {
        }
        return i;
    }

    public static void añadirCategorias(int id, List<Integer> categorias){
        for(Integer integer: categorias){
            PreparedStatement comm;
            try {
                comm = conexion.prepareStatement("INSERT INTO Evento_Categoria (id_evento, id_categoria) VALUES ("
                        + id + ", " + integer + ")");

                comm.executeUpdate();
            } catch (SQLException ex) {
            }
        }
    }

    public static LinkedList<String> buscarUsuarios(String usuario) {
        LinkedList<String> resultado = new LinkedList<>();
        Statement comm;
        try {
            comm = conexion.createStatement();
            ResultSet rs = comm.executeQuery("SELECT id_usuario FROM Usuario WHERE id_usuario LIKE '%" +
                    usuario + "%' or tel LIKE '%" + usuario + "%'");

            while(rs.next()){
                String aux = rs.getString("id_usuario");

                resultado.add(aux);
            }
        } catch (SQLException e) {
        }
        return resultado;
    }

    public static List<Evento> getInvitados() {
        List<Evento> resultado = new LinkedList<>();
        Statement comm;
        try {
            comm = conexion.createStatement();
            ResultSet rs = comm.executeQuery("SELECT * FROM [dbo].[Evento] INNER JOIN [dbo].[UsuarioInvitado] " +
                    "ON [dbo].[Evento].id_evento = [dbo].[UsuarioInvitado].id_evento and [dbo].[UsuarioInvitado].id_usuario = '" +
                    usuario.getId() + "'");

            while(rs.next()){
                String f = rs.getString("fecha_Hora");

                Date d = new Date(Integer.parseInt(f.substring(0,4)), Integer.parseInt(f.substring(5,7)), Integer.parseInt(f.substring(8,10)));
                Time t = new Time(Integer.parseInt(f.substring(11,13)), Integer.parseInt(f.substring(14,16)), 0);

                Evento e = new Evento(rs.getString("titulo"), d, t, R.drawable.eventos);

                e.setId(rs.getInt("id_evento"));
                e.setLat(rs.getDouble("lat"));
                e.setLng(rs.getDouble("lng"));
                e.setCosto(rs.getInt("costo"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setIdCreador(rs.getString("id_creador"));

                resultado.add(e);
            }
        } catch (SQLException e) {
        }
        return resultado;
    }

    public static void actualizarInfo(String nom, String valueOf) {
        PreparedStatement comm;
        try {
            comm = conexion.prepareStatement("UPDATE Usuario SET descripcion = '" + valueOf + "', nombre = '" + nom + "'");

            comm.executeUpdate();
        } catch (SQLException ex) {
        }
    }
}
