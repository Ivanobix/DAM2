package com.example.ej_firebase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {

    private DatabaseReference mDB;
    private DatabaseReference.CompletionListener cl;
    private Button btnInsertar;
    private EditText txtNombre, txtApellido, txtTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initComponents();
        initHandlers();
    }

    private void initComponents() {
        btnInsertar = (Button) findViewById(R.id.btnInsertar);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);

        mDB = FirebaseDatabase.getInstance().getReference("/contactos").push();
    }

    private void initHandlers() {
        cl = (dbError, dbRef) -> {
            if (dbError != null) {
                // Error de escritura
                Toast.makeText(AddActivity.this, dbError.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                // Escritura correcta
                Toast.makeText(AddActivity.this, "Contacto almacenado.", Toast.LENGTH_SHORT).show();
            }
        };

        btnInsertar.setOnClickListener(v -> {
            String nombre = txtNombre.getText().toString().trim();
            if (!nombre.equals("")) {
                String apellido = txtApellido.getText().toString().trim();
                if (!apellido.equals("")) {
                    try {
                        long telefono = Long.parseLong(txtTelefono.getText().toString().trim());
                        String id = mDB.getKey();
                        mDB.setValue(new Contacto(id, nombre, apellido, telefono), cl);
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(AddActivity.this, "El telefono no es correcto.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddActivity.this, "El apellido no es correcto.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(AddActivity.this, "El nombre no es correcto.", Toast.LENGTH_SHORT).show();
            }

        });

    }
}