package co.edu.etitc.programacion.entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Recurso {

    // encapsulamos propiedades
    private String nombre;
    private LocalDateTime fechaIngreso;
    private boolean activo;

    
    // Formatear la fecha para una mejor presentación
    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /* 
     * Además del contructor vacío por defecto, definimos otro constructor que acepte todos las propiedades definidas en la clase.
     */

    public Recurso(String nombre, LocalDateTime fechaIngreso, boolean activo) {
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
    }

    // métodos getters y setters para las propiedades encapsuladas

    public String getNombre() {
        return nombre;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void darDeBaja() {
        activo = false;
    }


    // definimos método para busqueda por criterio que será usado por sus clases hijas/subclases.
    public boolean coincideConCriterio(String criterio) {
        return nombre.equals(criterio) || fechaIngreso.format(formatter).toString().equals(criterio);
    }


    // sobreescribmos método 'toString' (aunque no requerido ya que se genera con esta estructura por defecto).
    @Override
    public String toString() {
        return String.format("- Nombre: %s\n - Fecha de ingreso: %s\n - Activo: %s\n", nombre, fechaIngreso.format(formatter), activo);
    }

}

