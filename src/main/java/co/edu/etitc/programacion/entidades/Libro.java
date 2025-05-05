package co.edu.etitc.programacion.entidades;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "LIBRO")
public class Libro implements Recurso {

    @Id
    private Integer id;

    private String nombre;
    private LocalDateTime fechaIngreso;
    private boolean activo;
    private String autor;
    private String editorial;
    private int anio; // ¡se cambio es tipo de dato para el campo año ya que genera un error de conversion por un entero!

    // Formatear la fecha para una mejor presentación
    @Transient
    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public Libro(String nombre, LocalDateTime fechaIngreso, boolean activo, String autor, String editorial, int anio) {
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

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public int getAnio() {
        return anio;
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
