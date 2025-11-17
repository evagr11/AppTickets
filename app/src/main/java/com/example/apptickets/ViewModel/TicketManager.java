package com.example.apptickets.ViewModel;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    private int obtenerSiguienteId(File carpeta){
        File[] archivos = carpeta.listFiles();
        if(archivos.length == 0) return 1;
        return archivos.length + 1;

    }
}
