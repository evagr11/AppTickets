package com.example.apptickets.Model;

public class Ticket {
    private int id;
    private String titulo, descripcion, comoRepetir;
    private EstadoTicket estado;

    public Ticket(int id, String titulo, String descripcion, String comoRepetir, EstadoTicket estado) {
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

    public EstadoTicket getEstado() { return estado; }

    public void setEstado(EstadoTicket estado) {
        this.estado = estado;
    }

    @Override
    public String toString(){
        return titulo + " | " +
               descripcion + " | " +
               comoRepetir + " | " +
               estado + " | " +
               id;
    }

}
