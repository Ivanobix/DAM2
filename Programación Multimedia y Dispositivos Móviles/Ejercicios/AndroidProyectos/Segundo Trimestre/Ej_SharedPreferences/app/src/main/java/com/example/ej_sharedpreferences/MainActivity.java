package com.example.ej_sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    protected TextView lblTitulo;
    protected ConstraintLayout clMain;
    protected SharedPreferences misDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lblTitulo = (TextView) findViewById(R.id.lblTitulo);
        clMain = (ConstraintLayout) findViewById(R.id.clMain);

        establecerPreferencias();
    }

    @Override
    protected void onResume() {
        super.onResume();
        establecerPreferencias();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, AjustesActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void establecerPreferencias() {
        misDatos = getSharedPreferences("preferencias", MODE_PRIVATE);
        clMain.setBackgroundColor(misDatos.getInt("colorFondo", Color.WHITE));
        lblTitulo.setTextColor(misDatos.getInt("colorTexto", Color.BLACK));
        lblTitulo.setText(misDatos.getString("textoTitulo", "SHARED PREFERENCES"));
    }
}