package com.example.cargarlibros;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BuscadorHolder extends RecyclerView.ViewHolder {
    TextView txt_titulolibro;
    TextView txt_publicacion;
    public BuscadorHolder(@NonNull View itemView) {
        super(itemView);
        txt_titulolibro = itemView.findViewById(R.id.txt_titulolibro);
        txt_publicacion = itemView.findViewById(R.id.txt_publicacion);
    }
}
