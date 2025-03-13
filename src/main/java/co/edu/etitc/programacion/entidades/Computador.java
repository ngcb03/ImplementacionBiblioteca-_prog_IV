package co.edu.etitc.programacion.entidades;

import java.time.LocalDateTime;
import co.edu.etitc.programacion.entidades.enums.TipoComputador;

/* 
 * Heredamos de la clase abstracta 'Recurso' para obtener sus propiedades y métodos.
 */

public class Computador extends Recurso {

    // encapsulamos las propiedades
    private String marca;
    private String modelo;
    private String sistemaOperativo;
    private TipoComputador tipoComputador;

    /* 
     * Además del contructor vacío por defecto, definimos otro constructor que acepte todos las propiedades definidas en la clase.
     */

    public Computador(String nombre, LocalDateTime fechaIngreso, boolean activo, String marca, String modelo, String sistemaOperativo, TipoComputador tipoComputador) {
        super(nombre, fechaIngreso, activo);
        this.marca = marca;
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
        this.tipoComputador = tipoComputador;
    }

    // métodos getters y setters para las propiedades encapsuladas

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public String getTipoComputador() {
        return tipoComputador.toString();
    }


    // se sobreescribe el metodo proveniente de la clase abstracta 'Recurso' en base a la lógica requerida para esta clase.
    @Override
    public boolean coincideConCriterio(String criterio) {
        return super.coincideConCriterio(criterio) || marca.contains(criterio) || sistemaOperativo.contains(criterio) || tipoComputador.toString().toLowerCase().contains(criterio);
    }

    /* 
     * también sobreescribimos el método 'toString' para agregar las propiedades de la clase abstracta 'Recurso' y 
     * poder visualizarlos por consola al momento de hacer eso de este método.
     */

    @Override
    public String toString() {
        return String.format("\n [/ COMPUTADOR /] \n %s - Marca: %s\n - Modelo: %s\n - Sistema operativo: %s\n - Tipo: %s", super.toString(), marca, modelo, sistemaOperativo, this.getTipoComputador());
    }
    
}
