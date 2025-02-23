package biblioteca;

import java.time.LocalDateTime;

public abstract class Recurso {

    private String nombre;
    private LocalDateTime fechaIngreso;
    private boolean activo;

    public Recurso(String nombre, LocalDateTime fechaIngreso, boolean activo) {
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
    }

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

    public abstract boolean coincideConCriterio(String criterio);

    @Override
    public String toString() {
        return "Recurso{" +
                "nombre='" + nombre + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", activo=" + activo +
                '}';
    }

}
