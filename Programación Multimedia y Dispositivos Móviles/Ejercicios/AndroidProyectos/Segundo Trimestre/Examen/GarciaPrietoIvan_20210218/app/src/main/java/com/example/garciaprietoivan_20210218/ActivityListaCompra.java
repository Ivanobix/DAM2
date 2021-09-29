package com.example.garciaprietoivan_20210218;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityListaCompra extends AppCompatActivity {

    public static final int RESULT_VENDIDO = 13423;
    private static final String[] categoria = new String[]{"Todo", "Móvil", "Ordenador", "Consola", "Libro", "Deporte"};
    private static final String[] estado = new String[]{"Todo", "Nuevo", "Usado"};
    private static final int REQUEST_COMPRA = 135;

    private SharedPreferences misDatos;
    private Spinner spTipoCompra, spEstadoCompra;
    private ListView listProductos;
    private TextView lblSaldo;
    private ArrayAdapter<String> adapTipo;
    private ArrayAdapter<String> adapEstado;
    private AdaptadorArticulo at;
    private ArrayList<Articulo> articulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compra);

        misDatos = getSharedPreferences("preferencias", MODE_PRIVATE);
        articulos = new ArrayList<>();

        spTipoCompra = (Spinner) findViewById(R.id.spTipoCompra);
        spEstadoCompra = (Spinner) findViewById(R.id.spEstadoCompra);
        listProductos = (ListView) findViewById(R.id.listProductos);
        lblSaldo = (TextView) findViewById(R.id.lblSaldo);

        adapTipo = new ArrayAdapter<>(this, R.layout.item_layout, categoria);
        adapTipo.setDropDownViewResource(R.layout.item_dropdown_layout);
        adapEstado = new ArrayAdapter<>(this, R.layout.item_layout, estado);
        adapEstado.setDropDownViewResource(R.layout.item_dropdown_layout);

        spTipoCompra.setAdapter(adapTipo);
        spEstadoCompra.setAdapter(adapEstado);
        lblSaldo.setText(String.valueOf(misDatos.getFloat("saldo", (float) 0)));

        obtenerArticulos();

        listProductos.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ActivityListaCompra.this, ActivityCompraProducto.class);
            intent.putExtra("idArticulo", articulos.get(position).getIdArticulo());
            startActivityForResult(intent, REQUEST_COMPRA);
        });

        initHandlers();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_COMPRA && resultCode == RESULT_VENDIDO) {
            obtenerArticulos();
            at.notifyDataSetChanged();
            lblSaldo.setText(String.valueOf(misDatos.getFloat("saldo", (float) 0)));
        }
    }

    private void obtenerArticulos() {
        ArticuloDB articuloDB = new ArticuloDB(getApplicationContext());
        articuloDB.openForRead();
        articulos = articuloDB.obtenerTodosContactos();
        articuloDB.close();
        if (articulos == null) {
            Toast.makeText(getApplicationContext(), "No se han encontrado artículos.", Toast.LENGTH_SHORT).show();
        } else {
            at = new AdaptadorArticulo(this, articulos);
            listProductos.setAdapter(at);
        }
    }

    private void filtrarLista(String tipo, String estado) {
        if (!(tipo.equals("Todo") && estado.equals("Todo"))) {
            ArrayList<Articulo> articulosFiltrados = new ArrayList<>();
            obtenerArticulos();

            if (tipo.equals("Todo")) {
                for (Articulo articulo : articulos) {
                    if (articulo.getEstado().equals(estado)) {
                        articulosFiltrados.add(articulo);
                    }
                }
            } else if (estado.equals("Todo")) {
                for (Articulo articulo : articulos) {
                    if (articulo.getCategoria().equals(tipo)) {
                        articulosFiltrados.add(articulo);
                    }
                }
            } else {
                for (Articulo articulo : articulos) {
                    if (articulo.getCategoria().equals(tipo) && articulo.getEstado().equals(estado)) {
                        articulosFiltrados.add(articulo);
                    }
                }
            }

            articulos = articulosFiltrados;
            if (articulos.size() < 1) {
                Toast.makeText(getApplicationContext(), "Ningún artículo coincide con los criterios de búsqueda.", Toast.LENGTH_SHORT).show();
            }
            at = new AdaptadorArticulo(this, articulos);
            listProductos.setAdapter(at);
            at.notifyDataSetChanged();
        } else {
            obtenerArticulos();
        }
    }

    private void initHandlers() {
        spTipoCompra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtrarLista(adapTipo.getItem(spTipoCompra.getSelectedItemPosition()), adapEstado.getItem(spEstadoCompra.getSelectedItemPosition()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spEstadoCompra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtrarLista(adapTipo.getItem(spTipoCompra.getSelectedItemPosition()), adapEstado.getItem(spEstadoCompra.getSelectedItemPosition()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}