package com.example.ej_broadcast2;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    protected Receptor receptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Receptor asociado a la aplicación
        IntentFilter filtro = new IntentFilter();
        filtro.addAction("android.intent.action.BATTERY_LOW");
        filtro.addAction("android.intent.action.AIRPLANE_MODE");
        Receptor receptor = new Receptor();
        getApplicationContext().registerReceiver(receptor, filtro);

        Button btnIr = (Button) findViewById(R.id.btnIr);
        btnIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);
            }
        });
    }

}