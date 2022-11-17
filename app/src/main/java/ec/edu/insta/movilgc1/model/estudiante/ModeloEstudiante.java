package ec.edu.insta.movilgc1.model.estudiante;

import android.content.Context;
import android.database.Cursor;
import ec.edu.insta.movilgc1.dbtemp.SQLiteOpenHelper;
import ec.edu.insta.movilgc1.model.CrudGeneric;

import java.util.ArrayList;

public class ModeloEstudiante extends Estudiante implements CrudGeneric<Estudiante, Integer> {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private Cursor cursor;

    public ModeloEstudiante() {
    }

    public ModeloEstudiante(int id, String cedula, String nombres, String apellidos, String genero, String fechaNacimiento, String ciudad, String direccion, String estadoCivil, String rutaImagen, String urlImagen, Boolean estado) {
        super(id, cedula, nombres, apellidos, genero, fechaNacimiento, ciudad, direccion, estadoCivil, rutaImagen, urlImagen, estado);
    }

    @Override
    public void create(Context context) {
        sqLiteOpenHelper = new SQLiteOpenHelper(context);
        String noSql = "INSERT INTO estudiante (cedula, nombres, apellidos, genero, fecha_nacimiento, ciudad, direccion," +
                " estado_civil, ruta_imagen, url_imagen, estado) VALUES ('" + getCedula() + "', '" + getNombres() + "', '"
                + getApellidos() + "', '" + getGenero() + "', '" + getFechaNacimiento() + "', '"
                + getCiudad() + "', '" + getDireccion() + "', '" + getEstadoCivil() + "', '"
                + getRutaImagen() + "', '" + getUrlImagen() + "', " + getEstado() + ")";

        sqLiteOpenHelper.noQuery(noSql);
        sqLiteOpenHelper.close();
    }

    @Override
    public ArrayList<Estudiante> read(Context context) {
        sqLiteOpenHelper = new SQLiteOpenHelper(context);
        String sql = "SELECT * FROM estudiante";

        cursor = sqLiteOpenHelper.query(sql);

        ArrayList<Estudiante> estudianteArrayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            Estudiante estudiante = new Estudiante();
            estudiante.setId(cursor.getInt(0));
            estudiante.setCedula(cursor.getString(1));
            estudiante.setNombres(cursor.getString(2));
            estudiante.setApellidos(cursor.getString(3));
            estudiante.setGenero(cursor.getString(4));
            estudiante.setFechaNacimiento(cursor.getString(5));
            estudiante.setCiudad(cursor.getString(6));
            estudiante.setDireccion(cursor.getString(7));
            estudiante.setEstadoCivil(cursor.getString(8));
            estudiante.setRutaImagen(cursor.getString(9));
            estudiante.setUrlImagen(cursor.getString(10));
            estudiante.setEstado(cursor.getInt(11) == 1);
            estudianteArrayList.add(estudiante);
        }

        sqLiteOpenHelper.close();

        return estudianteArrayList;
    }

    @Override
    public void update(Context context, Integer integer) {
        sqLiteOpenHelper = new SQLiteOpenHelper(context);
        String noSql = "UPDATE estudiante SET cedula = '" + getCedula() + "', nombres = '" + getNombres() + "', apellidos = '"
                + getApellidos() + "', genero = '" + getGenero() + "', fecha_nacimiento = '" + getFechaNacimiento() + "', ciudad = '"
                + getCiudad() + "', direccion = '" + getDireccion() + "', estado_civil = '" + getEstadoCivil() + "', ruta_imagen = '"
                + getRutaImagen() + "', url_imagen = '" + getUrlImagen() + "', estado = '" + getEstado() + "' WHERE id = " + integer + ";";


        sqLiteOpenHelper.noQuery(noSql);
        sqLiteOpenHelper.close();

    }

    @Override
    public void delete(Context context, Integer integer) {

    }

    @Override
    public Estudiante buscarPorId(Context context, Integer integer) {
        sqLiteOpenHelper = new SQLiteOpenHelper(context);
        String sql = "SELECT * FROM estudiante WHERE id = " + integer + ";";

        cursor = sqLiteOpenHelper.query(sql);

        Estudiante estudiante = new Estudiante();

        if (cursor.moveToNext()) {
            estudiante.setId(cursor.getInt(0));
            estudiante.setCedula(cursor.getString(1));
            estudiante.setNombres(cursor.getString(2));
            estudiante.setApellidos(cursor.getString(3));
            estudiante.setGenero(cursor.getString(4));
            estudiante.setFechaNacimiento(cursor.getString(5));
            estudiante.setCiudad(cursor.getString(6));
            estudiante.setDireccion(cursor.getString(7));
            estudiante.setEstadoCivil(cursor.getString(8));
            estudiante.setRutaImagen(cursor.getString(9));
            estudiante.setUrlImagen(cursor.getString(10));
            estudiante.setEstado(cursor.getInt(11) == 1);
        }
        sqLiteOpenHelper.close();

        return estudiante;
    }
}
