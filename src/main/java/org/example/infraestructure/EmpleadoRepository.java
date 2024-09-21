package org.example.infraestructure;



import org.example.domain.Empleado;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepository {
    private final String archivoEmpleados = "empleados.dat";

    public List<Empleado> cargarEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoEmpleados))) {
            empleados = (List<Empleado>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Se creará uno nuevo.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public void guardarEmpleados(List<Empleado> empleados) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoEmpleados))) {
            oos.writeObject(empleados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
