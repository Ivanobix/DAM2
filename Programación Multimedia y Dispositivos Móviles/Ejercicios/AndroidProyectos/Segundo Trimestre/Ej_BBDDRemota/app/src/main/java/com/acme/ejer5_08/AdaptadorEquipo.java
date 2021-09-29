package com.acme.ejer5_08;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorEquipo extends ArrayAdapter<Equipo> {

    private final ArrayList<Equipo> datos;

    public AdaptadorEquipo(Context c, ArrayList<Equipo> items) {
        super(c, R.layout.listitem_equipo, items);
        datos = items;
    }

    public View getView(int pos, View vista, ViewGroup parent) {
        View item = vista;
        ViewHolder holder;

        if (item == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            item = inflater.inflate(R.layout.listitem_equipo, null);
            holder = new ViewHolder();
            holder.nombre = (TextView) item.findViewById(R.id.itemNombre);
            item.setTag(holder);

        } else {
            holder = (ViewHolder) item.getTag();
        }
        String txtNombre = datos.get(pos).getCiudad() + " " + datos.get(pos).getNombre();
        holder.nombre.setText(txtNombre);
        return (item);
    }

    static class ViewHolder {
        TextView nombre;
    }
}
