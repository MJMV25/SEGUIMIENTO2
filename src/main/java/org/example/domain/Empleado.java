package org.example.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Empleado implements Serializable {
    private String nombre;
    private String apellido;
    private int edad;
    private String cargo;
    private String departamento;
    private double salario;
    private List<Tarea> tareas;

    public Empleado(String nombre, String apellido, int edad, String cargo, String departamento, double salario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.cargo = cargo;
        this.departamento = departamento;
        this.salario = salario;
        this.tareas = new ArrayList<>();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    public List<Tarea> getTareas() { return tareas; }
    public void agregarTarea(Tarea tarea) { tareas.add(tarea); }
    public void eliminarTarea(Tarea tarea) { tareas.remove(tarea); }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", cargo='" + cargo + '\'' +
                ", departamento='" + departamento + '\'' +
                ", salario=" + salario +
                '}';
    }
}

