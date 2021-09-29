package com.example.ejem_bbdd;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ANADIR_CONTACTO = 1;
    protected ListView listaContactos;
    protected ArrayList<String> lista;
    protected ArrayAdapter<String> adaptador;
    protected int ultimoContactoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContactos = (ListView) findViewById(R.id.listaContactos);
        lista = new ArrayList<>();

        // Mostramos el contenido de la BD en el ListView
        listar();
        // Añadir Contacto
        Button botNuevo = (Button) findViewById(R.id.botNuevo);
        botNuevo.setOnClickListener(view -> {
            Intent intento = new Intent(MainActivity.this, AddActivity.class);
            startActivityForResult(intento, REQUEST_ANADIR_CONTACTO);
        });

        registerForContextMenu(listaContactos);
        listaContactos.setOnItemLongClickListener((parent, view, position, id) -> {
            ultimoContactoSeleccionado = position;
            return false;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ANADIR_CONTACTO && resultCode == AddActivity.RESULT_GUARDADO) {
            listar();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opciones_contacto, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.opcEliminar) {
            eliminar();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    private void listar() {
        // Abrimos o creamos la BD
        SQLiteDatabase db;
        db = openOrCreateDatabase("MisContactos", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS misContactos (Nombre VARCHAR, Apellido1 VARCHAR, Apellido2 VARCHAR);");
        // Obtenemos los datos de la BD
        Cursor cur = db.rawQuery("SELECT * FROM MisContactos ORDER BY Apellido1, Apellido2", null);
        if (cur.getCount() == 0) {
            String mensa = "Lista vacía";
            int duracion = Toast.LENGTH_SHORT;
            Toast.makeText(getApplicationContext(), mensa, duracion).show();
        } else {
            lista.clear();
            while (cur.moveToNext()) {
                lista.add(cur.getString(0) + " " + cur.getString(1) + " "
                        + cur.getString(2));
            }
            Context c = getApplicationContext();
            adaptador = new ArrayAdapter<>(c, R.layout.list_item, lista);
            listaContactos.setAdapter(adaptador);
        }
        // Cerramos el cursor y la BD
        cur.close();
        db.close();
    }

    private void eliminar() {
        String contacto = adaptador.getItem(ultimoContactoSeleccionado).trim();
        String[] palabras = contacto.split(" ");
        String nombre = palabras[0];
        String apellido1 = palabras[1];
        String apellido2 = palabras[2];
        SQLiteDatabase db;
        db = openOrCreateDatabase("MisContactos", Context.MODE_PRIVATE, null);
        //Forma cutre
        db.execSQL("DELETE FROM MisContactos WHERE Nombre = '" + nombre + "' and Apellido1 = '" + apellido1 + "' and Apellido2 = '" + apellido2 + "';");

        //Forma correcta que no me funciona porque esta incompleta
        //int regs = db.delete("MisContactos", where, null);

        db.close();

        lista.remove(ultimoContactoSeleccionado);
        adaptador.notifyDataSetChanged();
        Toast.makeText(MainActivity.this, "El contacto ha sido eliminado.", Toast.LENGTH_SHORT).show();

    }

}