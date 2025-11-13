package com.example.apptickets;

public class Ticket {
    private int id;
    private String titulo;
    private String descripcion;
    private String comoRepetir;
    private String estado;

    public Ticket(int id, String titulo, String descripcion, String comoRepetir, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.comoRepetir = comoRepetir;
        this.estado = estado;
    }

    public int getId() { return id; }

    public String getTitulo() { return titulo; }

    public String getComoRepetir() { return comoRepetir; }

    public String getDescripcion() { return descripcion; }

    public String getEstado() { return estado; }

    @Override
    public String toString(){
        return titulo + " | " +
               descripcion + " | " +
               comoRepetir + " | " +
               estado + " | " +
               id;
    }
}
