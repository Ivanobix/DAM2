package com.example.ej_broadcast4;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    protected Receptor receptor;

    //Receptor activo mientras la actividad es visible
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIr = (Button) findViewById(R.id.btnIr);
        btnIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filtro = new IntentFilter();
        filtro.addAction("android.intent.action.BATTERY_LOW");
        filtro.addAction("android.intent.action.AIRPLANE_MODE");
        receptor = new Receptor();
        registerReceiver(receptor, filtro);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receptor);
    }

}