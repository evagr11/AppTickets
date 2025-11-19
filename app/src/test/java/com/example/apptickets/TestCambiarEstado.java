package com.example.apptickets;

import static org.junit.Assert.assertEquals;

import com.example.apptickets.Model.EstadoTicket;
import com.example.apptickets.Model.Ticket;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestCambiarEstado {

    @Test
    public void testCambiarEstado() {
        Ticket ticket = new Ticket(1, "T1", "Desc1", "repetir1", EstadoTicket.NUEVO);
        ticket.setEstado(EstadoTicket.RESUELTO);

        assertEquals(EstadoTicket.RESUELTO, ticket.getEstado());
    }

}