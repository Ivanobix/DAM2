package com.example.ej_18;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorDisco extends RecyclerView.Adapter {

    private ArrayList<Disco> discos;

    public AdaptadorDisco(ArrayList<Disco> discos) {
        this.discos = discos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_disco_layout, parent, false);
        DiscoViewHolder holder = new DiscoViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Disco disco = discos.get(position);
        ((DiscoViewHolder) holder).bindTitular(disco);
    }

    @Override
    public int getItemCount() {
        return discos.size();
    }

    public static class DiscoViewHolder extends RecyclerView.ViewHolder {
        private TextView lblTitulo;
        private TextView lblAutor;
        private ImageView imgPortada;

        public DiscoViewHolder(@NonNull View itemView) {
            super(itemView);
            lblTitulo = (TextView) itemView.findViewById(R.id.lblTitulo);
            lblAutor = (TextView) itemView.findViewById(R.id.lblAutor);
            imgPortada = (ImageView) itemView.findViewById(R.id.imgPortada);
        }

        public void bindTitular(Disco e) {
            lblTitulo.setText(e.getTitulo());
            lblAutor.setText(e.getAutor());
            imgPortada.setImageResource(e.getPortada());
        }
    }
}
