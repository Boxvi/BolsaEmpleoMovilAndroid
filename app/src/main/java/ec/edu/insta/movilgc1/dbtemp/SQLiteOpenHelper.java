package ec.edu.insta.movilgc1.dbtemp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;

public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbtemp";
    private static final int DATABASE_VERSION = 5;

    private static String TABLA_EMPRESA = "create table empresa(" +
            "id INTEGER PRIMARY KEY  NOT NULL, " +
            "sectorEmpresarial text, " +
            "ruc text, " +
            "nombre text, " +
            "tipoEmpresa text, " +
            "razonSocial text, " +
            "departamento text, " +
            "ciudad text, " +
            "direccion text, " +
            "sitioWeb text, " +
            "estado boolean)";

    private static String DROP_TABLE_EMPRESA = "DROP TABLE IF EXISTS empresa";


    private static String TABLA_USUARIOS = "create table login (" +
            "id INTEGER PRIMARY KEY  NOT NULL," +
            "username text, " +
            "pasword text, " +
            "email text, " +
            "telefono text, " +
            "estado text, " +
            "fechaCreacion text, " +
            "rol text)";

    private static String DROP_TABLE_USUARIOS = "DROP TABLE IF EXISTS login";


    private static String TABLA_ESTUDIANTE = "create table estudiante (" +
            "id INTEGER PRIMARY KEY  NOT NULL," +
            "cedula text, " +
            "nombres text, " +
            "apellidos text, " +
            "genero text, " +
            "fecha_nacimiento text, " +
            "ciudad text, " +
            "direccion text, " +
            "estado_civil text, " +
            "ruta_imagen text, " +
            "url_imagen text, " +
            "estado boolean)";

    private static String DROP_TABLE_ESTUDIANTE = "DROP TABLE IF EXISTS estudiante";

    private static String TABLA_OFERTA = "create table ofertas(" +
            "id INTEGER PRIMARY KEY  NOT NULL, " +
            "cargo text, " +
            "descripcion text, " +
            "area_conocimiento text, " +
            "salario text, " +
            "jornada text, " +
            "requisitos_academicos text, " +
            "experiencia text, " +
            "ubicacion text, " +
            "fecha_inicio text, " +
            "fecha_fin text, " +
            "empresa text, " +
            "ciudad text, " +
            "estado boolean)";

    private static String DROP_TABLE_OFERTA = "DROP TABLE IF EXISTS ofertas";


    public SQLiteOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_EMPRESA);
        sqLiteDatabase.execSQL(TABLA_USUARIOS);
        sqLiteDatabase.execSQL(TABLA_ESTUDIANTE);
        sqLiteDatabase.execSQL(TABLA_OFERTA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_TABLE_EMPRESA);
        sqLiteDatabase.execSQL(DROP_TABLE_USUARIOS);
        sqLiteDatabase.execSQL(DROP_TABLE_ESTUDIANTE);
        sqLiteDatabase.execSQL(DROP_TABLE_OFERTA);
        onCreate(sqLiteDatabase);


    }


    public Cursor query(String sql) {
        return this.getReadableDatabase().rawQuery(sql, null);
    }

    public void noQuery(String noSql) {
        this.getWritableDatabase().execSQL(noSql);
    }
}
