package com.example.garcaprietoivn_2020_12_03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int USUARIO_LOGUEADO = 1;
    private EditText txtUsuario, txtContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Mascota> mascotas = new ArrayList<>();
        mascotas.add(new Mascota("Simba", "Alaska", "Adopción", 2002, R.drawable.simba));
        mascotas.add(new Mascota("Sultán", "Bulldog Francés", "Acogida", 2002, -1));
        mascotas.add(new Mascota("Niebla", "Galgo", "Adoptado", 2002, -1));
        mascotas.add(new Mascota("Ringo", "Galgo", "Acogido", 2002, R.drawable.ringo));
        mascotas.add(new Mascota("Estela", "Alaska", "Adopción", 2002, -1));

        int numero = (int) (Math.random() * mascotas.size());
        Mascota mascota = mascotas.get(numero);
        ImageView imgPortada = findViewById(R.id.imgPortada);
        imgPortada.setImageResource(mascota.getIdFoto());
        TextView lblNombreEstado = findViewById(R.id.lblNombreEstado);
        String nombreEstado = mascota.getNombre() + ": " + mascota.getEstado();
        lblNombreEstado.setText(nombreEstado);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtContrasena = findViewById(R.id.txtContrasena);
        ImageButton btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            String usuario = txtUsuario.getText().toString();
            String contrasena = txtContrasena.getText().toString();
            if (usuario.equals("user") && contrasena.equals("user")) {
                Intent intent = new Intent(MainActivity.this, Lista.class);
                intent.putExtra("usuario", "normal");
                Toast.makeText(MainActivity.this, "Has iniciado sesión como usuario.", Toast.LENGTH_SHORT).show();
                startActivityForResult(intent, USUARIO_LOGUEADO);
            } else if (usuario.equals("admin") && contrasena.equals("admin")) {
                Intent intent = new Intent(MainActivity.this, Lista.class);
                intent.putExtra("usuario", "admin");
                Toast.makeText(MainActivity.this, "Has iniciado sesión como administrador.", Toast.LENGTH_SHORT).show();
                startActivityForResult(intent, USUARIO_LOGUEADO);
            } else {
                Toast.makeText(MainActivity.this, "Los datos introducidos no son correctos.", Toast.LENGTH_SHORT).show();
            }
        });
        imgPortada.setOnClickListener(v -> {
            int numero2 = (int) (Math.random() * mascotas.size());
            Mascota mascotaNueva = mascotas.get(numero2);
            imgPortada.setImageResource(mascotaNueva.getIdFoto());
            String nombreEstado1 = mascotaNueva.getNombre() + ": " + mascotaNueva.getEstado();
            lblNombreEstado.setText(nombreEstado1);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        txtUsuario.setText("");
        txtContrasena.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == USUARIO_LOGUEADO) {
            if (resultCode == Lista.RESULT_CANCELED) {
                Toast.makeText(MainActivity.this, "Has cerrado sesión.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}