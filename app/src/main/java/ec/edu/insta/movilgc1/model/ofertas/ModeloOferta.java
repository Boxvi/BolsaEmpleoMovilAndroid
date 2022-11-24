package ec.edu.insta.movilgc1.model.ofertas;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import ec.edu.insta.movilgc1.dbtemp.SQLiteOpenHelper;
import ec.edu.insta.movilgc1.model.CrudGeneric;

public class ModeloOferta extends Oferta implements CrudGeneric<Oferta, Integer> {
    private SQLiteOpenHelper sqLiteOpenHelper;
    private Cursor cursor;

    public ModeloOferta() {
    }

    public ModeloOferta(int id, String cargo, String descripcion, String area_conocimiento, String salario, String jornada, String requisitos_academicos, String experiencia, String ubicacion, String fecha_inicio, String fecha_fin, String empresa, String ciudad, Boolean estado) {
        super(id, cargo, descripcion, area_conocimiento, salario, jornada, requisitos_academicos, experiencia, ubicacion, fecha_inicio, fecha_fin, empresa, ciudad, estado);
    }

    public ModeloOferta(SQLiteOpenHelper sqLiteOpenHelper, Cursor cursor) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
        this.cursor = cursor;
    }

    @Override
    public void create(Context context) {
        sqLiteOpenHelper = new SQLiteOpenHelper(context);
        String noSql = "INSERT INTO ofertas ( id, cargo, descripcion, area_conocimiento, salario, jornada, requisitos_academicos, experiencia, ubicacion, fecha_inicio, fecha_fin,empresa,ciudad,estado) VALUES ('"+getId()+"', '" + getCargo() + "', '" + getDescripcion() + "', '" + getArea_conocimiento() + "', '" + getSalario() + "', '" + getJornada() + "', '" + getRequisitos_academicos() + "', '" + getExperiencia() + "', '" + getUbicacion() + "', '" + getFecha_inicio() + "', '" + getFecha_fin() +"', '" + getEmpresa() +"', '" + getCiudad() +"', '" + getEstado() + "');";
        sqLiteOpenHelper.noQuery(noSql);
        sqLiteOpenHelper.close();
    }

    @Override
    public ArrayList<Oferta> read(Context context) {
        sqLiteOpenHelper = new SQLiteOpenHelper(context);
        String sql = "SELECT * FROM ofertas";

        cursor = sqLiteOpenHelper.query(sql);

        ArrayList<Oferta> ofertaArrayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            Oferta oferta = new Oferta();
            oferta.setId(cursor.getInt(0));
            oferta.setCargo(cursor.getString(1));
            oferta.setDescripcion(cursor.getString(2));
            oferta.setArea_conocimiento(cursor.getString(3));
            oferta.setSalario(cursor.getString(4));
            oferta.setJornada(cursor.getString(5));
            oferta.setRequisitos_academicos(cursor.getString(6));
            oferta.setCiudad(cursor.getString(7));
            oferta.setExperiencia(cursor.getString(8));
            oferta.setUbicacion(cursor.getString(9));
            oferta.setFecha_fin(cursor.getString(10));
            oferta.setFecha_fin(cursor.getString(11));
            oferta.setEmpresa(cursor.getString(12));
            oferta.setCiudad(cursor.getString(13));


            ofertaArrayList.add(oferta);

        }

        return ofertaArrayList;

    }

    @Override
    public void update(Context context, Integer integer) {
        sqLiteOpenHelper = new SQLiteOpenHelper(context);
        String noSql = "UPDATE ofertas SET  cargo = '" + getCargo() + "', descripcion = '" + getDescripcion() + "', area_conocimiento = '" + getArea_conocimiento() + "', salario = '" + getSalario() + "', jornada = '" + getJornada() + "', requisitos_academicos = '" + getRequisitos_academicos() + "', experiencia = '" + getExperiencia() + "', ubicacion = '" + getUbicacion() + "', fecha_inicio = '" + getFecha_inicio() + "', fecha_fin = '" + getFecha_fin() + "', empresa = '" + getEmpresa() +"', ciudad = '" + getCiudad() +"', estado = '" + getEstado() + "' WHERE id = " + integer + ";";
        sqLiteOpenHelper.noQuery(noSql);
        sqLiteOpenHelper.close();
    }

    @Override
    public void delete(Context context, Integer integer) {

    }

    @Override
    public Oferta buscarPorId(Context context, Integer integer) {
        sqLiteOpenHelper = new SQLiteOpenHelper(context);
        String sql = "SELECT * FROM ofertas WHERE id = " + integer + ";";
        cursor = sqLiteOpenHelper.query(sql);
        Oferta oferta = new Oferta();

        if (cursor.moveToNext()) {
            oferta.setId(cursor.getInt(0));
            oferta.setCargo(cursor.getString(2));
            oferta.setDescripcion(cursor.getString(3));
            oferta.setArea_conocimiento(cursor.getString(4));
            oferta.setSalario(cursor.getString(5));
            oferta.setJornada(cursor.getString(6));
            oferta.setRequisitos_academicos(cursor.getString(7));
            oferta.setExperiencia(cursor.getString(8));
            oferta.setUbicacion(cursor.getString(9));
            oferta.setFecha_inicio(cursor.getString(10));
            oferta.setFecha_fin(cursor.getString(11));
            oferta.setEmpresa(cursor.getString(12));
            oferta.setCiudad(cursor.getString(13));
            oferta.setEstado(cursor.getInt(14) == 1);
        }

        sqLiteOpenHelper.close();

        return oferta;
    }
}