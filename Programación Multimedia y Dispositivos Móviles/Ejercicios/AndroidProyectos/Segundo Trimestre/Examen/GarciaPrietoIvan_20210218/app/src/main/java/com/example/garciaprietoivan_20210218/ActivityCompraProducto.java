package com.example.garciaprietoivan_20210218;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.MessageFormat;

public class ActivityCompraProducto extends AppCompatActivity {

    private SharedPreferences misDatos;
    private SharedPreferences.Editor editor;
    private TextView lblSaldo, lblTipoEstado, lblPrecioCompra, lblResumen;
    private ImageView imgProducto;
    private ImageButton btnLlamar, btnComprarProducto;
    private float saldo;
    private Articulo articulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_producto);

        misDatos = getSharedPreferences("preferencias", MODE_PRIVATE);
        editor = misDatos.edit();

        lblSaldo = (TextView) findViewById(R.id.lblSaldoCompra);
        lblTipoEstado = (TextView) findViewById(R.id.lblTipoEstado);
        lblPrecioCompra = (TextView) findViewById(R.id.lblPrecioCompra);
        lblResumen = (TextView) findViewById(R.id.lblResumen);
        imgProducto = (ImageView) findViewById(R.id.imgProducto);
        btnLlamar = (ImageButton) findViewById(R.id.btnLlamar);
        btnComprarProducto = (ImageButton) findViewById(R.id.btnComprarProducto);

        obtenerArticulo();
        mostrarDatos();

        btnLlamar.setOnClickListener(v -> llamar());

        btnComprarProducto.setOnClickListener(v -> {
            if (articulo.getPrecio() <= saldo) {
                comprar();
            } else {
                Toast.makeText(ActivityCompraProducto.this, "No tienes saldo suficiente.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void obtenerArticulo() {
        Intent data = getIntent();
        int idArticulo;
        if (data != null && data.hasExtra("idArticulo")) {
            idArticulo = data.getIntExtra("idArticulo", 0);

        } else {
            idArticulo = 0;
        }


        ArticuloDB articuloDB = new ArticuloDB(getApplicationContext());
        articuloDB.openForWrite();
        articulo = articuloDB.obtenerArticulo(idArticulo);
        articuloDB.close();
    }

    private void mostrarDatos() {
        saldo = misDatos.getFloat("saldo", 0);
        lblSaldo.setText(String.valueOf(saldo));

        int imagen;
        switch (articulo.getCategoria()) {
            case "Móvil":
                imagen = R.drawable.icon_movil;
                break;
            case "Ordenador":
                imagen = R.drawable.icon_ordenador;
                break;
            case "Consola":
                imagen = R.drawable.icon_consola;
                break;
            case "Libro":
                imagen = R.drawable.icon_libro;
                break;
            default:
                imagen = R.drawable.icon_deporte;
                break;
        }
        imgProducto.setImageResource(imagen);

        lblTipoEstado.setText(MessageFormat.format("{0} {1}", articulo.getCategoria(), articulo.getEstado()));
        lblPrecioCompra.setText(String.format("%s€", articulo.getPrecio()));
        String resumen = articulo.getNombre() + "\n" + articulo.getDescripcion();
        lblResumen.setText(resumen);
    }

    private void comprar() {
        ArticuloDB articuloDB = new ArticuloDB(getApplicationContext());
        articuloDB.openForWrite();

        if (articuloDB.eliminarArticulo(articulo.getIdArticulo()) != -1) {
            Toast.makeText(ActivityCompraProducto.this, "Enhorabuena, has comprado: " + articulo.getNombre(), Toast.LENGTH_SHORT).show();
            editor.putFloat("saldo", saldo - articulo.getPrecio());
            editor.apply();
            setResult(ActivityListaCompra.RESULT_VENDIDO);
            finish();
        } else {
            Toast.makeText(ActivityCompraProducto.this, "Algo no ha ido bien.", Toast.LENGTH_SHORT).show();
        }
        articuloDB.close();
    }

    private void llamar() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel: " + articulo.getTelefono()));
        startActivity(intent);
    }
}