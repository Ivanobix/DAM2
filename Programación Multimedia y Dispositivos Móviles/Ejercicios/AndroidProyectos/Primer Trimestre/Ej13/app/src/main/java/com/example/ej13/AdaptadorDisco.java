package com.example.ej13;

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

public class AdaptadorDisco extends ArrayAdapter<Disco> {

    private ArrayList<Disco> datos;

    public AdaptadorDisco(@NonNull Context context, ArrayList<Disco> discos) {
        super(context, R.layout.item_disco_layout, discos);
        datos = discos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View vistaItem = convertView;

        if (vistaItem == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            vistaItem = inflater.inflate(R.layout.item_disco_layout, null);
            holder = new ViewHolder();
            holder.titulo = vistaItem.findViewById(R.id.lblTitulo);
            holder.autor = vistaItem.findViewById(R.id.lblAutor);
            holder.portada = vistaItem.findViewById(R.id.imgPortada);
            vistaItem.setTag(holder);
        } else {
            holder = (ViewHolder) vistaItem.getTag();
        }

        Disco disco = datos.get(position);

        holder.portada.setImageResource(disco.getPortada());
        holder.titulo.setText(disco.getTitulo());
        holder.autor.setText(disco.getAutor());

        return vistaItem;
    }

    static class ViewHolder {
        TextView titulo;
        TextView autor;
        ImageView portada;
    }

}
