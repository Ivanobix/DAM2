package com.example.ej_contentreceivercontactos;

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

public class AdaptadorContacto extends ArrayAdapter<Contacto> {
    private final ArrayList<Contacto> datos;

    public AdaptadorContacto(@NonNull Context context, ArrayList<Contacto> contactos) {
        super(context, R.layout.item_contacto, contactos);
        datos = contactos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View vistaItem = convertView;

        if (vistaItem == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            vistaItem = inflater.inflate(R.layout.item_contacto, null);
            holder = new ViewHolder();
            holder.nombre = vistaItem.findViewById(R.id.lblNombre);
            holder.fotoPerfil = vistaItem.findViewById(R.id.imgFoto);
            vistaItem.setTag(holder);
        } else {
            holder = (ViewHolder) vistaItem.getTag();
        }

        Contacto contacto = datos.get(position);

        holder.nombre.setText(contacto.getNombre());
        holder.fotoPerfil.setImageURI(contacto.getFotoPerfil());

        return vistaItem;
    }

    static class ViewHolder {
        TextView nombre;
        ImageView fotoPerfil;
    }
}
