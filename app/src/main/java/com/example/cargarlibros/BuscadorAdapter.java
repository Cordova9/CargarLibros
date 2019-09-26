package com.example.cargarlibros;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BuscadorAdapter extends RecyclerView.Adapter<BuscadorHolder> {
    LayoutInflater inflater;
    List<Item_Libro> data;
    Context c;

    public BuscadorAdapter(Context c, List<Item_Libro> data) {
        inflater = LayoutInflater.from(c);
        this.data = data;
        this.c = c;
    }

    @NonNull
    @Override
    public BuscadorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_libro, parent, false);
        return new BuscadorHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BuscadorHolder holder, int position) {
        holder.txt_titulolibro.setText(data.get(position).getTitulo());
        holder.txt_publicacion.setText(data.get(position).getPublicacion());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
