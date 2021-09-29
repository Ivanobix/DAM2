package com.example.ejemlistview;

import android.content.Context;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdaptadorTitular extends ArrayAdapter<Titular> {

    private ArrayList<Titular> datos;

    static class ViewHolder {
        TextView titulo;
        TextView subtitulos;
    }

    public AdaptadorTitular(@NonNull Context context, ArrayList<Titular> items) {
        super(context, R.layout.item_titular_layout, items);
        datos = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View vista, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View vistaItem = vista;
        if(vistaItem==null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            vistaItem = inflater.inflate(R.layout.item_titular_layout, null);
            holder = new ViewHolder();
            holder.titulo = (TextView)vistaItem.findViewById(R.id.itemTitulo);
            holder.subtitulos=(TextView)vistaItem.findViewById(R.id.itemSubtitulo);
            vistaItem.setTag(holder);
        }
        else {
            holder = (ViewHolder)vistaItem.getTag();
        }

        Titular titular = datos.get(position);
        holder.titulo.setText(titular.getTitulo());
        holder.subtitulos.setText(titular.getSubtitulo());

        return vistaItem;
    }
}
