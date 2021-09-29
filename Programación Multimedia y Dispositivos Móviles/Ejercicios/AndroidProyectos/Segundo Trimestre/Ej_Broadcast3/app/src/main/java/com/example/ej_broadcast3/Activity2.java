package com.example.ej_broadcast3;

import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

    protected Receptor receptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        IntentFilter filtro = new IntentFilter();
        filtro.addAction("android.intent.action.BATTERY_LOW");
        filtro.addAction("android.intent.action.AIRPLANE_MODE");
        receptor = new Receptor();
        registerReceiver(receptor, filtro);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receptor);
    }
}