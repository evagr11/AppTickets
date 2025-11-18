package com.example.apptickets.ViewModel;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.example.apptickets.Model.EstadoTicket;
import com.example.apptickets.Model.Ticket;

public class TicketManager {

    private Context ct;

    public TicketManager(Context ct){
        this.ct = ct;
    }

    public void guardarTicket(Ticket ticket){
        try{
            //Creo la carpeta general para los tickets
            File carpeta = new File(ct.getFilesDir(), "TICKETS");
            if(!carpeta.exists()){
                carpeta.mkdir();
            }

            int id = obtenerSiguienteId(carpeta);
            ticket = new Ticket(id, ticket.getTitulo(), ticket.getDescripcion(), ticket.getComoRepetir(), ticket.getEstado());

            String nombreArchivo = ("ticket" + id + ".txt");
            File archivoTicket = new File(carpeta, nombreArchivo);

            FileWriter writer = new FileWriter(archivoTicket);
            writer.write(ticket.toString());
            writer.close();
        }catch (IOException e ){
            e.printStackTrace();
        }

    }

    public void actualizarTicket(Ticket ticket){
        try{
            File carpeta = new File(ct.getFilesDir(), "TICKETS");
            String nombreArchivo = "ticket" + ticket.getId() + ".txt";
            File archivoTicket = new File(carpeta, nombreArchivo);

            FileWriter writer = new FileWriter(archivoTicket);
            writer.write(ticket.toString());
            writer.close();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }


    private int obtenerSiguienteId(File carpeta){
        File[] archivos = carpeta.listFiles();
        if(archivos.length == 0) return 1;
        return archivos.length + 1;

    }



    // Borrar todos los tickets
    public void borrarTickets() {
        File carpeta = new File(ct.getFilesDir(), "TICKETS");
        if (carpeta.exists()) {
            File[] archivos = carpeta.listFiles();
            if (archivos != null) {
                for (int i = 0; i < archivos.length; i++) {
                    archivos[i].delete();
                }
            }
            carpeta.delete();
        }
    }

    // 5 tickets d testeo
    public void crearTicketsEjemplo() {
        guardarTicket(new Ticket(0, "Error de inicio", "La app no arranca correctamente", "Abrir la app desde el launcher", EstadoTicket.NUEVO));
        guardarTicket(new Ticket(0, "Pantalla congelada", "La pantalla se queda bloqueada", "Navegar al menú principal", EstadoTicket.ABIERTO));
        guardarTicket(new Ticket(0, "Bug en formulario", "El formulario no valida bien", "Rellenar campos con datos inválidos", EstadoTicket.EN_PROCESO));
        guardarTicket(new Ticket(0, "Crash solucionado", "El error de cierre inesperado ya está corregido", "Abrir y cerrar sesión", EstadoTicket.RESUELTO));
        guardarTicket(new Ticket(0, "Ticket cerrado", "El problema fue revisado y cerrado", "No aplica", EstadoTicket.CERRADO));
    }
}
