package com.example.garciaprietoivan_20210218;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdaptadorArticulo extends ArrayAdapter<Articulo> {
    private ArrayList<Articulo> datos;

    public AdaptadorArticulo(@NonNull Context context, ArrayList<Articulo> articulos) {
        super(context, R.layout.item_articulo_layout, articulos);
        datos = articulos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View vistaItem = convertView;

        if (vistaItem == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            vistaItem = inflater.inflate(R.layout.item_articulo_layout, null);
            holder = new ViewHolder();
            holder.nombre = vistaItem.findViewById(R.id.lblNombreArticulo);
            holder.precio = vistaItem.findViewById(R.id.lblPrecioArticulo);
            holder.imagen = vistaItem.findViewById(R.id.imgDescripcionProducto);
            vistaItem.setTag(holder);
        } else {
            holder = (ViewHolder) vistaItem.getTag();
        }

        Articulo articulo = datos.get(position);
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

        holder.imagen.setImageResource(imagen);
        holder.nombre.setText(articulo.getNombre());
        holder.precio.setText(String.format("%s€", articulo.getPrecio()));

        return vistaItem;
    }

    static class ViewHolder {
        TextView nombre;
        TextView precio;
        ImageView imagen;
    }
}
