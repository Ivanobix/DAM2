package com.example.ej_intent1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected EditText txtUsuario, txtContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsuario = (EditText)findViewById(R.id.txtUsuario);
        txtContrasena = (EditText)findViewById(R.id.txtContrasena);

        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aviso = "";
                if (txtUsuario.getText().toString().equals("") && txtContrasena.getText().toString().equals("")) {
                    aviso = "Rellena el usuario y la contraseña.";
                }
                else if (txtUsuario.getText().toString().equals("")) {
                    aviso = "Rellena el usuario.";
                }
                else if (txtContrasena.getText().toString().equals("")) {
                    aviso = "Rellena la contraseña";
                }
                else {
                    Intent intent = new Intent(MainActivity.this, Activity2.class);
                    aviso = "Todo correcto";
                    intent.putExtra("usuario", txtUsuario.getText().toString());
                    intent.putExtra("contrasena", txtContrasena.getText().toString());
                    startActivity(intent);
                }
                Toast.makeText(MainActivity.this, aviso, Toast.LENGTH_SHORT).show();

            }
        });
    }
}