package co.edu.etitc.programacion.entidades;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class Libro implements Recurso {

    // encapsulamos las propiedades
    private Integer id;
    private String nombre;
    private LocalDateTime fechaIngreso;
    private boolean activo;
    private String autor;
    private String editorial;
    private Year anio;

    // Formatear la fecha para una mejor presentación
    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public Libro(String nombre, LocalDateTime fechaIngreso, boolean activo, String autor, String editorial, Year anio) {
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
    }

    public Libro() {}

    

    public Integer id() {
        return this.id;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public Year getAnio() {
        return anio;
    }

    // se sobreescribe el metodo proveniente de la clase abstracta 'Recurso' en base a la lógica requerida para esta clase.

    public boolean findByCriteria(String criterio) {
        return nombre.equals(criterio) || fechaIngreso.format(formatter).toString().equals(criterio) || autor.contains(criterio) || editorial.contains(criterio) || anio.toString().contains(criterio);
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
        return String.format("\n [/ LIBRO /] \n - Id: %s\n - Nombre: %s\n - Fecha de ingreso: %s\n - Activo: %s\n - Autor: %s\n - Editorial: %s\n - Año: %s", id, nombre, fechaIngreso.format(formatter), activo, autor, editorial, anio + "");
    }
}
