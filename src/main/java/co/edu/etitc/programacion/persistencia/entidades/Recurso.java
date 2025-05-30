package co.edu.etitc.programacion.persistencia.entidades;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// anotaciones para manejar los subtipos
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "tipo"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Libro.class, name = "libro"),
    @JsonSubTypes.Type(value = Periodico.class, name = "periodico"),
    @JsonSubTypes.Type(value = Computador.class, name = "computador")
})
public interface Recurso {

    Integer getId();
    String getNombre();
    LocalDateTime getFechaIngreso();
    boolean isActivo();
    void darDeBaja();
    String toString();

}

