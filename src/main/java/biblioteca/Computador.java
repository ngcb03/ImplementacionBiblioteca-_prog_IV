package biblioteca;

import java.time.LocalDateTime;

public class Computador extends Recurso {

    private String marca;
    private String modelo;
    private String sistemaOperativo;
    private TipoComputador tipoComputador;


    public Computador(String nombre, LocalDateTime fechaIngreso, boolean activo, String marca, String modelo, String sistemaOperativo, TipoComputador tipoComputador) {
        super(nombre, fechaIngreso, activo);
        this.marca = marca;
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
        this.tipoComputador = tipoComputador;
    }

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
        return tipoComputador.toString().substring(1, getTipoComputador().length()-1).toLowerCase();
    }

    @Override
    public boolean coincideConCriterio(String criterio) {
        return getNombre().contains(criterio) || marca.contains(criterio) || sistemaOperativo.contains(criterio) || tipoComputador.toString().toLowerCase().contains(criterio);
    }

    @Override
    public String toString() {
        return "Computador{" +
                super.toString() +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", sistemaOperativo='" + sistemaOperativo + '\'' +
                ", tipoComputador='" + tipoComputador.toString() + '\'' +
                '}';
    }
    
}
