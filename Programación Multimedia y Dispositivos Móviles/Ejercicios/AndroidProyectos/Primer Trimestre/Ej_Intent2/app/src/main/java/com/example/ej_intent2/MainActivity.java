package com.example.ej_intent2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    protected static int REQUEST_ACTIVITY2 = 123;
    protected TextView lblBienvenida;
    protected Button btnToggleSesion;
    protected String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = "Invitado";
        lblBienvenida = (TextView) findViewById(R.id.lblBienvenida);
        btnToggleSesion = (Button) findViewById(R.id.btnToggleSesion);
        btnToggleSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                if (usuario.equals("Invitado")) {
                    startActivityForResult(intent, REQUEST_ACTIVITY2);
                } else {
                    usuario = "Invitado";
                    btnToggleSesion.setText("INICIAR SESIÓN");
                    lblBienvenida.setText("Bienvenido, Invitado.");
                    Toast.makeText(MainActivity.this, "Sesión Cerrada", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ACTIVITY2) {
            if (resultCode == RESULT_OK) {
                if (data.hasExtra("usuario")) {
                    usuario = data.getStringExtra("usuario");
                    String bienvenida = "Bienvenido, " + usuario;
                    lblBienvenida.setText(bienvenida);
                    btnToggleSesion.setText("CERRAR SESIÓN");
                    Toast.makeText(MainActivity.this, "Sesión Iniciada", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Inicio de Sesión Cancelado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}