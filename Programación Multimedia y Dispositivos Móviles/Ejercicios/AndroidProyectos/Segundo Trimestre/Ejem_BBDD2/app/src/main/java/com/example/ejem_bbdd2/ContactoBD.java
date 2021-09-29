package com.example.ejem_bbdd2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContactoBD {

    // Constantes BD
    public static final int VERSION = 1;
    public static final String BD_NOMBRE = "contactos.db";

    // Constantes TABLA
    public static final String TABLA_CONTACTOS = "TABLA_CONTACTOS";
    public static final String COL_ID = "ID";
    public static final int NUM_COL_ID = 0;
    public static final String COL_NOMBRE = "NOMBRE";
    public static final int NUM_COL_NOMBRE = 1;
    public static final String COL_APE1 = "APELLIDO1";
    public static final int NUM_COL_APE1 = 2;
    public static final String COL_APE2 = "APELLIDO2";
    public static final int NUM_COL_APE2 = 3;
    public static final String COL_TLF = "TELEFONO";
    public static final int NUM_COL_TLF = 4;
    public final ContactoHelper contactos;

    // Variables gestión BD
    private SQLiteDatabase bd;

    public ContactoBD(Context context) {
        contactos = new ContactoHelper(context, BD_NOMBRE, null, VERSION);
    }

    public void openForWrite() {
        bd = contactos.getWritableDatabase();
    }

    public void openForRead() {
        bd = contactos.getReadableDatabase();
    }

    public SQLiteDatabase getBD() {
        return bd;
    }

    public int eliminarContacto(int id) {
        String where = COL_ID + " = " + id;

        return bd.delete(TABLA_CONTACTOS, where, null);
    }

    public long insertarContacto(Contacto contacto) {
        ContentValues content = new ContentValues();

        content.put(COL_NOMBRE, contacto.getNombre());
        content.put(COL_APE1, contacto.getApellido1());
        content.put(COL_APE2, contacto.getApellido2());
        content.put(COL_TLF, contacto.getTelefono());

        return bd.insert(TABLA_CONTACTOS, null, content);
    }

    public int actualizarContacto(int id, Contacto contacto) {
        ContentValues content = new ContentValues();

        content.put(COL_NOMBRE, contacto.getNombre());
        content.put(COL_APE1, contacto.getApellido1());
        content.put(COL_APE2, contacto.getApellido2());
        content.put(COL_TLF, contacto.getTelefono());

        String where = COL_ID + " = " + id;

        return bd.update(TABLA_CONTACTOS, content, where, null);
    }

    public Contacto obtenerContacto(int id) {
        String where = COL_ID + " = " + id;
        String[] campos = new String[]{COL_ID, COL_NOMBRE, COL_APE1, COL_APE2, COL_TLF};

        Cursor cur = bd.query(TABLA_CONTACTOS, campos, where, null, null, null, COL_ID);

        Contacto contacto;
        if (cur.getCount() == 0) {
            contacto = null;

        } else {
            cur.moveToFirst();

            contacto = new Contacto();
            contacto.setId(cur.getInt(NUM_COL_ID));
            contacto.setNombre(cur.getString(NUM_COL_NOMBRE));
            contacto.setApellido1(cur.getString(NUM_COL_APE1));
            contacto.setApellido2(cur.getString(NUM_COL_APE2));
            contacto.setTelefono(cur.getString(NUM_COL_TLF));
        }

        cur.close();
        return contacto;
    }

    public ArrayList<Contacto> obtenerTodosContactos() {
        String[] campos = new String[]{COL_ID, COL_NOMBRE, COL_APE1, COL_APE2, COL_TLF};
        ArrayList<Contacto> listaContactos;

        Cursor cur = bd.query(TABLA_CONTACTOS, campos, null, null, null, null, COL_APE1 + ", " + COL_APE2);

        if (cur.getCount() == 0) {
            listaContactos = null;

        } else {
            listaContactos = new ArrayList<>();

            while (cur.moveToNext()) {
                Contacto contacto = new Contacto();
                contacto.setId(cur.getInt(NUM_COL_ID));
                contacto.setNombre(cur.getString(NUM_COL_NOMBRE));
                contacto.setApellido1(cur.getString(NUM_COL_APE1));
                contacto.setApellido2(cur.getString(NUM_COL_APE2));
                contacto.setTelefono(cur.getString(NUM_COL_TLF));
                listaContactos.add(contacto);
            }
        }
        cur.close();
        return listaContactos;
    }

    public void close() {
        bd.close();
    }
}
