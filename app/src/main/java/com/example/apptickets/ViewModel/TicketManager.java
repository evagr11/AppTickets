package com.example.apptickets.ViewModel;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.example.apptickets.Model.EstadoTicket;
import com.example.apptickets.Model.Ticket;

/**
 * Clase encargada de gestionar el almacenamiento y actualización de tickets.
 *
 * <p>Utiliza el sistema de archivos interno de la aplicación para crear una carpeta
 * denominada "TICKETS" y guardar cada ticket en un archivo de texto individual.</p>
 *
 * <p>Los archivos se nombran con el patrón: {@code ticket<ID>.txt}</p>
 */
public class TicketManager {

    /** Contexto de la aplicación, necesario para acceder al sistema de archivos interno. */
    private Context ct;

    /**
     * Constructor que recibe el contexto de la aplicación.
     *
     * @param ct Contexto necesario para acceder al sistema de archivos.
     */
    public TicketManager(Context ct){
        this.ct = ct;
    }

    /**
     * Guarda un nuevo ticket en la carpeta local "TICKETS".
     *
     * <p>Si la carpeta no existe, la crea. Asigna automáticamente un ID incremental
     * basado en el número de archivos existentes en la carpeta.</p>
     *
     * @param ticket Ticket a guardar (el ID será reasignado internamente).
     */
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

    /**
     * Actualiza un ticket existente sobrescribiendo su archivo correspondiente.
     *
     * @param ticket Ticket con los datos actualizados. Debe contener un ID válido.
     */
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

    /**
     * Calcula el siguiente ID disponible en base a la cantidad de archivos existentes.
     *
     * <p>Si no existen archivos en la carpeta, devuelve 1. En caso contrario,
     * devuelve el tamaño de la lista de archivos más uno.</p>
     *
     * @param carpeta Carpeta donde se almacenan los tickets.
     * @return ID siguiente disponible.
     */
    private int obtenerSiguienteId(File carpeta){
        File[] archivos = carpeta.listFiles();
        if(archivos.length == 0) return 1;
        return archivos.length + 1;

    }

}
