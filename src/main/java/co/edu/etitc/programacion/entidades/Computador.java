package co.edu.etitc.programacion.entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import co.edu.etitc.programacion.entidades.enums.TipoComputador;


public class Computador implements Recurso {

    // encapsulamos las propiedades
    private Integer id;
    private String nombre;
    private LocalDateTime fechaIngreso;
    private boolean activo;
    private String marca;
    private String modelo;
    private String sistemaOperativo;
    private TipoComputador tipoComputador;

    // Formatear la fecha para una mejor presentación
    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public Computador(String nombre, LocalDateTime fechaIngreso, boolean activo, String marca, String modelo, String sistemaOperativo, TipoComputador tipoComputador) {
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
        this.marca = marca;
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
        this.tipoComputador = tipoComputador;
    }

    public Computador() {}

    

    public Integer id() {
        return this.id;
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
        return tipoComputador.toString();
    }

    public boolean findByCriteria(String criterio) {
        return nombre.equals(criterio) || fechaIngreso.format(formatter).toString().equals(criterio) || marca.contains(criterio) || sistemaOperativo.contains(criterio) || tipoComputador.toString().toLowerCase().contains(criterio);
    }

    // sobreescribimos métodos de la interfaz Recurso con lógica propia.

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

    @Override
    public void darDeBaja() {
        activo = false;
    }

    @Override
    public String toString() {
        return String.format("\n [/ COMPUTADOR /] \n - Id: %s\n - Nombre: %s\n - Fecha de ingreso: %s\n - Activo: %s\n - Marca: %s\n - Modelo: %s\n - Sistema operativo: %s\n - Tipo: %s", id, nombre, fechaIngreso.format(formatter), activo, marca, modelo, sistemaOperativo, this.getTipoComputador());
    }
    
}
