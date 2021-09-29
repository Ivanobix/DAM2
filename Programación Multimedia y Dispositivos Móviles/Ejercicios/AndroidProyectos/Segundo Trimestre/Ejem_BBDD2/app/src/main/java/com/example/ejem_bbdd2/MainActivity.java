package com.example.ejem_bbdd2;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ADD = 123;
    private ArrayList<Contacto> listaContactos;
    private ListView lista;
    private int ultimoContactoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.lvContactos);

        // Mostramos el contenido de la BD en el ListView
        listar();

        // Añadir Contacto
        Button botNuevo = (Button) findViewById(R.id.botNuevo);
        botNuevo.setOnClickListener(view -> {
            Intent intento = new Intent(MainActivity.this, AddActivity.class);
            startActivityForResult(intento, REQUEST_ADD);
        });

        //Eliminar Contacto
        registerForContextMenu(lista);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD && resultCode == AddActivity.RESULT_ADDED) {
            listar();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        ultimoContactoSeleccionado = info.position;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.opcEliminar) {
            eliminar(listaContactos.get(ultimoContactoSeleccionado));
            return true;
        }
        return super.onContextItemSelected(item);
    }

    private void eliminar(Contacto contacto) {
        ContactoBD contactoBD = new ContactoBD(getApplicationContext());
        contactoBD.openForWrite();
        contactoBD.eliminarContacto(contacto.getId());
        listar();
        Toast.makeText(MainActivity.this, "Contacto Eliminado", Toast.LENGTH_SHORT).show();
    }

    private void listar() {
        ContactoBD contactoBD = new ContactoBD(getApplicationContext());
        contactoBD.openForRead();
        listaContactos = contactoBD.obtenerTodosContactos();
        contactoBD.close();
        if (listaContactos == null) {
            Toast.makeText(getApplicationContext(), "Lista vacía", Toast.LENGTH_SHORT).show();
        } else {
            ArrayAdapter<Contacto> adaptador = new ArrayAdapter<>
                    (this, android.R.layout.simple_list_item_1, listaContactos);
            lista.setAdapter(adaptador);
        }
    }

    private void vaciar() {
        ContactoBD contactoBD = new ContactoBD(getApplicationContext());
        contactoBD.openForWrite();
        ArrayList<Contacto> lista = contactoBD.obtenerTodosContactos();
        int id;
        for (int i = 0; i < lista.size(); i++) {
            id = lista.get(i).getId();
            contactoBD.eliminarContacto(id);
        }
        contactoBD.close();
    }
}