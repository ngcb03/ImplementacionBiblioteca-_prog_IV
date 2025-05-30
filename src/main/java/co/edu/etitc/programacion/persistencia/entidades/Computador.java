package co.edu.etitc.programacion.persistencia.entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import co.edu.etitc.programacion.persistencia.entidades.enums.TipoComputador;

@Table(name = "COMPUTADOR") // indicamos con la anotación @Table que esta clase será mapeada junto con sus atributos como una tabla en la base de datos (h2).
public class Computador implements Recurso {

    // indicamos con la anotación @Id el atributo que será mapeado como Id en nuestra tabla 
    @Id
    private Integer id;
    
    // agregamos atributos nombres, fechaIngreso y activo antes herados de la clase Recurso (actualmente una interfáz)
    private String nombre;
    private LocalDateTime fechaIngreso;
    private boolean activo;
    private String marca;
    private String modelo;
    private String sistemaOperativo;
    private TipoComputador tipoComputador;

    // Formatear la fecha para una mejor presentación
    @Transient
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

    

    @Override
    public Integer getId() {
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

    @Override
    public void darDeBaja() {
        activo = false;
    }

    @Override
    public String toString() {
        return String.format("\n [/ COMPUTADOR /] \n - Id: %s\n - Nombre: %s\n - Fecha de ingreso: %s\n - Activo: %s\n - Marca: %s\n - Modelo: %s\n - Sistema operativo: %s\n - Tipo: %s", id, nombre, fechaIngreso.format(formatter), activo, marca, modelo, sistemaOperativo, this.getTipoComputador());
    }
    
}
