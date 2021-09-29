package com.example.temperaturevio;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationService extends IntentService {

    private Intent intent2;

    public NotificationService(String name) {
        super(name);
    }

    public NotificationService() {
        super("SERVICIO");
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onHandleIntent(Intent intent2) {
        this.intent2 = intent2;
        int tipo = intent2.getIntExtra("tipo", 0);
        String mensaje = obtenerMensaje();
        if (!mensaje.equals("")) {
            if (tipo == AlarmReceiver.TYPE_TEMPERATURAS)
                crearNotificacion(mensaje);
            else if (tipo == AlarmReceiver.TYPE_EXPOSICION)
                crearNotificacion(mensaje);
            else if (tipo == AlarmReceiver.TYPE_HIDRATACION)
                crearNotificacion(mensaje);
        }

    }

    private String obtenerMensaje() {
        String aDevolver = "";
        int tipo = intent2.getIntExtra("tipo", 0);
        float temperaturaActual = intent2.getFloatExtra("temperaturaActual", -275);
        if (tipo == AlarmReceiver.TYPE_TEMPERATURAS) {
            float temperaturaConvertida = intent2.getFloatExtra("temperaturaConvertida", -275);
            if (temperaturaActual > 50)
                aDevolver = "\uD83D\uDC80 \uD83D\uDD25 ¿Estamos en el Infierno?: " + temperaturaConvertida + "º";
            else if (temperaturaActual > 25)
                aDevolver = "\u2600\uFE0F \uD83C\uDF7A ¡Qué calor!: " + temperaturaConvertida + "º";
            else if (temperaturaActual > 15)
                aDevolver = "\uD83D\uDE0E \uD83D\uDC4C ¡Qué bien se está!: " + temperaturaConvertida + "º";
            else if (temperaturaActual > -20)
                aDevolver = "\u2744\uFE0F \u2615 ¡Qué frío!: " + temperaturaConvertida + "º";
            else
                aDevolver = "\uD83D\uDC80 \uD83C\uDF00 ¿Estamos en la Edad de Hielo?: " + temperaturaConvertida + "º";
        } else if (tipo == AlarmReceiver.TYPE_EXPOSICION) {
            float ultimaExposicion = intent2.getFloatExtra("ultimaExposicion", -275);
            if (ultimaExposicion > 41) {
                if (temperaturaActual >= 40)
                    aDevolver = "\u2757 \u2757 ¡Sufres riesgo de sufrir hipertermia!";
            } else if (ultimaExposicion < -30) {
                if (temperaturaActual <= -25)
                    aDevolver = "\u2757 \u2757 ¡Sufres riesgo de sufrir hipotermia!";
            }
        } else if (tipo == AlarmReceiver.TYPE_HIDRATACION) {
            float litrosRecomendados = intent2.getFloatExtra("litrosRecomendados", (float) 2.3);
            if (temperaturaActual >= 38) litrosRecomendados += 0.5;
            aDevolver = "\uD83D\uDCA7 \uD83C\uDF0A Recuerda que debes beber al menos " + litrosRecomendados + "L de agua a lo largo del día.";
        }
        return aDevolver;
    }

    private void crearNotificacion(String mensaje) {
        String NOTIFICATION_CHANNEL_ID = getApplicationContext().getString(R.string.app_name);
        Context context = this.getApplicationContext();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent mIntent = new Intent(this, MainActivity.class);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            final int NOTIFY_ID = 0;
            PendingIntent pendingIntent;
            NotificationCompat.Builder builder;
            NotificationManager notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (notifManager == null) {
                notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            }
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = notifManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID);
            if (mChannel == null) {
                mChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_ID, importance);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notifManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
            mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentTitle(getString(R.string.app_name)).setCategory(Notification.CATEGORY_SERVICE)
                    .setSmallIcon(R.drawable.ic_notificacion)
                    .setContentText(mensaje)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setSound(soundUri)
                    .setContentIntent(pendingIntent);
            Notification notification = builder.build();
            notifManager.notify(NOTIFY_ID, notification);

            startForeground(1, notification);

        } else {
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            Notification notification = new NotificationCompat.Builder(this)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_notificacion)
                    .setSound(soundUri)
                    .setAutoCancel(true)
                    .setContentTitle(getString(R.string.app_name)).setCategory(Notification.CATEGORY_SERVICE)
                    .setContentText(mensaje).build();
            int NOTIFICATION_ID = 1;
            notificationManager.notify(NOTIFICATION_ID, notification);
        }
    }
}