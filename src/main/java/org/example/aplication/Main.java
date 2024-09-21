package org.example.aplication;

import org.example.domain.Empleado;
import org.example.domain.Tarea;
import org.example.service.EmpleadoService;
import org.example.service.TareaService;
import org.example.infraestructure.TareaRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmpleadoService empleadoService = new EmpleadoService();
        TareaService tareaService = new TareaService();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Registrar un nuevo empleado");
            System.out.println("2. Actualizar datos del empleado");
            System.out.println("3. Asignar una nueva tarea a un empleado existente");
            System.out.println("4. Eliminar una tarea asignada");
            System.out.println("5. Mostrar la lista de empleados registrados");
            System.out.println("6. Mostrar la lista de tareas asignadas a un empleado específico");
            System.out.println("7. Actualizar el estado de una tarea");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1: // Registrar un nuevo empleado
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = scanner.nextLine();
                    System.out.print("Edad: ");
                    int edad = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    System.out.print("Cargo: ");
                    String cargo = scanner.nextLine();
                    System.out.print("Departamento: ");
                    String departamento = scanner.nextLine();
                    System.out.print("Salario: ");
                    double salario = scanner.nextDouble();
                    scanner.nextLine(); // Limpiar buffer

                    Empleado empleado = new Empleado(nombre, apellido, edad, cargo, departamento, salario);
                    empleadoService.agregarEmpleado(empleado);
                    System.out.println("Empleado registrado exitosamente.");
                    break;

                case 2: // Actualizar datos del empleado
                    System.out.print("Ingrese el nombre del empleado a actualizar: ");
                    String nombreEmpleado = scanner.nextLine();
                    Empleado empleadoExistente = empleadoService.buscarEmpleadoPorNombre(nombreEmpleado);
                    if (empleadoExistente != null) {
                        System.out.print("Nuevo Cargo: ");
                        String nuevoCargo = scanner.nextLine();
                        System.out.print("Nuevo Departamento: ");
                        String nuevoDepartamento = scanner.nextLine();
                        System.out.print("Nuevo Salario: ");
                        double nuevoSalario = scanner.nextDouble();
                        scanner.nextLine(); // Limpiar buffer

                        empleadoExistente.setCargo(nuevoCargo);
                        empleadoExistente.setDepartamento(nuevoDepartamento);
                        empleadoExistente.setSalario(nuevoSalario);
                        empleadoService.actualizarEmpleado(empleadoExistente);
                        System.out.println("Empleado actualizado exitosamente.");
                    } else {
                        System.out.println("Empleado no encontrado.");
                    }
                    break;

                case 3: // Asignar una nueva tarea
                    System.out.print("Título de la tarea: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Descripción: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Fecha de inicio: ");
                    String fechaInicio = scanner.nextLine();
                    System.out.print("Fecha de fin: ");
                    String fechaFin = scanner.nextLine();
                    System.out.print("Estado: ");
                    String estado = scanner.nextLine();

                    System.out.print("Nombre del empleado asignado: ");
                    String nombreEmpleadoAsignado = scanner.nextLine();
                    Empleado empleadoAsignado = empleadoService.buscarEmpleadoPorNombre(nombreEmpleadoAsignado);
                    if (empleadoAsignado != null) {
                        Tarea tarea = new Tarea(titulo, descripcion, fechaInicio, fechaFin, estado, empleadoAsignado);
                        tareaService.agregarTarea(tarea);
                        System.out.println("Tarea asignada exitosamente.");
                    } else {
                        System.out.println("Empleado no encontrado.");
                    }
                    break;

                case 4: // Eliminar una tarea
                    System.out.print("Ingrese el título de la tarea a eliminar: ");
                    String tituloTarea = scanner.nextLine();
                    Tarea tareaAEliminar = tareaService.buscarTareaPorTitulo(tituloTarea);
                    if (tareaAEliminar != null) {
                        tareaService.eliminarTarea(tareaAEliminar);
                        System.out.println("Tarea eliminada exitosamente.");
                    } else {
                        System.out.println("Tarea no encontrada.");
                    }
                    break;

                case 5: // Mostrar lista de empleados
                    List<Empleado> empleados = empleadoService.listarEmpleados();
                    System.out.println("Lista de Empleados:");
                    for (Empleado emp : empleados) {
                        System.out.println(emp);
                    }
                    break;

                case 6: // Mostrar tareas de un empleado
                    System.out.print("Ingrese el nombre del empleado: ");
                    String nombreEmpleadoTareas = scanner.nextLine();
                    Empleado empleadoTareas = empleadoService.buscarEmpleadoPorNombre(nombreEmpleadoTareas);
                    if (empleadoTareas != null) {
                        List<Tarea> tareasEmpl = tareaService.listarTareasPorEmpleado(empleadoTareas);
                        System.out.println("Tareas asignadas a " + nombreEmpleadoTareas + ":");
                        for (Tarea tareaEmp : tareasEmpl) {
                            System.out.println(tareaEmp);
                        }
                    } else {
                        System.out.println("Empleado no encontrado.");
                    }
                    break;

                case 7: // Actualizar estado de una tarea
                    System.out.print("Ingrese el título de la tarea a actualizar: ");
                    String tituloTareaActualizar = scanner.nextLine();
                    Tarea tareaActualizar = tareaService.buscarTareaPorTitulo(tituloTareaActualizar);
                    if (tareaActualizar != null) {
                        System.out.print("Nuevo estado: ");
                        String nuevoEstado = scanner.nextLine();
                        tareaService.actualizarEstadoTarea(tareaActualizar, nuevoEstado);
                        System.out.println("Estado de la tarea actualizado exitosamente.");
                    } else {
                        System.out.println("Tarea no encontrada.");
                    }
                    break;

                case 8: // Salir
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        scanner.close();
    }
}

