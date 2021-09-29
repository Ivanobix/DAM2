package com.example.garciaprietoivan_20210218;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ArticuloDB {
    // Constantes BD
    public static final int VERSION = 1;
    public static final String BD_NOMBRE = "articulos.db";

    // Constantes TABLA
    private static final String TABLA_ARTICULOS = "ARTICULOS";
    private static final String COL_ID = "IDARTICULO";
    private static final String COL_NOM = "NOMBRE";
    private static final String COL_DESC = "DESCRIPCION";
    private static final String COL_CAT = "CATEGORIA";
    private static final String COL_EST = "ESTADO";
    private static final String COL_TLF = "TELEFONO";
    private static final String COL_PRE = "PRECIO";

    private static final int NUM_COL_ID = 0;
    private static final int NUM_COL_NOM = 1;
    private static final int NUM_COL_DESC = 2;
    private static final int NUM_COL_CAT = 3;
    private static final int NUM_COL_EST = 4;
    private static final int NUM_COL_TLF = 5;
    private static final int NUM_COL_PRE = 6;

    public final ArticuloHelper articulos;

    // Variables gestión BD
    private SQLiteDatabase bd;

    public ArticuloDB(Context context) {
        articulos = new ArticuloHelper(context, BD_NOMBRE, null, VERSION);
    }

    public void openForWrite() {
        bd = articulos.getWritableDatabase();
    }

    public void openForRead() {
        bd = articulos.getReadableDatabase();
    }

    public SQLiteDatabase getBD() {
        return bd;
    }

    public int eliminarArticulo(int id) {
        String where = COL_ID + " = " + id;

        return bd.delete(TABLA_ARTICULOS, where, null);
    }

    public long insertarArticulo(Articulo articulo) {
        ContentValues content = new ContentValues();

        content.put(COL_NOM, articulo.getNombre());
        content.put(COL_DESC, articulo.getDescripcion());
        content.put(COL_CAT, articulo.getCategoria());
        content.put(COL_EST, articulo.getEstado());
        content.put(COL_PRE, articulo.getPrecio());
        content.put(COL_TLF, articulo.getTelefono());

        return bd.insert(TABLA_ARTICULOS, null, content);
    }

    public int actualizarArticulo(int id, Articulo articulo) {
        ContentValues content = new ContentValues();

        content.put(COL_NOM, articulo.getNombre());
        content.put(COL_DESC, articulo.getDescripcion());
        content.put(COL_CAT, articulo.getCategoria());
        content.put(COL_EST, articulo.getEstado());
        content.put(COL_PRE, articulo.getPrecio());
        content.put(COL_TLF, articulo.getTelefono());

        String where = COL_ID + " = " + id;

        return bd.update(TABLA_ARTICULOS, content, where, null);
    }

    public Articulo obtenerArticulo(int id) {
        String where = COL_ID + " = " + id;
        String[] campos = new String[]{COL_ID, COL_NOM, COL_DESC, COL_CAT, COL_EST, COL_TLF, COL_PRE};

        Cursor cur = bd.query(TABLA_ARTICULOS, campos, where, null, null, null, COL_ID);

        Articulo articulo;
        if (cur.getCount() == 0) {
            articulo = null;

        } else {
            cur.moveToFirst();

            articulo = new Articulo();
            articulo.setIdArticulo(cur.getInt(NUM_COL_ID));
            articulo.setNombre(cur.getString(NUM_COL_NOM));
            articulo.setDescripcion(cur.getString(NUM_COL_DESC));
            articulo.setCategoria(cur.getString(NUM_COL_CAT));
            articulo.setEstado(cur.getString(NUM_COL_EST));
            articulo.setTelefono(cur.getString(NUM_COL_TLF));
            articulo.setPrecio(cur.getFloat(NUM_COL_PRE));

        }

        cur.close();
        return articulo;
    }

    public ArrayList<Articulo> obtenerTodosContactos() {
        String[] campos = new String[]{COL_ID, COL_NOM, COL_DESC, COL_CAT, COL_EST, COL_TLF, COL_PRE};
        ArrayList<Articulo> listaArticulos;

        Cursor cur = bd.query(TABLA_ARTICULOS, campos, null, null, null, null, COL_ID);

        if (cur.getCount() == 0) {
            listaArticulos = null;

        } else {
            listaArticulos = new ArrayList<>();

            while (cur.moveToNext()) {
                Articulo articulo = new Articulo();
                articulo.setIdArticulo(cur.getInt(NUM_COL_ID));
                articulo.setNombre(cur.getString(NUM_COL_NOM));
                articulo.setDescripcion(cur.getString(NUM_COL_DESC));
                articulo.setCategoria(cur.getString(NUM_COL_CAT));
                articulo.setEstado(cur.getString(NUM_COL_EST));
                articulo.setTelefono(cur.getString(NUM_COL_TLF));
                articulo.setPrecio(cur.getFloat(NUM_COL_PRE));
                listaArticulos.add(articulo);
            }
        }
        cur.close();
        return listaArticulos;
    }

    public void close() {
        bd.close();
    }
}
