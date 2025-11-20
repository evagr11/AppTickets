package com.example.apptickets.View;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apptickets.Model.EstadoTicket;
import com.example.apptickets.Model.Ticket;
import com.example.apptickets.R;
import com.google.android.material.chip.Chip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class ListTickets extends Fragment {

    private Chip chipNuevo, chipAbierto, chipProceso, chipResuelto, chipCerrado;
    private TextView noTickets;

    private ArrayList<Ticket> listaTickets = new ArrayList<>();
    private ArrayList<Ticket> listaFiltrada = new ArrayList<>();
    private TicketAdapter adapter;

    private ListView listView;

    public ListTickets() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list_tickets, container, false);

        chipNuevo = rootView.findViewById(R.id.chipNuevo);
        chipAbierto = rootView.findViewById(R.id.chipAbierto);
        chipProceso = rootView.findViewById(R.id.chipProceso);
        chipResuelto = rootView.findViewById(R.id.chipResuelto);
        chipCerrado = rootView.findViewById(R.id.chipCerrado);
        noTickets = rootView.findViewById(R.id.textView3);
        listView = rootView.findViewById(R.id.listView);

        cargarTicketsDesdeArchivos();

        filtrado();

        edicion();

        return rootView;
    }

    private void filtrado(){
        chipNuevo.setOnCheckedChangeListener((b, c) -> filtrar());
        chipAbierto.setOnCheckedChangeListener((b, c) -> filtrar());
        chipProceso.setOnCheckedChangeListener((b, c) -> filtrar());
        chipResuelto.setOnCheckedChangeListener((b, c) -> filtrar());
        chipCerrado.setOnCheckedChangeListener((b, c) -> filtrar());
    }

    private void edicion(){
        listaFiltrada.addAll(listaTickets);
        adapter = new TicketAdapter(getContext(), listaFiltrada);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Ticket seleccionado = listaFiltrada.get(position);

            Bundle args = new Bundle();
            args.putInt("id", seleccionado.getId());
            args.putString("titulo", seleccionado.getTitulo());
            args.putString("descripcion", seleccionado.getDescripcion());
            args.putString("comoRepetir", seleccionado.getComoRepetir());
            args.putString("estado", seleccionado.getEstado().name());

            CrearTickets fragmentEditar = new CrearTickets();
            fragmentEditar.setArguments(args);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerTickets, fragmentEditar)
                    .addToBackStack(null)
                    .commit();
        });
    }

    private void cargarTicketsDesdeArchivos() {
        listaTickets.clear();
        listaFiltrada.clear();

        File carpeta = new File(getContext().getFilesDir(), "TICKETS");
        File[] archivos = carpeta.listFiles();

        if (archivos != null) {
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

                    switch (estadoStr) {
                        case "NUEVO": estado = EstadoTicket.NUEVO;
                            break;
                        case "ABIERTO": estado = EstadoTicket.ABIERTO;
                            break;
                        case "EN_PROCESO": estado = EstadoTicket.EN_PROCESO;
                            break;
                        case "RESUELTO": estado = EstadoTicket.RESUELTO;
                            break;
                        case "CERRADO": estado = EstadoTicket.CERRADO;
                            break;
                        default: throw new Exception("");
                    }

                    listaTickets.add(new Ticket(id, titulo, descripcion, comoRepetir, estado));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Collections.reverse(listaTickets);
        }
    }

    private void filtrar() {

        boolean filtroNuevo = chipNuevo.isChecked();
        boolean filtroAbierto = chipAbierto.isChecked();
        boolean filtroProceso = chipProceso.isChecked();
        boolean filtroResuelto = chipResuelto.isChecked();
        boolean filtroCerrado = chipCerrado.isChecked();

        boolean ninguno = !filtroNuevo && !filtroAbierto && !filtroProceso && !filtroResuelto && !filtroCerrado;
        boolean todos = filtroNuevo && filtroAbierto && filtroProceso && filtroResuelto && filtroCerrado;

        listaFiltrada.clear();

        if (ninguno || todos) {
            listaFiltrada.addAll(listaTickets);
        } else {
            for (int i = 0; i < listaTickets.size(); i++) {
                EstadoTicket estado = listaTickets.get(i).getEstado();

                if ((filtroNuevo && estado == EstadoTicket.NUEVO) ||
                        (filtroAbierto && estado == EstadoTicket.ABIERTO) ||
                        (filtroProceso && estado == EstadoTicket.EN_PROCESO) ||
                        (filtroResuelto && estado == EstadoTicket.RESUELTO) ||
                        (filtroCerrado && estado == EstadoTicket.CERRADO)) {

                    listaFiltrada.add(listaTickets.get(i));
                }
            }

        }

        if(listaFiltrada.isEmpty()){
            noTickets.setVisibility(View.VISIBLE);
        } else{
            noTickets.setVisibility(View.GONE);
        }

        adapter.notifyDataSetChanged();
    }
}