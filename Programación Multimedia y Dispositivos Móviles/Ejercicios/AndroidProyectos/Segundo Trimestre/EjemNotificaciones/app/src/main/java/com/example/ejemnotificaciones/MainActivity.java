package com.example.ejemnotificaciones;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_NOTIFICACION = 101;
    public static final int REQUEST_TLF = 108;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //PendingIntent Notificacion
        Intent nIntent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent nPending = PendingIntent.getActivity(MainActivity.this, REQUEST_NOTIFICACION, nIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        //PendingIntent Acción teléfono
        Intent intentTlf = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:601100518"));
        PendingIntent pendingTlf = PendingIntent.getActivity(MainActivity.this, REQUEST_TLF, intentTlf, PendingIntent.FLAG_CANCEL_CURRENT);

        //Crear Notificación
        NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder nBuilder;
        if (Build.VERSION.SDK_INT < 26) {
            nBuilder = new NotificationCompat.Builder(MainActivity.this);
        } else {
            NotificationChannel nCanal = new NotificationChannel("micanal", "nombreCanal", NotificationManager.IMPORTANCE_DEFAULT);
            nManager.createNotificationChannel(nCanal);
            nBuilder = new NotificationCompat.Builder(MainActivity.this, "micanal");
        }
        nBuilder.setWhen(System.currentTimeMillis());
        nBuilder.setSmallIcon(android.R.drawable.btn_star_big_on);
        nBuilder.setContentTitle("Titulo notificación");
        nBuilder.setContentText("Texto de la notificación");
        nBuilder.setContentIntent(nPending);
        nBuilder.addAction(android.R.drawable.ic_menu_call, "Teléfono", pendingTlf);
        int nId = 1;
        Notification noti = nBuilder.build();
        nManager.notify(nId, noti);
    }
}