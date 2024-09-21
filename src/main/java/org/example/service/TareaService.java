package org.example.service;




import org.example.domain.Tarea;
import org.example.infraestructure.TareaRepository;
import org.example.domain.Empleado;

import java.util.ArrayList;
import java.util.List;

public class TareaService {
    private List<Tarea> tareas;
    private final TareaRepository tareaRepository;

    public TareaService() {
        tareaRepository = new TareaRepository();
        tareas = tareaRepository.cargarTareas(); // Cargar tareas al iniciar
    }

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
        tareaRepository.guardarTareas(tareas); // Guardar después de agregar
    }

    public void eliminarTarea(Tarea tarea) {
        tareas.remove(tarea);
        tareaRepository.guardarTareas(tareas); // Guardar después de eliminar
    }

    public List<Tarea> listarTareas() {
        return tareas;
    }

    // Buscar tarea por título
    public Tarea buscarTareaPorTitulo(String titulo) {
        for (Tarea tarea : tareas) {
            if (tarea.getTitulo().equalsIgnoreCase(titulo)) {
                return tarea;
            }
        }
        return null; // No encontrada
    }

    // Listar tareas por empleado
    public List<Tarea> listarTareasPorEmpleado(Empleado empleado) {
        List<Tarea> tareasEmpleado = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getEmpleado().equals(empleado)) {
                tareasEmpleado.add(tarea);
            }
        }
        return tareasEmpleado;
    }

    public void actualizarEstadoTarea(Tarea tarea, String nuevoEstado) {
        tarea.setEstado(nuevoEstado);
        tareaRepository.guardarTareas(tareas); // Guardar después de actualizar
    }
}
