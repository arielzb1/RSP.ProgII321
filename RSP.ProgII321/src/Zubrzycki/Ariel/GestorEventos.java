package Zubrzycki.Ariel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class GestorEventos<T extends CSVSerializable & Comparable<T>> implements Gestionable<T>{
    private List<T> items = new ArrayList<>();

    @Override
    public void agregar(T item) {
        if(item == null){
            throw new IllegalArgumentException("No puedo almanecar algo nulo");
        }
        items.add(item);
    }

    @Override
    public void eliminar(int indice) {
        validarIndice(indice);
        items.remove(indice);
    }
    
    @Override
    public T obtener(int indice) {
        validarIndice(indice);
        return items.get(indice);
    }
    
    @Override
    public void limpiar() {
        items.clear();
    }
    
    @Override
    public void mostrarTodos() {
        if (!items.isEmpty()){
            for (T item : items) {
                System.out.println(item);
            }
        }
    }

    @Override
    public void ordenar() {
        ordenar((Comparator<T>) Comparator.naturalOrder());
    }

    @Override
    public void ordenar(Comparator<T> comparator) {
        items.sort(comparator);
    }

    @Override
    public List<T> filtrar(Predicate<T> predicate) {   
        List<T> toReturn = new ArrayList<>();
        for (T item : items) {
            if (predicate.test(item)) {
                toReturn.add(item);
            }
        }
        return toReturn; 
    }

    public void validarIndice(int indice){
        if(indice < 0 && indice < items.size()){
            throw new IndexOutOfBoundsException("Indice invalido");
        }
    }
    
    @Override
    public void guardarEnBinario(String path){
        try{
            validarPath(path);

            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(path)); 

            salida.writeObject(items);

            salida.close();
        }catch(IOException e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void cargarDesdeBinario(String path){
        try{
            validarPath(path);
            if (!(new File(path)).exists()) {
                throw new FileNotFoundException("El archivo no existe");
            }

            limpiar();
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(path)); 
            items.addAll((List<T>) entrada.readObject());  
            entrada.close();
        }catch(IOException | ClassNotFoundException e){
            System.err.println("Error: " + e.getMessage());
        }
    }

   @Override
    public void guardarEnCSV(String path) {
        try{
            validarPath(path);

            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            
            bw.write(items.get(0).toHeaderCSV() + "\n");
            for (T item : items) {
                bw.write(item.toCSV() + "\n");
            }

            bw.close();
        }catch(IOException e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void cargarDesdeCSV(String path, Function<String, T> transformadora) {
        try{
            validarPath(path);
            if (!(new File(path)).exists()) {
                throw new FileNotFoundException("El archivo no existe");
            }

            limpiar();
            BufferedReader br = new BufferedReader(new FileReader(path));
            String linea = "";

            br.readLine();
            while((linea = br.readLine()) != null){
                items.add(transformadora.apply(linea));
            }

            br.close();
        }catch(IOException e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void validarPath(String path){
        if (path == null) {
            throw new IllegalArgumentException("La ruta no existe o no apunta a un archivo valido");
        }
    } 
}
