package com.example.ejem_bbdd2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    public static final int RESULT_ADDED = 987;
    protected EditText editNombre, editApe1, editApe2, editTlf;
    protected Button botGuardar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editNombre = (EditText) findViewById(R.id.editNombre);
        editApe1 = (EditText) findViewById(R.id.editApellido1);
        editApe2 = (EditText) findViewById(R.id.editApellido2);
        editTlf = (EditText) findViewById(R.id.editTelefono);

        botGuardar = (Button) findViewById(R.id.botGuardar);

        botGuardar.setOnClickListener((View.OnClickListener) view -> anadirContacto());
    }

    private void anadirContacto() {
        String nombre = editNombre.getText().toString().trim();
        String ape1 = editApe1.getText().toString().trim();
        String ape2 = editApe2.getText().toString().trim();
        String tlf = editTlf.getText().toString().trim();

        String mensa;

        if (!nombre.equals("") && !ape1.equals("") && !ape2.equals("") && !tlf.equals("")) {
            // Creamos un contacto y le asignamos valores
            Contacto contacto = new Contacto(nombre, ape1, ape2, tlf);

            ContactoBD contactoBD = new ContactoBD(getApplicationContext());
            contactoBD.openForWrite();

            if (contactoBD.insertarContacto(contacto) != -1) {
                // Alerta: contacto añadido
                mensa = "Contacto añadido";
                setResult(RESULT_ADDED);
                finish();

            } else {
                // Alerta: contacto añadido
                mensa = "No se ha podido añadir el contacto";
            }
            contactoBD.close();

        } else {
            // Alerta: faltan datos
            mensa = "Faltan datos";
        }

        Toast.makeText(getApplicationContext(), mensa, Toast.LENGTH_SHORT).show();
    }
}