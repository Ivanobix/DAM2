package com.example.ejemrecyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected ArrayList<Titular> titulares;
    protected AdaptadorTitular at;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //creamos los datos para mostrarlos en la lista
        titulares = new ArrayList<Titular>();
        for (int i = 1; i <= 50; i++) {
            String titu = "Titular" + i;
            titulares.add(new Titular(titu, "Prueba"));
        }

        titulares.add(new Titular("Vacuna COVID", "Proximamente"));
        titulares.add(new Titular("LLega la Navidad", "Aun con incertidumbre"));
        titulares.add(new Titular("Biden gana las elecciones", "Trump no reconoce el resultado"));

        //Obenemos referencia a Recycler View y le asociamos un adaptador
        RecyclerView recView = (RecyclerView) findViewById(R.id.recView);
        //el constructor de adaptador espera un array

        at = new AdaptadorTitular(titulares);

        //Con esto se mejora mucho el rendimiento
        recView.setHasFixedSize(true);
        //recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //con esta linea se muestra horizontal
        recView.setLayoutManager(new GridLayoutManager(this, 3));
        recView.setAdapter(at);


        //SEPARADORES: coloca una linea entre los elementos.
        recView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

        //ANIMACIONES:
        recView.setItemAnimator(new DefaultItemAnimator());


        //Boton add
        Button botAdd = (Button) findViewById(R.id.botAdd);
        botAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titulares.add(0, new Titular("Nuevo Titulo", "Nuevo Subtitulo"));
                //notificar cambios al adaptador

                //notifyItemInserted(position) se cambia para que las animaciones cambian, este tiene una animacion predeterminada.
                at.notifyDataSetChanged();
            }
        });


        at.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titulares.remove(recView.getChildAdapterPosition(v));
                at.notifyDataSetChanged();
            }
        });
    }
}