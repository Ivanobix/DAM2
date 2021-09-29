package com.example.ejemintentapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAjustes = (Button) findViewById(R.id.btnAjustes);
        btnAjustes.setOnClickListener(this);
        Button btnCalculadora = (Button) findViewById(R.id.btnCalculadora);
        btnCalculadora.setOnClickListener(this);
        Button btnCalendario = (Button) findViewById(R.id.btnCalendario);
        btnCalendario.setOnClickListener(this);
        Button btnCamara = (Button) findViewById(R.id.btnCamara);
        btnCamara.setOnClickListener(this);
        Button btnTelefono = (Button) findViewById(R.id.btnTelefono);
        btnTelefono.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String app = "";
        switch (v.getId()) {
            case R.id.btnAjustes:
                app = "com.android.settings";
                break;
            case R.id.btnCalculadora:
                app = "com.android.calculator2";
                break;
            case R.id.btnCalendario:
                app = "com.android.calendar";
                break;
            case R.id.btnCamara:
                app = "com.android.camera";
                break;
            case R.id.btnTelefono:
                app = "com.android.dialer";
                break;
        }
        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(app);
        if (intent != null) {
            startActivity(intent);
        } else {
            String mensaje = "Aplicación no disponible";
            Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
        }
    }
    
}