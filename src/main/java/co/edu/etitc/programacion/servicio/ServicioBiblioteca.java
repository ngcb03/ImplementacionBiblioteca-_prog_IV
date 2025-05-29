package co.edu.etitc.programacion.servicio;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.etitc.programacion.persistencia.entidades.Computador;
import co.edu.etitc.programacion.persistencia.entidades.Libro;
import co.edu.etitc.programacion.persistencia.entidades.Periodico;
import co.edu.etitc.programacion.persistencia.entidades.Recurso;
import co.edu.etitc.programacion.persistencia.repositorio.ComputadorRepositorio;
import co.edu.etitc.programacion.persistencia.repositorio.LibroRepositorio;
import co.edu.etitc.programacion.persistencia.repositorio.PeriodicoRepositorio;

@Component
public class ServicioBiblioteca {

    // inyección de dependencias por constructor
    private final LibroRepositorio repositorioLibros;
    private final PeriodicoRepositorio repositorioPeriodicos;
    private final ComputadorRepositorio repositorioComputadores;
    
    @Autowired
    public ServicioBiblioteca(LibroRepositorio libroRepositorio,
                              PeriodicoRepositorio periodicoRepositorio,
                              ComputadorRepositorio computadorRepositorio) {
        this.repositorioLibros = libroRepositorio;
        this.repositorioPeriodicos = periodicoRepositorio;
        this.repositorioComputadores = computadorRepositorio;
    }

    // agregar un recurso desde el respositorio específico
    public void agregar(Recurso recurso) {
        if (recurso instanceof Libro) {
            repositorioLibros.save((Libro) recurso);
        } else if (recurso instanceof Periodico) {
            repositorioPeriodicos.save((Periodico) recurso);
        } else if (recurso instanceof Computador) {
            repositorioComputadores.save((Computador) recurso);
        } else {
            throw new IllegalArgumentException("Tipo de recurso no soportado: " + recurso.getClass().getName());
        }
    }

    // quitar un recurso desde el respositorio específico
    public void quitarRecurso(Recurso recurso) {
        if (recurso instanceof Libro) {
            repositorioLibros.delete((Libro) recurso);
        } else if (recurso instanceof Periodico) {
            repositorioPeriodicos.delete((Periodico) recurso);
        } else if (recurso instanceof Computador) {
            repositorioComputadores.delete((Computador) recurso);
        } else {
            throw new IllegalArgumentException("Tipo de recurso no soportado: " + recurso.getClass().getName());
        }
    }


    /* busca todos los recursos que cumplan con el criterio indicado, puede ser por 
    fecha, nombre, autor, etc (revisar métodos sobreescritos de cada clase que entienda Recurso).
    */
    public Collection<Recurso> buscarRecursos(String criterio) {
        Collection<Recurso> recursosBuscados = new ArrayList<>();
        
        recursosBuscados.addAll(repositorioLibros.findByCriteria(criterio));
        recursosBuscados.addAll(repositorioPeriodicos.findByCriteria(criterio));
        recursosBuscados.addAll(repositorioComputadores.findByCriteria(criterio));
        
        return recursosBuscados;
    }
    

    // devuelve los todos los recursos cargados en el momento.
    public Collection<Recurso> obtenerTodos() {
        Collection<Recurso> recursosBuscados = new ArrayList<>();

        recursosBuscados.addAll(repositorioLibros.findAll());
        recursosBuscados.addAll(repositorioPeriodicos.findAll());
        recursosBuscados.addAll(repositorioComputadores.findAll());

        return recursosBuscados;
    }

}
