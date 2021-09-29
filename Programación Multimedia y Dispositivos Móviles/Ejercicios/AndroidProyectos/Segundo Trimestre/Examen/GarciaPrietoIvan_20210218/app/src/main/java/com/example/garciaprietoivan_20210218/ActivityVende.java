package com.example.garciaprietoivan_20210218;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityVende extends AppCompatActivity {

    private Spinner spCategoria, spEstado;
    private Button btnGuardar, btnCancelar;
    private EditText txtNombre, txtDescripcion, txtTelefono, txtPrecio;
    private ArrayAdapter<String> adapCategoria;
    private ArrayAdapter<String> adapEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vende);

        spCategoria = (Spinner) findViewById(R.id.spCategoria);
        spEstado = (Spinner) findViewById(R.id.spEstado);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtPrecio = (EditText) findViewById(R.id.txtPrecio);

        adapCategoria = new ArrayAdapter<>(this, R.layout.item_layout, MainActivity.categoria);
        adapCategoria.setDropDownViewResource(R.layout.item_dropdown_layout);
        adapEstado = new ArrayAdapter<>(this, R.layout.item_layout, MainActivity.estado);
        adapEstado.setDropDownViewResource(R.layout.item_dropdown_layout);
        spCategoria.setAdapter(adapCategoria);
        spEstado.setAdapter(adapEstado);

        btnCancelar.setOnClickListener(v -> finish());
        btnGuardar.setOnClickListener(v -> guardarArticulo());
    }

    private Articulo recogerDatos() {
        Articulo articulo = null;

        String categoria = adapCategoria.getItem(spCategoria.getSelectedItemPosition());
        if (!categoria.isEmpty()) {
            String estado = adapEstado.getItem(spEstado.getSelectedItemPosition());
            if (!estado.isEmpty()) {
                String nombre = txtNombre.getText().toString().trim();
                if (!nombre.isEmpty()) {
                    String descripcion = txtDescripcion.getText().toString().trim();
                    if (!descripcion.isEmpty()) {
                        String telefono = txtTelefono.getText().toString();
                        if (!telefono.isEmpty()) {
                            try {
                                float precio = Float.parseFloat(txtPrecio.getText().toString());
                                if (precio >= 0) {
                                    articulo = new Articulo(nombre, descripcion, categoria, estado, telefono, precio);
                                } else {
                                    Toast.makeText(ActivityVende.this, "El precio debe ser mayor o igual que 0.", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                Toast.makeText(ActivityVende.this, "El precio no es correcto.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ActivityVende.this, "Falta el telefono.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ActivityVende.this, "Falta la descripción.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivityVende.this, "Falta el nombre.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ActivityVende.this, "Falta el estado.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ActivityVende.this, "Falta la categoría.", Toast.LENGTH_SHORT).show();
        }

        return articulo;
    }

    private void guardarArticulo() {
        Articulo articulo = recogerDatos();
        if (articulo != null) {
            ArticuloDB articuloDB = new ArticuloDB(getApplicationContext());
            articuloDB.openForWrite();

            if (articuloDB.insertarArticulo(articulo) != -1) {
                txtNombre.setText("");
                txtDescripcion.setText("");
                txtTelefono.setText("");
                txtPrecio.setText("");
                spCategoria.setSelection(0);
                spEstado.setSelection(0);
                Toast.makeText(ActivityVende.this, "El artículo se ha puesto a la venta.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ActivityVende.this, "Algo no ha ido bien.", Toast.LENGTH_SHORT).show();
            }
            articuloDB.close();
        }
    }
}