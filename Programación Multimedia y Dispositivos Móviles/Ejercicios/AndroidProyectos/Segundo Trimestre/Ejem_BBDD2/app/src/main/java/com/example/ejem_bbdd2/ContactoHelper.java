package com.example.ejem_bbdd2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactoHelper extends SQLiteOpenHelper {
    // Constantes TABLA
    private static final String TABLA_CONTACTOS = "TABLA_CONTACTOS";
    private static final String COL_ID = "ID";
    private static final String COL_NOMBRE = "NOMBRE";
    private static final String COL_APE1 = "APELLIDO1";
    private static final String COL_APE2 = "APELLIDO2";
    private static final String COL_TLF = "TELEFONO";
    // Constante creacion tabla
    private static final String CREATE_BD =
            "CREATE TABLE " + TABLA_CONTACTOS + " ("
                    + COL_ID + " INTEGER PRIMARY KEY, "
                    + COL_NOMBRE + " TEXT NOT NULL, "
                    + COL_APE1 + " TEXT NOT NULL, "
                    + COL_APE2 + " TEXT NOT NULL, "
                    + COL_TLF + " TEXT NOT NULL);";

    // Constructor
    public ContactoHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creamos la BD
        db.execSQL(CREATE_BD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Gestionamos las actualizaciones de la BD
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_CONTACTOS);
        onCreate(db);
    }
}
