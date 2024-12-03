package Zubrzycki.Ariel;

import java.time.LocalDate;
import java.util.List;

public class Tester {

    public static void main(String[] args) {
        try {
            // Crear instancia del gestor
            GestorEventos<EventoMusical> gestor = new GestorEventos<>();

            // Agregar eventos musicales
            gestor.agregar(new EventoMusical(1, "Concierto de Rock", LocalDate.of(2024, 12, 10), "Band XYZ", GeneroMusical.ROCK));
            gestor.agregar(new EventoMusical(2, "Festival de Jazz", LocalDate.of(2024, 11, 20), "Jazz Masters", GeneroMusical.JAZZ));
            gestor.agregar(new EventoMusical(3, "Fiesta Electrónica", LocalDate.of(2024, 12, 5), "DJ Beat", GeneroMusical.ELECTRONICA));
            gestor.agregar(new EventoMusical(4, "Recital de Pop", LocalDate.of(2025, 1, 15), "Pop Stars", GeneroMusical.POP));

            // Mostrar eventos agregados
            System.out.println("Eventos iniciales:");
            gestor.mostrarTodos();

            // Filtrar eventos por género
            System.out.println("\nFiltrar por género: ROCK");
            List<EventoMusical> rockEventos = gestor.filtrar((EventoMusical e) -> {return e.getGenero().equals(GeneroMusical.ROCK);});
            rockEventos.forEach(System.out::println);

            
            // Filtrar eventos por rango de fechas
            System.out.println("\nFiltrar por rango de fechas (2024-12-01 a 2024-12-31):");
            List<EventoMusical> rangoFechas = gestor.filtrar((EventoMusical e) -> {
                return e.getFecha().isAfter(LocalDate.of(2024, 12, 1)) && e.getFecha().isBefore(LocalDate.of(2024, 12, 30));});
            rangoFechas.forEach(System.out::println);
            

            // Ordenar eventos por nombre
            System.out.println("\nEventos ordenados por nombre:");
            gestor.ordenar((e1, e2) -> (e1.getNombre()).compareTo(e2.getNombre()));
            gestor.mostrarTodos();

            // Ordenar eventos por fecha
            System.out.println("\nEventos ordenados por fecha:");
            gestor.ordenar();
            gestor.mostrarTodos();

            // Guardar y cargar en archivo binario
            gestor.guardarEnBinario(AppConfig.PATH_SER.toString());
            GestorEventos<EventoMusical> gestorBinario = new GestorEventos<>();
            gestorBinario.cargarDesdeBinario(AppConfig.PATH_SER.toString());
            System.out.println("\nEventos cargados desde archivo binario:");
            gestorBinario.mostrarTodos();

            // Guardar y cargar en archivo CSV
            gestor.guardarEnCSV(AppConfig.PATH_CSV.toString());
            GestorEventos<EventoMusical> gestorCSV = new GestorEventos<>();
            gestorCSV.cargarDesdeCSV(AppConfig.PATH_CSV.toString(), EventoMusical::fromCSV);
            System.out.println("\nEventos cargados desde archivo CSV:");
            gestorCSV.mostrarTodos();

            // Eliminar evento
            System.out.println("\nEliminar evento con índice 1:");
            gestor.eliminar(1);
            gestor.mostrarTodos();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }    
}

