package co.edu.etitc.programacion;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.etitc.programacion.entidades.Computador;
import co.edu.etitc.programacion.entidades.Libro;
import co.edu.etitc.programacion.entidades.Periodico;
import co.edu.etitc.programacion.entidades.Recurso;
import co.edu.etitc.programacion.repositorio.interfaces.RecursoRepositorio;

@Component
public class ServicioBiblioteca {

    // inyección de dependencias por constructor lineas 18-34
    private final RecursoRepositorio<Libro> repositorioLibros;
    private final RecursoRepositorio<Periodico> repositorioPeriodicos;
    private final RecursoRepositorio<Computador> repositorioComputadores;

    /*
     * ejemplo práctico en código donde una clase sea inyectada 
     * como dependencia mediante el constructor.
    */
    @Autowired
    public ServicioBiblioteca(RecursoRepositorio<Libro> libroRepositorio,
                              RecursoRepositorio<Periodico> periodicoRepositorio,
                              RecursoRepositorio<Computador> computadorRepositorio) {
        this.repositorioLibros = libroRepositorio;
        this.repositorioPeriodicos = periodicoRepositorio;
        this.repositorioComputadores = computadorRepositorio;
    }

    // agregar un recurso desde el respositorio específico
    public void agregar(Recurso recurso) {
        if (recurso instanceof Libro) {
            repositorioLibros.agregar((Libro) recurso);
        } else if (recurso instanceof Periodico) {
            repositorioPeriodicos.agregar((Periodico) recurso);
        } else if (recurso instanceof Computador) {
            repositorioComputadores.agregar((Computador) recurso);
        } else {
            throw new IllegalArgumentException("Tipo de recurso no soportado: " + recurso.getClass().getName());
        }
    }

    // quitar un recurso desde el respositorio específico
    public void quitarRecurso(Recurso recurso) {
        if (recurso instanceof Libro) {
            repositorioLibros.eliminar((Libro) recurso);
        } else if (recurso instanceof Periodico) {
            repositorioPeriodicos.eliminar((Periodico) recurso);
        } else if (recurso instanceof Computador) {
            repositorioComputadores.eliminar((Computador) recurso);
        } else {
            throw new IllegalArgumentException("Tipo de recurso no soportado: " + recurso.getClass().getName());
        }
    }


    /* busca todos los recursos que cumplan con el criterio indicado, puede ser por 
    fecha, nombre, autor, etc (revisar métodos sobreescritos de cada clase que entienda Recurso).
    */
    public Collection<Recurso> buscarRecursos(String criterio) {
        Collection<Recurso> recursosBuscados = new ArrayList<>();
        
        recursosBuscados.addAll(repositorioLibros.buscar(criterio));
        recursosBuscados.addAll(repositorioPeriodicos.buscar(criterio));
        recursosBuscados.addAll(repositorioComputadores.buscar(criterio));
        
        return recursosBuscados;
    }
    

    // devuelve los todos los recursos cargados en el momento.
    public Collection<Recurso> obtenerTodos() {
        Collection<Recurso> recursosBuscados = new ArrayList<>();

        recursosBuscados.addAll(repositorioLibros.obtenerTodos());
        recursosBuscados.addAll(repositorioPeriodicos.obtenerTodos());
        recursosBuscados.addAll(repositorioComputadores.obtenerTodos());

        return recursosBuscados;
    }

}
