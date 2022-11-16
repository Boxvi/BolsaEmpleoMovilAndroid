package ec.edu.insta.movilgc1.model.usuario;

import android.content.Context;
import android.database.Cursor;
import ec.edu.insta.movilgc1.dbtemp.SQLiteOpenHelper;
import ec.edu.insta.movilgc1.model.CrudGeneric;

import java.util.ArrayList;


public class ModeloUsuario extends Usuario implements CrudGeneric<Usuario, Integer> {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private Cursor cursor;
    private Usuario usuario;

    public ModeloUsuario() {
    }

    public ModeloUsuario(Integer id, String username, String password, String email, String telefono, boolean estado, String fechaCreacion, String rol) {
        super(id, username, password, email, telefono, estado, fechaCreacion, rol);
    }

    /*
    public void agregarUsuario(Context context) {


    }*/

    @Override
    public void create(Context context) {
        sqLiteOpenHelper = new SQLiteOpenHelper(context);
        String noSql = "INSERT INTO login (id, username, pasword, email, telefono, estado, rol) VALUES ('" + getId() + "', '" + getUsername() + "', '" + getPassword() + "', '" + getEmail() + "', '" + getTelefono() + "', '" + isEstado() + "',  '" + getRol() + "')";
        sqLiteOpenHelper.noQuery(noSql);
        System.out.println(noSql);
        sqLiteOpenHelper.close();
    }

    @Override
    public ArrayList<Usuario> read(Context context) {
        sqLiteOpenHelper = new SQLiteOpenHelper(context);
        String nosql = "SELECT * FROM login";
        System.out.println(nosql);

        cursor = sqLiteOpenHelper.query(nosql);

        ArrayList<Usuario> usuarioArrayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setUsername(cursor.getString(1));
            usuario.setPassword(cursor.getString(2));
            usuario.setEmail(cursor.getString(3));
            usuario.setTelefono(cursor.getString(4));
            usuario.setEstado(cursor.getInt(5) == 1);
            usuario.setRol(cursor.getString(6));
            usuarioArrayList.add(usuario);
            System.out.println(usuario);
        }

        sqLiteOpenHelper.close();
        return usuarioArrayList;
    }

    @Override
    public void update(Context context, Integer integer) {
        sqLiteOpenHelper = new SQLiteOpenHelper(context);
        String noSql = "UPDATE login SET username = '" + getUsername() + "', pasword = '" + getPassword() + "', email = '" + getEmail() + "', telefono = '" + getTelefono() + "', estado = '" + isEstado() + "', rol = '" + getRol() + "' WHERE id = " + integer;
        sqLiteOpenHelper.noQuery(noSql);
        System.out.println(noSql);
        sqLiteOpenHelper.close();
    }

    @Override
    public void delete(Context context, Integer integer) {

    }

    @Override
    public Usuario buscarPorId(Context context, Integer integer) {
        sqLiteOpenHelper = new SQLiteOpenHelper(context);
        String nosql = "SELECT * FROM login WHERE id = " + integer;

        Cursor cursor = sqLiteOpenHelper.query(nosql);

        Usuario usuario = new Usuario();

        if (cursor.moveToNext()) {
            usuario.setId(cursor.getInt(0));
            usuario.setUsername(cursor.getString(1));
            usuario.setPassword(cursor.getString(2));
            usuario.setEmail(cursor.getString(3));
            usuario.setTelefono(cursor.getString(4));
            usuario.setEstado(cursor.getInt(5) == 1);
            usuario.setRol(cursor.getString(6));
        }

        sqLiteOpenHelper.close();
        return usuario;
    }
}

/*
public boolean agregarUsuario(String username, String cedula, String nombres, String apellidos, String genero, String fechaNacimiento, String ciudad, String direccion, String estadoCivil) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        if (sqLiteDatabase != null) {
            ");
            sqLiteDatabase.close();
            return true;
        }
        return false;
    }

    public void editarUsuario(String nombres, String apellidos, String genero, String fechaNacimiento, String ciudad, String direccion, String estadoCivil, String username) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        if (sqLiteDatabase != null) {
            sqLiteDatabase.execSQL("UPDATE usuarios SET nombres = '" + nombres + "', apellidos = '" + apellidos + "', genero = '" + genero + "', fechaNacimiento = '" + fechaNacimiento + "', ciudad = '" + ciudad + "', direccion = '" + direccion + "', estadoCivil = '" + estadoCivil + "' WHERE username = '" + username + "'");
            sqLiteDatabase.close();
        }
    }

    public void agregarLogin(String username, String password, String email, String telefono, String estado, String fechaCreacion, String rol) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        if (sqLiteDatabase != null) {
            INSERT INTO login (username, pasword, email, telefono, estado, fechaCreacion, rol) VALUES ('" + username + "', '" + password + "', '" + email + "', '" + telefono + "', '" + estado + "', '" + fechaCreacion + "', '" + rol + "')");
            sqLiteDatabase.close();
        }
    }
 */