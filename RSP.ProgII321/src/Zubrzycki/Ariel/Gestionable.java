package Zubrzycki.Ariel;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Gestionable<T extends CSVSerializable & Comparable<T>>{
    
    void agregar(T item);
            
    void eliminar(int indice); 
            
    public T obtener(int indice);
            
    void limpiar();
    
    void mostrarTodos();
    
    void ordenar();
    
    void ordenar(Comparator<T> comparator);
    
    List<T> filtrar(Predicate<T> predicate);
    
    void guardarEnBinario(String path);
    
    void cargarDesdeBinario(String path);
    
    void guardarEnCSV(String path);
    
    void cargarDesdeCSV(String path,Function<String, T> transformadora);
}
