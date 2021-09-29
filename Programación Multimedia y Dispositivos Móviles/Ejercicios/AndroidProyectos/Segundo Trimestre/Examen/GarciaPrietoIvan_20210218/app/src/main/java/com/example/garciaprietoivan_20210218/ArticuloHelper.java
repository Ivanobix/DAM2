package com.example.garciaprietoivan_20210218;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ArticuloHelper extends SQLiteOpenHelper {

    // Constantes TABLA
    private static final String TABLA_ARTICULOS = "ARTICULOS";
    private static final String COL_ID = "IDARTICULO";
    private static final String COL_NOM = "NOMBRE";
    private static final String COL_DESC = "DESCRIPCION";
    private static final String COL_CAT = "CATEGORIA";
    private static final String COL_EST = "ESTADO";
    private static final String COL_TLF = "TELEFONO";
    private static final String COL_PRE = "PRECIO";

    // Constante creacion tabla
    private static final String CREATE_BD =
            "CREATE TABLE " + TABLA_ARTICULOS + " ("
                    + COL_ID + " INTEGER PRIMARY KEY, "
                    + COL_NOM + " TEXT NOT NULL, "
                    + COL_DESC + " TEXT NOT NULL, "
                    + COL_CAT + " TEXT NOT NULL, "
                    + COL_EST + " TEXT NOT NULL, "
                    + COL_TLF + " TEXT NOT NULL, "
                    + COL_PRE + " REAL NOT NULL);";

    // Constructor
    public ArticuloHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_BD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_ARTICULOS);
        onCreate(db);
    }
}
