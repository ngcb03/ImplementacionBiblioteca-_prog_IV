package co.edu.etitc.programacion.persistencia.entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "LIBRO") // indicamos con la anotación @Table que esta clase será mapeada junto con sus atributos como una tabla en la base de datos (h2).
public class Libro implements Recurso {

    // indicamos con la anotación @Id el atributo que será mapeado como Id en nuestra tabla
    @Id
    private Integer id;

    // agregamos atributos nombres, fechaIngreso y activo antes herados de la clase Recurso (actualmente una interfáz)
    private String nombre;
    private LocalDateTime fechaIngreso;
    private boolean activo;
    private String autor;
    private String editorial;
    private int anio; // ¡se cambia el tipo de dato para el campo año de Year a int, ya que genera un error de conversion con el tipo Year!

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

    

    @Override
    public Integer getId() {
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
