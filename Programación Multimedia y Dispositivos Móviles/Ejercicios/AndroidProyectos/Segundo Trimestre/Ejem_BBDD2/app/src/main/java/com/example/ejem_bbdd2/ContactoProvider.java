package com.example.ejem_bbdd2;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContactoProvider extends ContentProvider {
    public static final Uri contentUri =
            Uri.parse("content://com.example.ejem_bbdd2.ContactoProvider");
    private final String CONTENT_PROVIDER_MIME =
            "vnd.android.cursor.item/com.example.ejem_bbdd2.provider.contactos";
    private ContactoHelper bdHelper;

    @Override
    public boolean onCreate() {
        bdHelper = new ContactoHelper(getContext(), ContactoBD.BD_NOMBRE, null,
                ContactoBD.VERSION);
        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return CONTENT_PROVIDER_MIME;
    }

    public long getId(Uri contentUri) {
        long id = -1;
        String idRuta = contentUri.getLastPathSegment();
        if (idRuta != null) {
            id = Long.parseLong(idRuta);
        }
        return id;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues valores) {
        SQLiteDatabase bd = bdHelper.getWritableDatabase();
        try {
            long id = bd.insertOrThrow(ContactoBD.TABLA_CONTACTOS, null, valores);
            if (id == -1) {
                throw new RuntimeException(String.format("%s: Error al insertar[%s]",
                        "ContactoProvider", valores, uri));
            } else {
                return ContentUris.withAppendedId(uri, id);
            }
        } finally {
            bd.close();
        }
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] proyeccion,
                        @Nullable String seleccion, @Nullable String[] seleccionArgs,
                        @Nullable String orden) {
        long id = getId(uri);
        SQLiteDatabase bd = bdHelper.getReadableDatabase();
        if (id < 0) {
            return bd.query(ContactoBD.TABLA_CONTACTOS, proyeccion, seleccion,
                    seleccionArgs, null, null, orden);
        } else {
            return bd.query(ContactoBD.TABLA_CONTACTOS, proyeccion,
                    ContactoBD.COL_ID + "=" + id, null, null, null, null);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues valores,
                      @Nullable String seleccion, @Nullable String[] seleccionArgs) {
        long id = getId(uri);
        SQLiteDatabase bd = bdHelper.getWritableDatabase();
        try {
            if (id < 0) {
                return bd.update(ContactoBD.TABLA_CONTACTOS, valores, seleccion,
                        seleccionArgs);
            } else {
                return bd.update(ContactoBD.TABLA_CONTACTOS, valores,
                        ContactoBD.COL_ID + "=" + id, null);
            }
        } finally {
            bd.close();
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String seleccion,
                      @Nullable String[] seleccionArgs) {
        long id = getId(uri);
        SQLiteDatabase bd = bdHelper.getWritableDatabase();
        try {
            if (id < 0) {
                return bd.delete(ContactoBD.TABLA_CONTACTOS, seleccion, seleccionArgs);
            } else {
                return bd.delete(ContactoBD.TABLA_CONTACTOS,
                        ContactoBD.COL_ID + "=" + id, null);
            }
        } finally {
            bd.close();
        }
    }

}
