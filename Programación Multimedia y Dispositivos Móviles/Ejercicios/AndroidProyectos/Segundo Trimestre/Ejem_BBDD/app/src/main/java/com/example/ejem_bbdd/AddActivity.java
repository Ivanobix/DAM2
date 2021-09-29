package com.example.ejem_bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    public static final int RESULT_GUARDADO = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Guardar datos en la BD
        Button botGuardar = (Button) findViewById(R.id.botGuardar);
        botGuardar.setOnClickListener((View.OnClickListener) view -> {
            // Recogemos los datos
            EditText editNombre = (EditText) findViewById(R.id.editNombre);
            EditText editApe1 = (EditText) findViewById(R.id.editApellido1);
            EditText editApe2 = (EditText) findViewById(R.id.editApellido2);
            String nombre = editNombre.getText().toString().trim();
            String ape1 = editApe1.getText().toString().trim();
            String ape2 = editApe2.getText().toString().trim();
            String mensa;
            if (!nombre.equals("") && !ape1.equals("") && !ape2.equals("")) {

                // Abrimos o creamos la BD
                SQLiteDatabase db;
                db = openOrCreateDatabase("MisContactos", Context.MODE_PRIVATE, null);
                db.execSQL("CREATE TABLE IF NOT EXISTS misContactos (Nombre VARCHAR, Apellido1 VARCHAR, Apellido2 VARCHAR); ");
                //Manera cutre
                //db.execSQL("INSERT INTO misContactos VALUES ('" + nombre + "', '" + ape1 + "', '" + ape2 + "'); ");

                //Manera correcta
                ContentValues contacto = new ContentValues();
                contacto.put("Nombre", nombre);
                contacto.put("Apellido1", ape1);
                contacto.put("Apellido2", ape2);
                if (db.insert("MisContactos", null, contacto) != -1) {
                    mensa = "Contacto añadido";
                } else {
                    mensa = "No se ha podido añadir";
                }
                // Alerta: contacto añadido
                Toast.makeText(getApplicationContext(), mensa, Toast.LENGTH_SHORT).show();
                // Borramos los datos de pantalla
                editNombre.setText("");
                editApe1.setText("");
                editApe2.setText("");
                setResult(RESULT_GUARDADO);
                finish();
            } else {
                // Alerta: faltan datos
                mensa = "Faltan datos";
                Toast.makeText(getApplicationContext(), mensa, Toast.LENGTH_SHORT).show();
            }
        });
    }
}