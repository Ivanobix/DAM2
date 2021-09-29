package com.example.ejemintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //protected EditText txtNombre, txtEdad;
    protected static int REQUEST_ACTIVITY2 = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //txtNombre = (EditText)findViewById(R.id.txtNombre);
        //txtEdad = (EditText)findViewById(R.id.txtEdad);

        Button btnUno = (Button) findViewById(R.id.btnUno);
        btnUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                /*
                Toast.makeText(MainActivity.this, "Iniciando Actividad 2", Toast.LENGTH_SHORT).show();
                String nombre = txtNombre.getText().toString();
                int edad = Integer.parseInt(txtEdad.getText().toString());
                intent.putExtra("nombre", nombre);
                intent.putExtra("edad", edad);
                startActivity(intent);
                 */
                startActivityForResult(intent, REQUEST_ACTIVITY2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ACTIVITY2) {
            String mensaje = "";
            if (resultCode == RESULT_OK) {
                String nombre = data.getStringExtra("nombre");
                mensaje = "Nombre: " + nombre;
            } else {
                mensaje = "Cancelado";
            }
            Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
        }
    }
}