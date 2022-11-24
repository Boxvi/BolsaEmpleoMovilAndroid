package ec.edu.insta.movilgc1.model.empresa;

import android.content.Context;
import android.database.Cursor;
import ec.edu.insta.movilgc1.dbtemp.SQLiteOpenHelper;
import ec.edu.insta.movilgc1.model.CrudGeneric;

import java.util.ArrayList;

public class ModeloEmpresa extends Empresa implements CrudGeneric<Empresa, Integer> {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private Cursor cursor;

    public ModeloEmpresa() {
    }

    public ModeloEmpresa(int id, String sectorEmpresarial, String ruc, String nombre, String tipoEmpresa, String razonSocial, String departamento, String ciudad, String direccion, String sitioWeb, boolean estado) {
        super(id, sectorEmpresarial, ruc, nombre, tipoEmpresa, razonSocial, departamento, ciudad, direccion, sitioWeb, estado);
    }

    @Override
    public void create(Context context) {
        sqLiteOpenHelper = new SQLiteOpenHelper(context);
        String noSql = "INSERT INTO empresa ( id, sectorEmpresarial, ruc, nombre, tipoEmpresa, razonSocial, departamento, ciudad, direccion, sitioWeb, estado) VALUES ('"+getId()+"', '"
                + getSectorEmpresarial() + "', '" + getRuc() + "', '" + getNombre() + "', '" + getTipoEmpresa() + "', '"
                + getRazonSocial() + "', '" + getDepartamento() + "', '" + getCiudad() + "', '" + getDireccion() + "', '"
                + getSitioWeb() + "', '" + isEstado() + "');";
        sqLiteOpenHelper.noQuery(noSql);
        sqLiteOpenHelper.close();

    }

    @Override
    public ArrayList<Empresa> read(Context context) {
        sqLiteOpenHelper = new SQLiteOpenHelper(context);
        String sql = "SELECT * FROM empresa";

        cursor = sqLiteOpenHelper.query(sql);

        ArrayList<Empresa> empresaArrayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            Empresa empresa = new Empresa();
            empresa.setId(cursor.getInt(0));
            empresa.setSectorEmpresarial(cursor.getString(1));
            empresa.setRuc(cursor.getString(2));
            empresa.setNombre(cursor.getString(3));
            empresa.setTipoEmpresa(cursor.getString(4));
            empresa.setRazonSocial(cursor.getString(5));
            empresa.setDepartamento(cursor.getString(6));
            empresa.setCiudad(cursor.getString(7));
            empresa.setDireccion(cursor.getString(8));
            empresa.setSitioWeb(cursor.getString(9));
            empresa.setEstado(Boolean.parseBoolean(cursor.getString(10)));

            empresaArrayList.add(empresa);

        }

        return empresaArrayList;
    }

    @Override
    public void update(Context context, Integer integer) {
        sqLiteOpenHelper = new SQLiteOpenHelper(context);
        String noSql = "UPDATE empresa SET  sectorEmpresarial = '" + getSectorEmpresarial() + "', ruc = '" + getRuc() + "', nombre = '" + getNombre() + "', tipoEmpresa = '" + getTipoEmpresa() + "', razonSocial = '" + getRazonSocial() + "', departamento = '" + getDepartamento() + "', ciudad = '" + getCiudad() + "', direccion = '" + getDireccion() + "', sitioWeb = '" + getSitioWeb() + "', estado = '" + isEstado() + "' WHERE id = " + integer + ";";
        sqLiteOpenHelper.noQuery(noSql);
        sqLiteOpenHelper.close();

    }

    @Override
    public void delete(Context context, Integer integer) {

    }

    @Override
    public Empresa buscarPorId(Context context, Integer integer) {

        sqLiteOpenHelper = new SQLiteOpenHelper(context);
        String sql = "SELECT * FROM empresa WHERE id = " + integer + ";";
        cursor = sqLiteOpenHelper.query(sql);
        Empresa empresa = new Empresa();

        if (cursor.moveToNext()) {
            empresa.setId(cursor.getInt(0));
            empresa.setSectorEmpresarial(cursor.getString(1));
            empresa.setRuc(cursor.getString(2));
            empresa.setNombre(cursor.getString(3));
            empresa.setTipoEmpresa(cursor.getString(4));
            empresa.setRazonSocial(cursor.getString(5));
            empresa.setDepartamento(cursor.getString(6));
            empresa.setCiudad(cursor.getString(7));
            empresa.setDireccion(cursor.getString(8));
            empresa.setSitioWeb(cursor.getString(9));
            empresa.setEstado(Boolean.parseBoolean(cursor.getString(10)));
        }

        sqLiteOpenHelper.close();

        return empresa;
    }
}
