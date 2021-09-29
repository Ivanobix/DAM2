package com.example.ejemlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listElementos = (ListView)findViewById(R.id.listElementos);

        //ListView utilizando string array
        //ArrayAdapter<CharSequence> adap = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_list_item_1);
        //listElementos.setAdapter(adap);

        //List View utilizando un array de string
        //String[] items = new String[] {"Teclado", "Ratón", "Monitor"};
        //ArrayAdapter<String> adap = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        //listElementos.setAdapter(adap);

        //List View utilizando un ArrayList de Titular y un adaptador personalizado
        ArrayList<Titular> listaTitulos = new ArrayList<>();
        listaTitulos.add(new Titular("Don Quijote", "Miguel de Cervantes"));
        listaTitulos.add(new Titular("Lazarillo de Tormes", "Anónimo"));
        listaTitulos.add(new Titular("Episodios Nacionales", "Benito Pérez Galdós"));

        AdaptadorTitular at = new AdaptadorTitular(this, listaTitulos);
        listElementos.setAdapter(at);

        listaTitulos.add(new Titular("La Celestina", "Fernando de Rojas"));
        at.notifyDataSetChanged();
    }
}