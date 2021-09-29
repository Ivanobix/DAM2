package com.example.temperaturevio;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    public static final int RESULT_APLICAR = 1375;
    public static final int RESULT_CANCELAR = 3452;
    private SwitchCompat swAvisosTemperatura, swAvisosExposicion;
    private SwitchCompat swPersonalizacion, swHidratacion;
    private RadioButton rbCelsius, rbKelvin, rbFahrenheit;
    private EditText txtAltura, txtPeso, txtEdad;
    private Button btnAplicar, btnCancelar;
    private SharedPreferences misDatos;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initComponents();
        cargarConfiguracionesActuales();
        initHandlers();
    }

    private void initComponents() {
        swAvisosTemperatura = (SwitchCompat) findViewById(R.id.swAvisosTemperatura);
        swAvisosExposicion = (SwitchCompat) findViewById(R.id.swAvisosExposicion);
        swPersonalizacion = (SwitchCompat) findViewById(R.id.swPersonalizacion);
        swHidratacion = (SwitchCompat) findViewById(R.id.swHidratacion);

        txtAltura = (EditText) findViewById(R.id.txtAltura);
        txtPeso = (EditText) findViewById(R.id.txtPeso);
        txtEdad = (EditText) findViewById(R.id.txtEdad);

        btnAplicar = (Button) findViewById(R.id.btnAplicar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        rbCelsius = (RadioButton) findViewById(R.id.rbCelsius);
        rbKelvin = (RadioButton) findViewById(R.id.rbKelvin);
        rbFahrenheit = (RadioButton) findViewById(R.id.rbFahrenheit);

        misDatos = getSharedPreferences("preferencias", MODE_PRIVATE);
        editor = misDatos.edit();
    }

    private void initHandlers() {
        btnAplicar.setOnClickListener(v -> guardarPreferencias());
        btnCancelar.setOnClickListener(v -> salir());
    }

    private void cargarConfiguracionesActuales() {
        if (misDatos.getString("escala", "celsius").equals("celsius")) rbCelsius.setChecked(true);
        else if (misDatos.getString("escala", "celsius").equals("kelvin"))
            rbKelvin.setChecked(true);
        else rbFahrenheit.setChecked(true);

        if (misDatos.getBoolean("avisosTemperatura", true)) swAvisosTemperatura.setChecked(true);
        if (misDatos.getBoolean("avisosExposicion", true)) swAvisosExposicion.setChecked(true);
        if (misDatos.getBoolean("hidratacion", true)) swHidratacion.setChecked(true);

        if (misDatos.getBoolean("personalizacion", true)) {
            swPersonalizacion.setChecked(true);
            txtAltura.setText(misDatos.getString("altura", ""));
            txtPeso.setText(misDatos.getString("peso", ""));
            txtEdad.setText(misDatos.getString("edad", ""));
        }
    }

    private void guardarPreferencias() {
        if (rbCelsius.isChecked()) editor.putString("escala", "celsius");
        else if (rbKelvin.isChecked()) editor.putString("escala", "kelvin");
        else editor.putString("escala", "fahrenheit");

        editor.putBoolean("avisosTemperatura", swAvisosTemperatura.isChecked());
        editor.putBoolean("avisosExposicion", swAvisosExposicion.isChecked());
        editor.putBoolean("hidratacion", swHidratacion.isChecked());

        editor.putBoolean("personalizacion", swPersonalizacion.isChecked());
        if (swPersonalizacion.isChecked()) {
            String altura = txtAltura.getText().toString();
            if (!altura.equals("")) {
                String peso = txtPeso.getText().toString();
                if (!peso.equals("")) {
                    String edad = txtEdad.getText().toString();
                    if (!edad.equals("")) {
                        editor.putString("altura", altura);
                        editor.putString("peso", peso);
                        editor.putString("edad", edad);
                        editor.putFloat("litrosRecomendados", (float) (Math.round((Float.parseFloat(peso) / 35) * 100) / 100d));
                    } else {
                        Toast.makeText(SettingsActivity.this, "Introduce tu edad.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SettingsActivity.this, "Introduce tu peso.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(SettingsActivity.this, "Introduce tu altura.", Toast.LENGTH_SHORT).show();
            }

        } else {
            editor.putString("altura", "");
            editor.putString("peso", "");
            editor.putString("edad", "");
            editor.putFloat("litrosRecomendados", (float) 2.3);
        }
        editor.apply();

        setResult(RESULT_APLICAR);
        finish();
    }

    private void salir() {
        setResult(RESULT_CANCELAR);
        finish();
    }
}