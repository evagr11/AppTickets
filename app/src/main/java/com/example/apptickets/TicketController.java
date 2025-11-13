package com.example.apptickets;

import android.content.Context;

public class TicketController {

    private final TicketManager ticketManager;

    public TicketController(Context ct){
        this.ticketManager = new TicketManager(ct);
    }

    public void crearTicket(String titulo, String descripcion, String comoRepetir, String estado){
        Ticket ticket = new Ticket(0, titulo,descripcion,comoRepetir,estado);
        ticketManager.guardarTicket(ticket);
    }
}
