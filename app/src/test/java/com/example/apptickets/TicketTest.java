package com.example.apptickets;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.apptickets.Model.EstadoTicket;
import com.example.apptickets.Model.Ticket;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TicketTest {

    @Test
    public void testCrearTicket() {
        Ticket ticket = new Ticket(1, "Titulo", "Descripcion", "ComoRepetir", EstadoTicket.NUEVO);

        assertEquals(1, ticket.getId());
        assertEquals("Titulo", ticket.getTitulo());
        assertEquals("Descripcion", ticket.getDescripcion());
        assertEquals("ComoRepetir", ticket.getComoRepetir());
        assertEquals(EstadoTicket.NUEVO, ticket.getEstado());
    }

    @Test
    public void testCrearTicketMal() {
        Ticket ticket = new Ticket(1, "", "", "", EstadoTicket.NUEVO);

        assertEquals(1, ticket.getId());
        assertEquals("Titulo", ticket.getTitulo());
        assertEquals("Descripcion", ticket.getDescripcion());
        assertEquals("ComoRepetir", ticket.getComoRepetir());
        assertEquals(EstadoTicket.NUEVO, ticket.getEstado());
    }

}