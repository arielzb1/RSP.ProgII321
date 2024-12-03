package Zubrzycki.Ariel;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Evento implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre;
    private LocalDate fecha;

    public Evento(int id, String nombre, LocalDate fecha) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    
    public String toString() {
        return "id=" + id + ", nombre=" + nombre + ", fecha=" + fecha;
    }
    
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Evento e){
            return Integer.compare(id, e.id) == 0;
        }
        
        Evento evento = (Evento)obj;
        return evento.nombre == this.nombre && evento.fecha == this.fecha;
    }
}
