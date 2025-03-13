package co.edu.etitc.programacion.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/* 
 * Heredamos de la clase abstracta 'Recurso' para obtener sus propiedades y métodos.
 */

public class Periodico extends Recurso {

    // encapsulamos las propiedades
    private LocalDate fechaPublicacion;
    private String editorial;


    // Formatear la fecha para una mejor presentación
    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    
    public Periodico(String nombre, LocalDateTime fechaIngreso, boolean activo, LocalDate fechaPublicacion, String editorial) {
        super(nombre, fechaIngreso, activo);
        this.fechaPublicacion = fechaPublicacion;
        this.editorial = editorial;
    }

    // métodos getters y setters para las propiedades encapsuladas

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    // se sobreescribe el metodo proveniente de la clase abstracta 'Recurso' en base a la lógica requerida para esta clase.

    @Override
    public boolean coincideConCriterio(String criterio) {
        return super.coincideConCriterio(criterio) || fechaPublicacion.format(formatter).toString().contains(criterio) || editorial.contains(criterio);
    }

    /* 
     * también sobreescribimos el método 'toString' para agregar las propiedades de la clase abstracta 'Recurso' y 
     * poder visualizarlos por consola al momento de hacer eso de este método.
     */

    @Override
    public String toString() {
        return String.format("\n [/ PERIODICO /] \n %s - Fecha de publicación: %s\n - Editorial: %s", super.toString(), fechaPublicacion.format(formatter), editorial);
    }

}
