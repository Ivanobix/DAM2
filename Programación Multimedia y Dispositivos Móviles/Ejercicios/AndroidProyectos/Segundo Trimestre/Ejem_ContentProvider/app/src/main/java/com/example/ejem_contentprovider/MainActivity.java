package com.example.ejem_contentprovider;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int URL_LOADER = 0;
    protected ListView lvContactos;
    protected ArrayList<Contacto> listaContactos;
    protected ArrayAdapter<Contacto> adaptador;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton botAdd =
                (FloatingActionButton) findViewById(R.id.botAdd);
        botAdd.setOnClickListener(view -> {
            Intent intento = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intento);
        });
        // Mostrar Contactos
        listaContactos = new ArrayList<Contacto>();
        adaptador = new ArrayAdapter<Contacto>
                (this, android.R.layout.simple_list_item_1, listaContactos);
        lvContactos = (ListView) findViewById(R.id.lvContactos);
        lvContactos.setAdapter(adaptador);
        //getLoaderManager().initLoader(URL_LOADER, null, this);
        // En caso de error con el método anterior podemos utilizar la versión:
        getSupportLoaderManager().initLoader(URL_LOADER, null, this);
    }

    protected void onRestart() {
        super.onRestart();
        //getLoaderManager().restartLoader(URL_LOADER, null, this);
        // En caso de error con el método anterior podemos utilizar la versión:
        getSupportLoaderManager().restartLoader(URL_LOADER, null, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Loader<Cursor> onCreateLoader(int loaderID, Bundle bundle) {
        switch (loaderID) {
            case URL_LOADER:
                String columns[] = new String[]{
                        Contacto.COL_ID,
                        Contacto.COL_NOMBRE,
                        Contacto.COL_APE1,
                        Contacto.COL_APE2,
                        Contacto.COL_TLF
                };
                return new CursorLoader(this, Contacto.contentUri, columns,
                        null, null, null);
            default:
                // ID invalido
                return null;
        }
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                listaContactos.clear();
                do {
                    Contacto contacto = new Contacto();
                    contacto.setId(cursor.getInt(cursor.getColumnIndex
                            (Contacto.COL_ID)));
                    contacto.setNombre(cursor.getString(cursor.getColumnIndex
                            (Contacto.COL_NOMBRE)));
                    contacto.setApellido1(cursor.getString(cursor.getColumnIndex
                            (Contacto.COL_APE1)));
                    contacto.setApellido2(cursor.getString(cursor.getColumnIndex
                            (Contacto.COL_APE2)));
                    contacto.setTelefono(cursor.getString(cursor.getColumnIndex
                            (Contacto.COL_TLF)));
                    listaContactos.add(contacto);
                } while (cursor.moveToNext());
                if (listaContactos.size() == 0) {
                    String mensa = "Lista vacía";
                    int duracion = Toast.LENGTH_SHORT;
                    Toast.makeText(getApplicationContext(), mensa, duracion).show();
                } else {
                    adaptador.notifyDataSetChanged();
                }
            }
        } else {
            // La BD puede que no se haya creado
            String mensa = "BD inaccesible";
            int duracion = Toast.LENGTH_SHORT;
            Toast.makeText(getApplicationContext(), mensa, duracion).show();
        }
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        int duracion = Toast.LENGTH_SHORT;
        String mensa = "Cargador reiniciado";
        Toast.makeText(getApplicationContext(), mensa, duracion).show();
    }

}