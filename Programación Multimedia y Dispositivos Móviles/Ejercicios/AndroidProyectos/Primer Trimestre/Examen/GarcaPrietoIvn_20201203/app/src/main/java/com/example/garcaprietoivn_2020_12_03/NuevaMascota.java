package com.example.garcaprietoivn_2020_12_03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class NuevaMascota extends AppCompatActivity {

    public static final int RESULT_MASCOTA_ANADIDA = 78;
    private EditText txtNombreNuevo, txtNacimientoNuevo;
    private Spinner spinRazaNuevo, spinEstadoNuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_mascota);
        txtNombreNuevo = findViewById(R.id.txtNombreNuevo);
        txtNacimientoNuevo = findViewById(R.id.txtNacimientoNuevo);
        spinRazaNuevo = findViewById(R.id.spinRazaNuevo);
        spinEstadoNuevo = findViewById(R.id.spinEstadoNuevo);
        ImageButton btnGuardar = findViewById(R.id.btnGuardar);
        ImageButton btnCancelarCreacion = findViewById(R.id.btnCancelarCreacion);


        String[] razas = new String[]{"Alaska", "Bulldog Francés", "Galgo", "Pastor Alemán", "Otro"};
        ArrayAdapter<String> adapRazas = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, razas);
        adapRazas.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinRazaNuevo.setAdapter(adapRazas);

        String[] estados = new String[]{"Adopción", "Acogida", "Adoptado", "Acogido"};
        ArrayAdapter<String> adapEstados = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, estados);
        adapEstados.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinEstadoNuevo.setAdapter(adapEstados);

        btnGuardar.setOnClickListener(v -> {
            String nombre = txtNombreNuevo.getText().toString().trim();
            if (!nombre.equals("")) {
                int nacimiento = Integer.parseInt(txtNacimientoNuevo.getText().toString());
                int fechaActual = Calendar.getInstance().get(Calendar.YEAR);
                if (nacimiento <= fechaActual && (nacimiento > fechaActual - 10)) {
                    String raza = (String) spinRazaNuevo.getSelectedItem();
                    String estado = (String) spinEstadoNuevo.getSelectedItem();
                    Mascota mascota = new Mascota(nombre, raza, estado, nacimiento, -1);
                    Intent intent = new Intent(NuevaMascota.this, Lista.class);
                    intent.putExtra("mascota", mascota);
                    setResult(RESULT_MASCOTA_ANADIDA, intent);
                    finish();
                } else {
                    Toast.makeText(NuevaMascota.this, "Un perro no puede tener más de 10 años.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(NuevaMascota.this, "Debes introducir un nombre.", Toast.LENGTH_SHORT).show();
            }

        });

        btnCancelarCreacion.setOnClickListener(v -> finish());
    }
}