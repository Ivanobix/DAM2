package com.example.garcaprietoivn_2020_12_03;

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

public class AdaptadorMascota extends ArrayAdapter<Mascota> {

    private final ArrayList<Mascota> datos;

    public AdaptadorMascota(@NonNull Context context, ArrayList<Mascota> mascotas) {
        super(context, R.layout.item_mascota, mascotas);
        datos = mascotas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View vistaItem = convertView;

        if (vistaItem == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            vistaItem = inflater.inflate(R.layout.item_mascota, null);
            holder = new ViewHolder();
            holder.nombreMascota = vistaItem.findViewById(R.id.lblNombreMascota);
            holder.estadoMascota = vistaItem.findViewById(R.id.lblEstadoMascota);
            holder.fotoMascota = vistaItem.findViewById(R.id.imgMascota);
            vistaItem.setTag(holder);
        } else {
            holder = (ViewHolder) vistaItem.getTag();
        }

        Mascota disco = datos.get(position);

        holder.fotoMascota.setImageResource(disco.getIdFoto());
        holder.nombreMascota.setText(disco.getNombre());
        holder.estadoMascota.setText(disco.getEstado());

        return vistaItem;
    }

    static class ViewHolder {
        TextView nombreMascota;
        TextView estadoMascota;
        ImageView fotoMascota;
    }
}
