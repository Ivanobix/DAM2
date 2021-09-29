package com.example.ejem_contentprovider;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
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
            EditText editTlf = (EditText) findViewById(R.id.editTelefono);
            String nombre = editNombre.getText().toString().trim();
            String ape1 = editApe1.getText().toString().trim();
            String ape2 = editApe2.getText().toString().trim();
            String tlf = editTlf.getText().toString().trim();
            String mensa;
            int duracion = Toast.LENGTH_SHORT;
            if (!nombre.equals("") && !ape1.equals("") && !ape2.equals("")
                    && !tlf.equals("")) {
                // Creamos un contacto y le asignamos valores
                ContentValues contacto = new ContentValues();
                contacto.put(Contacto.COL_NOMBRE, nombre);
                contacto.put(Contacto.COL_APE1, ape1);
                contacto.put(Contacto.COL_APE2, ape2);
                contacto.put(Contacto.COL_TLF, tlf);
                if (getContentResolver().insert(Contacto.contentUri, contacto)
                        != null) {
                    // Alerta: contacto añadido
                    mensa = "Contacto añadido";
                    Toast.makeText(getApplicationContext(), mensa, duracion).show();
                    // Borramos los datos de pantalla
                    editNombre.setText("");
                    editApe1.setText("");
                    editApe2.setText("");
                    editTlf.setText("");
                } else {
                    // Alerta: contacto no añadido
                    mensa = "No se ha podido añadir el contacto";
                    Toast.makeText(getApplicationContext(), mensa, duracion).show();
                }
            } else {
                // Alerta: faltan datos
                mensa = "Faltan datos";
                Toast.makeText(getApplicationContext(), mensa, duracion).show();
            }
        });
    }
}