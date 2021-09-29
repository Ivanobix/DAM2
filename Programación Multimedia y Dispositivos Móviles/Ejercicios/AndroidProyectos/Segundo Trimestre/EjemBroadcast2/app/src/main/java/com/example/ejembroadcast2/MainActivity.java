package com.example.ejembroadcast2;

import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filtro = new IntentFilter();
        filtro.addAction("android.intent.action.BATTERY_LOW");
        filtro.addAction("android.intent.action.AIRPLANE_MODE");
        Receptor receptor = new Receptor();
        getApplicationContext().registerReceiver(receptor, filtro);
    }
}