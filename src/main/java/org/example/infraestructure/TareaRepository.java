package org.example.infraestructure;



import org.example.domain.Tarea;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TareaRepository {
    private final String archivoTareas = "tareas.dat"; // Nombre del archivo para almacenar tareas

    // Cargar las tareas desde el archivo
    public List<Tarea> cargarTareas() {
        List<Tarea> tareas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoTareas))) {
            tareas = (List<Tarea>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de tareas no encontrado. Se creará uno nuevo.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tareas;
    }

    // Guardar las tareas en el archivo
    public void guardarTareas(List<Tarea> tareas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoTareas))) {
            oos.writeObject(tareas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
