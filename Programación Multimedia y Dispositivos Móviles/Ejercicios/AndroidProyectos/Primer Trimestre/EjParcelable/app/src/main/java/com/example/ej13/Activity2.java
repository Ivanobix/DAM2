package com.example.ej13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    private TextView lblTitulo, lblAutor;
    private ImageView imgCaratula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        lblTitulo = (TextView)findViewById(R.id.lblTituloGrande);
        lblAutor = (TextView)findViewById(R.id.lblAutorGrande);
        imgCaratula = (ImageView)findViewById(R.id.imgCaratula);

        Intent data = getIntent();
        if(data!=null && data.hasExtra("disco")) {
            Disco di = data.getParcelableExtra("disco");
            lblTitulo.setText(di.getTitulo());
            lblAutor.setText(di.getAutor());
            imgCaratula.setImageResource(di.getPortada());
        }
        else {
            Toast.makeText(Activity2.this, "No hay datos.", Toast.LENGTH_SHORT).show();
        }
    }


}