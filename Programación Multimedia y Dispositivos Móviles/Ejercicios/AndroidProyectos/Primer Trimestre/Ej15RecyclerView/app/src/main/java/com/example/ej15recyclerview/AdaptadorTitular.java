package com.example.ej15recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class AdaptadorTitular extends RecyclerView.Adapter {

    private ArrayList<Titular> datos;
    private View.OnClickListener listener;

    public AdaptadorTitular(ArrayList<Titular> datos) {
        this.datos = datos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adaptador_titular, parent, false);
        TitularViewHolder holder = new TitularViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {
        Titular titular = datos.get(pos);
        ((TitularViewHolder) holder).bindTitular(titular);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public static class TitularViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitulo;
        private TextView txtSubtitulo;

        public TitularViewHolder(View itemView) {
            super(itemView);
            txtTitulo = (TextView) itemView.findViewById(R.id.txtTitulo);
            txtSubtitulo = (TextView) itemView.findViewById(R.id.txtSubtitulo);
        }

        public void bindTitular(Titular t) {
            txtTitulo.setText(t.getTitulo());
            txtSubtitulo.setText(t.getSubtitulo());
        }
    }


}