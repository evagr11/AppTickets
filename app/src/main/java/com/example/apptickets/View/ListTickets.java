package com.example.apptickets.View;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.apptickets.Model.EstadoTicket;
import com.example.apptickets.Model.Ticket;
import com.example.apptickets.R;
import com.google.android.material.chip.ChipGroup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ListTickets extends Fragment {

    private ChipGroup chipGroup;
    public ListTickets() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_tickets, container, false);

        ListView listView = rootView.findViewById(R.id.listView);

        File carpeta = new File(getContext().getFilesDir(), "TICKETS");
        File[] archivos = carpeta.listFiles();

        ArrayList<Ticket> listaTickets = new ArrayList<>();

        if (archivos != null){
            for (int i = 0; i < archivos.length; i++) {
                File archivo = archivos[i];
                try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                    String linea = br.readLine();
                    String[] partes = linea.split("\\|");

                    String titulo = partes[0].trim();
                    String descripcion = partes[1].trim();
                    String comoRepetir = partes[2].trim();
                    String estadoStr = partes[3].trim();
                    int id = Integer.parseInt(partes[4].trim());

                    EstadoTicket estado;
                    switch (estadoStr.toUpperCase()) {
                        case "NUEVO":
                            estado = EstadoTicket.NUEVO;
                            break;
                        case "ABIERTO":
                            estado = EstadoTicket.ABIERTO;
                            break;
                        case "EN_PROCESO":
                            estado = EstadoTicket.EN_PROCESO;
                            break;
                        case "RESUELTO":
                            estado = EstadoTicket.RESUELTO;
                            break;
                        default:
                            estado = EstadoTicket.CERRADO;
                            break;
                    }

                    listaTickets.add(new Ticket(id, titulo, descripcion, comoRepetir, estado));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        TicketAdapter adapter = new TicketAdapter(getContext(), listaTickets);
        listView.setAdapter(adapter);

        return rootView;
    }
}
