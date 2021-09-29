package com.example.garciaprietoivan_20210218;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String[] categoria = new String[]{"Móvil", "Ordenador", "Consola", "Libro", "Deporte"};
    public static final String[] estado = new String[]{"Nuevo", "Usado"};

    private SharedPreferences misDatos;
    private SharedPreferences.Editor editor;
    private Button btnCompra, btnVende;
    private TextView lblBono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        misDatos = getSharedPreferences("preferencias", MODE_PRIVATE);
        editor = misDatos.edit();

        btnCompra = (Button) findViewById(R.id.btnCompra);
        btnVende = (Button) findViewById(R.id.btnVende);
        lblBono = (TextView) findViewById(R.id.lblBono);

        if (!misDatos.getBoolean("bonusOtorgado", false)) {
            lblBono.setVisibility(TextView.VISIBLE);
            editor.putBoolean("bonusOtorgado", true);
            editor.putFloat("saldo", 100);
            editor.apply();
        }

        btnVende.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityVende.class);
            startActivity(intent);
        });

        btnCompra.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityListaCompra.class);
            startActivity(intent);
        });

    }
}