package co.edu.etitc.programacion.persistencia.entidades;

import java.time.LocalDateTime;

public interface Recurso {

    String getNombre();
    LocalDateTime getFechaIngreso();
    boolean isActivo();
    void darDeBaja();
    String toString();

}

