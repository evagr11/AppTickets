package com.example.apptickets;

import static org.junit.Assert.assertEquals;

import com.example.apptickets.Model.EstadoTicket;
import com.example.apptickets.Model.Ticket;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TicketFilterTest {

    @Test
    public void testFiltrado() {
        ArrayList<Ticket> lista = new ArrayList<>();
        lista.add(new Ticket(1, "T1", "Desc1", "Repetir1",EstadoTicket.NUEVO));
        lista.add(new Ticket(2, "T1", "Desc1", "Repetir1",EstadoTicket.NUEVO));
        lista.add(new Ticket(3, "T2", "Desc2", "Repetir2",EstadoTicket.EN_PROCESO));
        lista.add(new Ticket(4, "T3", "Desc3", "Repetir3",EstadoTicket.RESUELTO));

        ArrayList<Ticket> filtrada = new ArrayList<>();
        for(int i = 0; i < lista.size(); i++){
            if(lista.get(i).getEstado() == EstadoTicket.NUEVO){
                filtrada.add(lista.get(i));
            }
        }

        assertEquals(2,filtrada.size());
    }

}