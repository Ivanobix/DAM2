package com.example.ejem_broadcaster;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.AIRPLANE_MODE")) {
            String mensa = "";
            int duracion = Toast.LENGTH_LONG;
            Bundle extras = intent.getExtras();
            if (extras != null) {
                if (extras.getBoolean("state")) {
                    mensa = "Modo Avión activado";
                } else {
                    mensa = "Modo Avión desactivado";
                }
            }
            Toast.makeText(context, mensa, duracion).show();
        }
    }
}