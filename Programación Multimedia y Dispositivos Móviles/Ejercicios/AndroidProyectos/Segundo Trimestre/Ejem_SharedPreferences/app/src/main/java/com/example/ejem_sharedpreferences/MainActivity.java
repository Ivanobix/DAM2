package com.example.ejem_sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNombre, txtApellido1, txtApellido2;
    private Button btnRecuperar, btnEliminar, btnGuardar;
    private TextView lblResultado;
    private SharedPreferences misDatos;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido1 = (EditText) findViewById(R.id.txtApellido1);
        txtApellido2 = (EditText) findViewById(R.id.txtApellido2);

        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnRecuperar = (Button) findViewById(R.id.btnRecuperar);

        lblResultado = (TextView) findViewById(R.id.lblResultado);

        misDatos = getPreferences(MODE_PRIVATE);
        editor = misDatos.edit();
        SharedPreferences.OnSharedPreferenceChangeListener controlDeCambios = (sharedPreferences, key) -> {
            if (key.equals("hayDatos")) {
                hayDatos();
            }
        };
        misDatos.registerOnSharedPreferenceChangeListener(controlDeCambios);

        initHandlers();
        hayDatos();

    }

    private void initHandlers() {
        btnGuardar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnRecuperar.setOnClickListener(this);
    }

    private boolean hayDatos() {
        boolean hayDatos = misDatos.getBoolean("hayDatos", false);
        if (hayDatos) {
            ocultarBotones(false);
            return true;
        } else {
            ocultarBotones(true);
            return false;
        }
    }

    private void guardarDatos() {
        String nombre = txtNombre.getText().toString().trim();
        if (!nombre.equals("")) {
            String apellido1 = txtApellido1.getText().toString().trim();
            if (!apellido1.equals("")) {
                String apellido2 = txtApellido2.getText().toString().trim();
                if (!apellido2.equals("")) {
                    editor.putBoolean("hayDatos", true);
                    editor.putString("nombre", nombre);
                    editor.putString("apellido1", apellido1);
                    editor.putString("apellido2", apellido2);
                    editor.apply();
                    vaciarCampos();
                    ocultarBotones(false);
                    lblResultado.setText("Datos almacenados");
                } else {
                    lblResultado.setText("Falta el apellido 2");
                }
            } else {
                lblResultado.setText("Falta el apellido 1");
            }
        } else {
            lblResultado.setText("Falta el nombre");
        }
    }

    private void eliminarDatos() {
        if (hayDatos()) {
            editor.remove("hayDatos");
            editor.remove("nombre");
            editor.remove("apellido1");
            editor.remove("apellido2");
            editor.apply();
            vaciarCampos();
            ocultarBotones(true);
            lblResultado.setText("Datos eliminados");
        } else {
            lblResultado.setText("No hay datos almacenados");
        }

    }

    private void recuperarDatos() {
        if (hayDatos()) {
            txtNombre.setText(misDatos.getString("nombre", ""));
            txtApellido1.setText(misDatos.getString("apellido1", ""));
            txtApellido2.setText(misDatos.getString("apellido2", ""));
            lblResultado.setText("Datos leídos");

        } else {
            lblResultado.setText("No hay datos almacenados");
        }
    }

    private void ocultarBotones(boolean invisible) {
        if (invisible) {
            btnEliminar.setVisibility(View.INVISIBLE);
            btnRecuperar.setVisibility(View.INVISIBLE);
        } else {
            btnEliminar.setVisibility(View.VISIBLE);
            btnRecuperar.setVisibility(View.VISIBLE);
        }

    }

    private void vaciarCampos() {
        txtNombre.setText("");
        txtApellido1.setText("");
        txtApellido2.setText("");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGuardar:
                guardarDatos();
                break;
            case R.id.btnEliminar:
                eliminarDatos();
                break;
            case R.id.btnRecuperar:
                recuperarDatos();
                break;
        }
    }
}