package ec.edu.insta.movilgc1.model.usuario;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface CrudGeneric<T, ID> {

    void create(Context context);

    ArrayList<T> read(Context context);

    void update(Context context, ID id);

    void delete(Context context, ID id);

     T buscarPorId(Context context, ID id);
/*

    List<T> arrayBuscarPorId(ID id);*/



}
