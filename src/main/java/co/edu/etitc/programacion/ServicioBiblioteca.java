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
    private final RecursoRepositorio<Libro> libroRepositorio;
    private final RecursoRepositorio<Periodico> periodicoRepositorio;
    private final RecursoRepositorio<Computador> computadorRepositorio;

    /*
     * ejemplo práctico en código donde una clase sea inyectada 
     * como dependencia mediante el constructor.
    */
    @Autowired
    public ServicioBiblioteca(RecursoRepositorio<Libro> libroRepositorio,
                              RecursoRepositorio<Periodico> periodicoRepositorio,
                              RecursoRepositorio<Computador> computadorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.periodicoRepositorio = periodicoRepositorio;
        this.computadorRepositorio = computadorRepositorio;
    }

    // agregar un recurso desde el respositorio específico
    public void agregar(Recurso recurso) {
        if (recurso instanceof Libro) {
            libroRepositorio.agregar((Libro) recurso);
        } else if (recurso instanceof Periodico) {
            periodicoRepositorio.agregar((Periodico) recurso);
        } else if (recurso instanceof Computador) {
            computadorRepositorio.agregar((Computador) recurso);
        } else {
            throw new IllegalArgumentException("Tipo de recurso no soportado: " + recurso.getClass().getName());
        }
    }

    // quitar un recurso desde el respositorio específico
    public void quitarRecurso(Recurso recurso) {
        if (recurso instanceof Libro) {
            libroRepositorio.eliminar((Libro) recurso);
        } else if (recurso instanceof Periodico) {
            periodicoRepositorio.eliminar((Periodico) recurso);
        } else if (recurso instanceof Computador) {
            computadorRepositorio.eliminar((Computador) recurso);
        } else {
            throw new IllegalArgumentException("Tipo de recurso no soportado: " + recurso.getClass().getName());
        }
    }


    /* busca todos los recursos que cumplan con el criterio indicado, puede ser por 
    fecha, nombre, autor, etc (revisar métodos sobreescritos de cada clase que entienda Recurso).
    */
    public Collection<Recurso> buscarRecursos(String criterio) {
        Collection<Recurso> recursosBuscados = new ArrayList<>();
        
        recursosBuscados.addAll(libroRepositorio.buscar(criterio));
        recursosBuscados.addAll(periodicoRepositorio.buscar(criterio));
        recursosBuscados.addAll(computadorRepositorio.buscar(criterio));
        
        return recursosBuscados;
    }
    

    // devuelve los todos los recursos cargados en el momento.
    public Collection<Recurso> obtenerTodos() {
        Collection<Recurso> recursosBuscados = new ArrayList<>();

        recursosBuscados.addAll(libroRepositorio.obtenerTodos());
        recursosBuscados.addAll(periodicoRepositorio.obtenerTodos());
        recursosBuscados.addAll(computadorRepositorio.obtenerTodos());

        return recursosBuscados;
    }

}
