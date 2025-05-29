package co.edu.etitc.programacion.persistencia.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "PERIODICO")  // indicamos con la anotación @Table que esta clase será mapeada junto con sus atributos como una tabla en la base de datos (h2).
public class Periodico implements Recurso {

    // indicamos con la anotación @Id el atributo que será mapeado como Id en nuestra tabla
    @Id
    private Integer id;
    
    // agregamos atributos nombres, fechaIngreso y activo antes herados de la clase Recurso (actualmente una interfáz)
    private String nombre;
    private LocalDateTime fechaIngreso;
    private boolean activo;
    private LocalDate fechaPublicacion;
    private String editorial;


    // Formatear la fecha para una mejor presentación
    @Transient
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

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getEditorial() {
        return editorial;
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
