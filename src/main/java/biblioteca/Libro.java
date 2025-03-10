package biblioteca;

import java.time.LocalDateTime;
import java.time.Year;

/* 
 * Heredamos de la clase abstracta 'Recurso' para obtener sus propiedades y métodos.
 */

public class Libro extends Recurso {

    // encapsulamos las propiedades
    private String autor;
    private String editorial;
    private Year anio;

    /* 
     * Además del contructor vacío por defecto, definimos otro constructor que acepte todos las propiedades definidas en la clase.
     */

    public Libro(String nombre, LocalDateTime fechaIngreso, boolean activo, String autor, String editorial, Year anio) {
        super(nombre, fechaIngreso, activo);
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
    }

    // métodos getters y setters para las propiedades encapsuladas

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

    @Override
    public boolean coincideConCriterio(String criterio) {
        return super.coincideConCriterio(criterio) || autor.contains(criterio) || editorial.contains(criterio) || anio.toString().contains(criterio);
    }

    /* 
     * también sobreescribimos el método 'toString' para agregar las propiedades de la clase abstracta 'Recurso' y 
     * poder visualizarlos por consola al momento de hacer eso de este método.
     */

    @Override
    public String toString() {
        return String.format("\n [/ LIBRO /] \n %s - Autor: %s\n - Editorial: %s\n - Año: %s", super.toString(), autor, editorial, anio + "");
    }
}
