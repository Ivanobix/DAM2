package com.example.ejembroadcast2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.Toast;

public class Receptor extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int duracion = Toast.LENGTH_SHORT;
        String mensa = "";
        switch (intent.getAction()) {
            case "android.intent.action.BATTERY_LOW":
                // Este código permite obtener el nivel de batería en el momento en que cambia el estado
                IntentFilter filtro = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                Intent estadoBateria = context.registerReceiver(null, filtro);
                int nivel = estadoBateria.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int escala = estadoBateria.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int valor = (int) (nivel / (float) escala * 100);
                mensa = "Nivel de batería bajo (" + valor + "%)";
                Toast.makeText(context, mensa, duracion).show();
                break;
            case "android.intent.action.AIRPLANE_MODE":
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    if (extras.getBoolean("state")) {
                        mensa = "Modo Avión activado";
                    } else {
                        mensa = "Modo Avión desactivado";
                    }
                }
                Toast.makeText(context, mensa, duracion).show();
                break;
        }
    }
}
