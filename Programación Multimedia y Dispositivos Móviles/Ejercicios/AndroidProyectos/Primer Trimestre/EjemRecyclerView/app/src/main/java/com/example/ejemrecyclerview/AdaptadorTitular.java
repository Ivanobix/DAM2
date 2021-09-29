package com.example.ejemrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorTitular extends RecyclerView.Adapter implements View.OnClickListener {

    //1º esto lo hamos nosotros para tener los datos.
    protected ArrayList<Titular> datos;
    private View.OnClickListener listener;

    public AdaptadorTitular(ArrayList<Titular> datos) {
        this.datos = datos;
    }

    //2º Despues añadimos la clase ViewHolder

    public void setOnClickListener(View.OnClickListener listen) {
        this.listener = listen;
    }

    //Este metodo se llama cuando se crea el ViewHolder, por lo cual aqui tenemos que crear la vista
    //con un inflador
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_layout, parent, false);
        TitularViewHolder holder = new TitularViewHolder(itemView);
        itemView.setOnClickListener(this);
        return holder;
    }

    //llamar al bind e indicarle la posicion que ocupa el elemento
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Titular t = datos.get(position);
        ((TitularViewHolder) holder).blindTitular(t);
    }

    //conocer cuantos elementos tiene mi conjunto de objetos
    @Override
    public int getItemCount() {
        return datos.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public static class TitularViewHolder extends RecyclerView.ViewHolder {
        protected TextView subtitulo;
        private TextView titulo;

        //3º obtenemos las referencias para cada una de esas vistas de item_layout
        public TitularViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.itemTitulo);
            subtitulo = (TextView) itemView.findViewById(R.id.itemSubtitulo);
            //4º despues hacemos un metodo nuevo que rellena estos objetos con el contenido blindTitular
        }

        public void blindTitular(Titular t) {
            titulo.setText(t.getTitulo());
            subtitulo.setText(t.getTitulo());
        }
    }
}
