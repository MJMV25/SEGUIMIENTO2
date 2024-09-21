package org.example.service;

import org.example.domain.Empleado;
import org.example.domain.Tarea;
import org.example.infraestructure.EmpleadoRepository;

import java.util.List;

public class EmpleadoService {
    private List<Empleado> empleados;
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService() {
        empleadoRepository = new EmpleadoRepository();
        empleados = empleadoRepository.cargarEmpleados();
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
        empleadoRepository.guardarEmpleados(empleados);
    }

    public Empleado buscarEmpleadoPorNombre(String nombre) {
        return empleados.stream().filter(e -> e.getNombre().equalsIgnoreCase(nombre)).findFirst().orElse(null);
    }

    public void asignarTarea(Empleado empleado, Tarea tarea) {
        empleado.agregarTarea(tarea);
        empleadoRepository.guardarEmpleados(empleados);
    }

    public void eliminarTarea(Empleado empleado, Tarea tarea) {
        empleado.eliminarTarea(tarea);
        empleadoRepository.guardarEmpleados(empleados);
    }

    public void actualizarEmpleado(Empleado empleado) {
        empleadoRepository.guardarEmpleados(empleados);
    }

    public List<Empleado> listarEmpleados() {
        return empleados;
    }

    public void generarReporteProductividad() {
        empleados.forEach(empleado -> {
            long completadas = empleado.getTareas().stream().filter(t -> t.getEstado().equals("Completada")).count();
            System.out.println(empleado.getNombre() + " " + empleado.getApellido() + " completó " + completadas + " tareas.");
        });
    }

    public double calcularSalarioPorDepartamento(String departamento) {
        return empleados.stream()
                .filter(e -> e.getDepartamento().equalsIgnoreCase(departamento))
                .mapToDouble(Empleado::getSalario)
                .sum();
    }
}

