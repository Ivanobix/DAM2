package com.example.ej_sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorChangedListener;

public class AjustesActivity extends AppCompatActivity {

    protected EditText txtTitulo;
    protected TextView lblTituloVistaPrevia;
    protected ConstraintLayout clVistaprevia;
    protected ColorPickerView cpColorDeTexto, cpColorDeFondo;
    protected CheckBox cbPreferencias;
    protected SharedPreferences misDatos;
    protected SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        lblTituloVistaPrevia = (TextView) findViewById(R.id.lblTituloVistaPrevia);
        clVistaprevia = (ConstraintLayout) findViewById(R.id.clVistaPrevia);
        cpColorDeFondo = (ColorPickerView) findViewById(R.id.cpColorFondo);
        cpColorDeTexto = (ColorPickerView) findViewById(R.id.cpColorTexto);
        cbPreferencias = (CheckBox) findViewById(R.id.cbPreferencias);

        misDatos = getSharedPreferences("preferencias", MODE_PRIVATE);
        editor = misDatos.edit();

        if (misDatos.getBoolean("usarPreferencias", false)) {
            cbPreferencias.setChecked(true);
        } else {
            editor.clear();
        }

        cpColorDeTexto.addOnColorChangedListener(new OnColorChangedListener() {
            @Override
            public void onColorChanged(int selectedColor) {
                editor.putInt("colorTexto", selectedColor);
                lblTituloVistaPrevia.setTextColor(selectedColor);
                aplicarSiMarcado();
            }
        });

        cpColorDeFondo.addOnColorChangedListener(new OnColorChangedListener() {
            @Override
            public void onColorChanged(int selectedColor) {
                editor.putInt("colorFondo", selectedColor);
                clVistaprevia.setBackgroundColor(selectedColor);
                aplicarSiMarcado();
            }
        });

        txtTitulo.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    String titulo = txtTitulo.getText().toString().trim();
                    editor.putString("textoTitulo", titulo);
                    lblTituloVistaPrevia.setText(titulo);
                    aplicarSiMarcado();
                    return true;
                } else {
                    return false;
                }

            }
        });

        cbPreferencias.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putBoolean("usarPreferencias", true);
                } else {
                    editor.clear();
                    editor.putBoolean("usarPreferencias", false);
                }
                editor.apply();
            }
        });

    }

    private void aplicarSiMarcado() {
        if (cbPreferencias.isChecked()) {
            editor.apply();
        }
    }

}