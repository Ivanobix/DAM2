package com.example.temperaturevio;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import androidx.core.content.ContextCompat;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class AlarmReceiver extends BroadcastReceiver {

    public static final int TYPE_TEMPERATURAS = 998810;
    public static final int TYPE_EXPOSICION = 998820;
    public static final int TYPE_HIDRATACION = 998830;
    private Context contexto;

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences preferences = context.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        contexto = context;
        if (preferences.getBoolean("iniciado", false)) {
            String data = intent.getDataString();
            boolean alarmaActivada = false;
            int tipo = 0;
            if (data != null && data.equals("custom://" + TYPE_TEMPERATURAS)) {
                if (preferences.getBoolean("avisosTemperatura", true)) alarmaActivada = true;
                tipo = TYPE_TEMPERATURAS;
                reActivarAlarma(TYPE_TEMPERATURAS);
            } else if (data != null && data.equals("custom://" + TYPE_EXPOSICION)) {
                if (preferences.getBoolean("avisosExposicion", true)) alarmaActivada = true;
                tipo = TYPE_EXPOSICION;
                reActivarAlarma(TYPE_EXPOSICION);
            } else if (data != null && data.equals("custom://" + TYPE_HIDRATACION)) {
                if (preferences.getBoolean("hidratacion", true)) alarmaActivada = true;
                tipo = TYPE_HIDRATACION;
                reActivarAlarma(TYPE_HIDRATACION);
            }

            if (alarmaActivada) {
                Intent service1 = new Intent(context, NotificationService.class);
                service1.setData((Uri.parse("custom://" + System.currentTimeMillis())));
                service1.putExtra("tipo", tipo);

                float temperaturaActual = preferences.getFloat("temperaturaActual", -275);
                service1.putExtra("temperaturaActual", temperaturaActual);

                if (tipo == TYPE_TEMPERATURAS) {
                    float temperaturaConvertida = preferences.getFloat("temperaturaConvertida", -275);
                    service1.putExtra("temperaturaConvertida", temperaturaConvertida);
                } else if (tipo == TYPE_EXPOSICION) {
                    float ultimaExposicion = preferences.getFloat("ultimaExposicion", -275);
                    service1.putExtra("ultimaExposicion", ultimaExposicion);
                    editor.putFloat("ultimaExposicion", temperaturaActual);
                    editor.apply();
                } else {
                    float litrosRecomendados = preferences.getFloat("litrosRecomendados", (float) 2.3);
                    service1.putExtra("litrosRecomendados", litrosRecomendados);
                }
                ContextCompat.startForegroundService(context, service1);
            }
        }
    }

    private void reActivarAlarma(int tipo) {
        Calendar today = Calendar.getInstance();
        int dia = today.get(Calendar.DAY_OF_YEAR);
        int hora = today.get(Calendar.HOUR);
        int minuto = today.get(Calendar.MINUTE);
        int segundo = 0;
        if (!MainActivity.DEBUG) {
            if (tipo == TYPE_TEMPERATURAS) {
                hora += 1;
            } else if (tipo == TYPE_EXPOSICION) {
                hora += 4;
                segundo = 20;
            } else {
                hora += 6;
                segundo = 40;
            }
        } else {
            if (tipo == TYPE_TEMPERATURAS) {
                minuto += 1;
            } else if (tipo == TYPE_EXPOSICION) {
                minuto += 2;
                segundo = 20;
            } else {
                minuto += 3;
                segundo = 40;
            }
        }

        while (minuto > 60) {
            minuto -= 60;
            hora++;
        }
        while (hora > 24) {
            hora -= 24;
            dia++;
        }

        today.set(Calendar.DAY_OF_YEAR, dia);
        today.set(Calendar.HOUR, hora);
        today.set(Calendar.MINUTE, minuto);
        today.set(Calendar.SECOND, segundo);

        AlarmManager alarmManager = (AlarmManager) contexto.getSystemService(ALARM_SERVICE);
        Intent alarmIntent = new Intent(contexto, AlarmReceiver.class);
        alarmIntent.setData(Uri.parse("custom://" + tipo));
        PendingIntent pendingIntent = PendingIntent.getBroadcast(contexto, tipo, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, today.getTimeInMillis(), pendingIntent);
    }
}