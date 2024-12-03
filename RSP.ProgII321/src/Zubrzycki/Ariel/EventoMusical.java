package Zubrzycki.Ariel;

import java.io.Serializable;
import java.time.LocalDate;

public class EventoMusical extends Evento implements Serializable, CSVSerializable,Comparable<EventoMusical>{
    private static final long serialVersionUID = 1L;
    private String artista;
    private GeneroMusical genero;

    public EventoMusical(int id, String nombre, LocalDate fecha, String artista, GeneroMusical genero) {
        super(id, nombre, fecha);
        this.artista = artista;
        this.genero = genero;
    }

    public String getArtista() {
        return artista;
    }

    public GeneroMusical getGenero() {
        return genero;
    }
    
    
    public int compareTo(EventoMusical e) {
          return this.getFecha().compareTo(e.getFecha());
    }

    @Override
    public String toCSV() {
        return String.format("%d,%s,%s,%s,%s", this.getId(), this.getNombre(), this.getFecha(), artista, genero);
    }

    @Override
    public String toHeaderCSV() {
        return "ID,Nombre,Fecha,Artista,Genero";
    }

    @Override
    public String toString() {
        return "EventoMusical{" + super.toString()+ ", artista=" + artista + ", genero=" + genero + '}';
    }   
    
    public static EventoMusical fromCSV(String eventoCSV){
        String[] values = eventoCSV.split(",");
        return new EventoMusical(Integer.parseInt(values[0]), 
                values[1], 
                LocalDate.parse(values[2]),
                values[3],
                GeneroMusical.valueOf(values[4])); 
    }
}
