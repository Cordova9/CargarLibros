package com.example.cargarlibros;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolderDatos> {

    ArrayList<String> listadatos;

    public adapter(ArrayList<String> listadatos) {
        this.listadatos = listadatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_libros,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listadatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listadatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder{

        TextView datos;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            datos = (TextView) itemView.findViewById(R.id.lista);
        }

        public void asignarDatos(String s) {
            datos.setText(s);
        }
    }


}
