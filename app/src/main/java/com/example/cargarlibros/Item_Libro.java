package com.example.cargarlibros;

public class Item_Libro {
    String titulo;
    String publicacion;

    public Item_Libro(String titulo, String publicacion) {
        this.titulo = titulo;
        this.publicacion = publicacion;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getPublicacion() {
        return publicacion;
    }
    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }
}
