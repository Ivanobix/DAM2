package com.example.ejemseleccion;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinElementos = (Spinner) findViewById(R.id.spinElementos);
        //Utilizando Array de String
        /*
        String[] items = new String[]{"Teclado", "Ratón", "Monitor"};
        ArrayAdapter<String> adap = new ArrayAdapter<>(this, R.layout.item_layout, items);
        adap.setDropDownViewResource(R.layout.item_dropdown_layout);
         */

        //Utilizando Array-String
        ArrayAdapter<CharSequence> adap = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        adap.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinElementos.setAdapter(adap);

        spinElementos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String texto = "Seleccionado: " + parent.getItemAtPosition(position);
                TextView txtMensaje = (TextView) findViewById(R.id.txtMensaje);
                txtMensaje.setText(texto);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String texto = "";
                TextView txtMensaje = (TextView) findViewById(R.id.txtMensaje);
                txtMensaje.setText(texto);
            }
        });
    }
}