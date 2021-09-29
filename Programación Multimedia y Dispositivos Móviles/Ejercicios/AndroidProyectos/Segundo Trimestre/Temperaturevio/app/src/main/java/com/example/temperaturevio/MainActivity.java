package com.example.temperaturevio;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    public static final boolean DEBUG = true;

    private final static int REQUEST_PREFERENCIAS = 345;
    private Button btnIniciar, btnDetener, btnConfiguraciones;
    private TextView lblTemperatura;
    private SharedPreferences misDatos;
    private SharedPreferences.Editor editor;
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        initComponents();
        initHandlers();

    }

    private void initComponents() {
        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnDetener = (Button) findViewById(R.id.btnDetener);
        btnConfiguraciones = (Button) findViewById(R.id.btnConfiguraciones);

        lblTemperatura = (TextView) findViewById(R.id.lblTemperatura);

        misDatos = getSharedPreferences("preferencias", MODE_PRIVATE);
        editor = misDatos.edit();

        if (misDatos.getBoolean("iniciado", false)) btnIniciar.setEnabled(false);
        else btnDetener.setEnabled(false);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (sensor == null)
            Toast.makeText(this.getApplicationContext(), "Este dispositivo no tiene sensor de temperatura ambiental.", Toast.LENGTH_SHORT).show();
        else
            iniciarSensorDeTemperatura();

    }

    private void initHandlers() {
        btnIniciar.setOnClickListener(v -> iniciar());
        btnDetener.setOnClickListener(v -> detener());
        btnConfiguraciones.setOnClickListener(v -> irAConfiguraciones());
    }

    private void irAConfiguraciones() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivityForResult(intent, REQUEST_PREFERENCIAS);
    }

    private void iniciar() {
        iniciarAlarmas();
        iniciarSensorDeTemperatura();

        editor.putBoolean("iniciado", true);
        editor.apply();

        btnIniciar.setEnabled(false);
        btnDetener.setEnabled(true);
    }

    private void detener() {
        detenerSensorDeTemperatura();

        editor.putBoolean("iniciado", false);
        editor.apply();

        btnIniciar.setEnabled(true);
        btnDetener.setEnabled(false);
    }

    private void iniciarAlarmas() {
        Calendar today = Calendar.getInstance();
        crearAlarmasTemperaturas(today);
        crearAlarmasExposicion(today);
        crearAlarmaHidratacion(today);

    }

    private void crearAlarmasTemperaturas(Calendar today) {
        if (!DEBUG) {
            today.set(Calendar.HOUR_OF_DAY, today.get(Calendar.HOUR_OF_DAY) + 1);
            today.set(Calendar.MINUTE, 0);
        } else {
            int hora = today.get(Calendar.HOUR_OF_DAY);
            int minuto = today.get(Calendar.MINUTE) + 1;
            if (minuto >= 60) {
                minuto -= 60;
                hora++;
            }
            today.set(Calendar.HOUR_OF_DAY, hora);
            today.set(Calendar.MINUTE, minuto);
        }
        today.set(Calendar.SECOND, 0);

        crearAlarma(AlarmReceiver.TYPE_TEMPERATURAS, today.getTimeInMillis());
    }

    private void crearAlarmasExposicion(Calendar today) {
        if (!DEBUG) {
            today.set(Calendar.HOUR_OF_DAY, today.get(Calendar.HOUR_OF_DAY) + 3);
            today.set(Calendar.MINUTE, 0);
        } else {
            int hora = today.get(Calendar.HOUR_OF_DAY);
            int minuto = today.get(Calendar.MINUTE) + 1;
            if (minuto >= 60) {
                minuto -= 60;
                hora++;
            }
            today.set(Calendar.HOUR_OF_DAY, hora);
            today.set(Calendar.MINUTE, minuto);
        }
        today.set(Calendar.SECOND, 20);

        crearAlarma(AlarmReceiver.TYPE_EXPOSICION, today.getTimeInMillis());
    }

    private void crearAlarmaHidratacion(Calendar today) {
        if (!DEBUG) {
            today.set(Calendar.HOUR_OF_DAY, today.get(Calendar.HOUR_OF_DAY) + 2);
            today.set(Calendar.MINUTE, 0);
        } else {
            int hora = today.get(Calendar.HOUR_OF_DAY);
            int minuto = today.get(Calendar.MINUTE) + 1;
            if (minuto >= 60) {
                minuto -= 60;
                hora++;
            }
            today.set(Calendar.HOUR_OF_DAY, hora);
            today.set(Calendar.MINUTE, minuto);
        }
        today.set(Calendar.SECOND, 40);

        crearAlarma(AlarmReceiver.TYPE_HIDRATACION, today.getTimeInMillis());
    }

    private void crearAlarma(int id, long timestamp) {
        AlarmManager alarmManager = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        alarmIntent.setData(Uri.parse("custom://" + id));
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, id, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timestamp, pendingIntent);
    }

    private void iniciarSensorDeTemperatura() {
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void detenerSensorDeTemperatura() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float temperaturaActual = event.values[0];
        editor.putFloat("temperaturaActual", temperaturaActual);

        float temperaturaConvertida;
        if (misDatos.getString("escala", "celsius").equals("celsius")) {
            temperaturaConvertida = temperaturaActual;
        } else if (misDatos.getString("escala", "celsius").equals("kelvin")) {
            temperaturaConvertida = (float) (temperaturaActual + 273.15);
        } else {
            temperaturaConvertida = (temperaturaActual * 9 / 5) + 32;
        }
        editor.putFloat("temperaturaConvertida", temperaturaConvertida);

        lblTemperatura.setText(String.valueOf(temperaturaConvertida));
        editor.apply();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}