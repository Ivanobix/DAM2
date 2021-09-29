package com.example.ej_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditActivity extends AppCompatActivity {

    private DatabaseReference mDB;
    private DatabaseReference.CompletionListener cl;
    private Button btnModificar, btnCancelar, btnEliminar;
    private EditText txtNombre, txtApellido, txtTelefono;
    private Contacto contacto;
    private boolean modificando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent data = getIntent();
        if (data != null && data.hasExtra("contacto")) {
            contacto = data.getParcelableExtra("contacto");
            initComponents();
            initHandlers();
            cargarDatos();
        } else {
            contacto = null;
            Toast.makeText(EditActivity.this, "No hay datos.", Toast.LENGTH_SHORT).show();
        }

    }

    private void initComponents() {
        btnModificar = (Button) findViewById(R.id.btnModificar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar2);

        txtNombre = (EditText) findViewById(R.id.txtNombre2);
        txtApellido = (EditText) findViewById(R.id.txtApellido2);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono2);

        mDB = FirebaseDatabase.getInstance().getReference("/contactos/" + contacto.getId());
        modificando = false;
    }

    private void initHandlers() {
        cl = (dbError, dbRef) -> {
            if (dbError != null) {
                // Error de escritura
                Toast.makeText(EditActivity.this, dbError.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                // Escritura correcta
                Toast.makeText(EditActivity.this, "Se ha efectuado el cambio correctamente.", Toast.LENGTH_SHORT).show();
            }
        };

        btnModificar.setOnClickListener(v -> {
            if (!modificando) {
                modificando = true;
                txtNombre.setEnabled(true);
                txtApellido.setEnabled(true);
                txtTelefono.setEnabled(true);
            } else {
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
                            Toast.makeText(EditActivity.this, "El telefono no es correcto.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(EditActivity.this, "El apellido no es correcto.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditActivity.this, "El nombre no es correcto.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelar.setOnClickListener(v -> finish());

        btnEliminar.setOnClickListener(v -> {
            mDB.removeValue(cl);
            finish();
        });

    }

    private void cargarDatos() {
        txtNombre.setText(contacto.getNombre());
        txtApellido.setText(contacto.getApellido());
        txtTelefono.setText(String.valueOf(contacto.getTelefono()));
    }
}