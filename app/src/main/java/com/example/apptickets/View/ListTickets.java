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

/**
 * Fragmento que muestra la lista de tickets registrados.
 *
 * <p>Permite filtrar por estado mediante chips y abrir el formulario de edición
 * al pulsar sobre un elemento de la lista.</p>
 */
public class ListTickets extends Fragment {

    private Chip chipNuevo, chipAbierto, chipProceso, chipResuelto, chipCerrado;
    private TextView noTickets;

    private ArrayList<Ticket> listaTickets = new ArrayList<>();
    private ArrayList<Ticket> listaFiltrada = new ArrayList<>();
    private TicketAdapter adapter;

    private ListView listView;

    public ListTickets() {}

    /**
     * Infla el layout del fragmento, inicializa los componentes,
     * carga los tickets desde archivos y configura el filtrado y la edición.
     *
     * @param inflater Inflador de vistas.
     * @param container Contenedor padre.
     * @param savedInstanceState Estado guardado del fragmento.
     * @return Vista raíz del fragmento.
     */
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


        adapter = new TicketAdapter(getContext(), listaFiltrada);
        listView.setAdapter(adapter);

        cargarTicketsDesdeArchivos();

        filtrado();

        edicion();

        return rootView;
    }

    /**
     * Configura los listeners de los chips para activar el filtrado dinámico.
     */
    private void filtrado(){
        chipNuevo.setOnCheckedChangeListener((b, c) -> filtrar());
        chipAbierto.setOnCheckedChangeListener((b, c) -> filtrar());
        chipProceso.setOnCheckedChangeListener((b, c) -> filtrar());
        chipResuelto.setOnCheckedChangeListener((b, c) -> filtrar());
        chipCerrado.setOnCheckedChangeListener((b, c) -> filtrar());
    }

    /**
     * Configura el comportamiento de edición al hacer clic en un elemento de la lista.
     *
     * <p>Abre el fragmento {@link CrearTickets} con los datos del ticket seleccionado
     * prellenados mediante un {@link android.os.Bundle}.</p>
     */
    private void edicion(){
        listaFiltrada.addAll(listaTickets);

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

    /**
     * Carga los tickets desde archivos locales en la carpeta "TICKETS".
     *
     * <p>Lee la primera línea de cada archivo y realiza parsing del contenido para
     * construir objetos {@link Ticket}. Invierte el orden para mostrar el más reciente primero.</p>
     */
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

    /**
     * Aplica el filtrado según los chips seleccionados.
     *
     * <p>Si no hay filtros seleccionados o están todos activos, se muestran todos los tickets.
     * También actualiza la visibilidad del mensaje cuando la lista filtrada está vacía.</p>
     */
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