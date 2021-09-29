package com.example.ej12;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    protected Spinner spinFondo;
    protected Spinner spinLetra;
    protected EditText txtTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinFondo = (Spinner) findViewById(R.id.spinFondo);
        spinLetra = (Spinner) findViewById(R.id.spinLetra);
        txtTexto = (EditText) findViewById(R.id.txtTexto);
        ArrayAdapter<CharSequence> adap = ArrayAdapter.createFromResource(this, R.array.colores, android.R.layout.simple_spinner_item);
        adap.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinFondo.setAdapter(adap);
        spinLetra.setAdapter(adap);
        spinFondo.setSelection(0);
        spinLetra.setSelection(1);
        spinFondo.setOnItemSelectedListener(this);
        spinLetra.setOnItemSelectedListener(this);
    }

    protected int sacarColor(AdapterView<?> parent, int position) {
        String colorMarcado = (String) parent.getItemAtPosition(position);
        int color = 0;
        switch (colorMarcado) {
            case "Blanco":
                color = getColor(R.color.blanco);
                break;
            case "Negro":
                color = getColor(R.color.negro);
                break;
            case "Rojo":
                color = getColor(R.color.rojo);
                break;
            case "Azul":
                color = getColor(R.color.azul);
                break;
            case "Verde":
                color = getColor(R.color.verde);
                break;
        }
        return color;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinFondo:
                txtTexto.setBackgroundColor(sacarColor(parent, position));

                break;
            case R.id.spinLetra:
                txtTexto.setTextColor(sacarColor(parent, position));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}