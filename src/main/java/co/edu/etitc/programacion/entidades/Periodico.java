package co.edu.etitc.programacion.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Periodico implements Recurso {

    // encapsulamos las propiedades
    private Integer id;
    private String nombre;
    private LocalDateTime fechaIngreso;
    private boolean activo;
    private LocalDate fechaPublicacion;
    private String editorial;


    // Formatear la fecha para una mejor presentación
    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    
    public Periodico(String nombre, LocalDateTime fechaIngreso, boolean activo, LocalDate fechaPublicacion, String editorial) {
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
        this.fechaPublicacion = fechaPublicacion;
        this.editorial = editorial;
    }

    public Periodico() {}
    


    public Integer id() {
        return this.id;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public boolean findByCriteria(String criterio) {
        return nombre.equals(criterio) || fechaIngreso.format(formatter).toString().equals(criterio) || fechaPublicacion.format(formatter).toString().contains(criterio) || editorial.contains(criterio);
    }

    // sobreescribimos métodos de la interfaz Recurso con lógica propia.

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    @Override
    public boolean isActivo() {
        return activo;
    }

    @Override
    public void darDeBaja() {
        activo = false;
    }

    @Override
    public String toString() {
        return String.format("\n [/ PERIODICO /] \n - Id: %s\n - Nombre: %s\n - Fecha de ingreso: %s\n - Activo: %s\n - Fecha de publicación: %s\n - Editorial: %s", id, nombre, fechaIngreso.format(formatter), activo, fechaPublicacion.format(formatter), editorial);
    }

}
