package com.example.apptickets.Model;

/**
 * Clase que representa un ticket
 */
public class Ticket {
    private int id;
    private String titulo, descripcion, comoRepetir;
    private EstadoTicket estado;

    /**
     * Constructor para crear un ticket
     *
     * @param id Identificador único del ticket
     * @param titulo Título del ticket
     * @param descripcion Descripción del problema
     * @param comoRepetir Instrucciones para reproducir el problema
     * @param estado Estado actual del ticket
     */
    public Ticket(int id, String titulo, String descripcion, String comoRepetir, EstadoTicket estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.comoRepetir = comoRepetir;
        this.estado = estado;
    }

    /**
     * Obtiene el identificador único del ticket.
     *
     * @return ID del ticket.
     */
    public int getId() { return id; }

    /**
     * Obtiene el título del ticket.
     *
     * @return Título del ticket.
     */
    public String getTitulo() { return titulo; }

    /**
     * Obtiene las instrucciones para reproducir el problema.
     *
     * @return Texto con los pasos para reproducir el problema.
     */
    public String getComoRepetir() { return comoRepetir; }

    /**
     * Obtiene la descripción del ticket.
     *
     * @return Descripción del ticket.
     */
    public String getDescripcion() { return descripcion; }

    /**
     * Obtiene el estado actual del ticket.
     *
     * @return Estado del ticket.
     */
    public EstadoTicket getEstado() { return estado; }

    /**
     * Establece el estado del ticket
     *
     * @param estado Nuevo estado a asignar.
     */
    public void setEstado(EstadoTicket estado) {
        this.estado = estado;
    }

    /**
     * Representa el ticket como una cadena separada por barras verticales.
     *
     * <p>Formato: titulo | descripcion | comoRepetir | estado | id</p>
     *
     * @return Cadena con los datos del ticket en formato plano.
     */
    @Override
    public String toString(){
        return titulo + " | " +
               descripcion + " | " +
               comoRepetir + " | " +
               estado + " | " +
               id;
    }

}
