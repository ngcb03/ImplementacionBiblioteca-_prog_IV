package co.edu.etitc.programacion.repositorio.interfaces;

import java.util.Collection;

import co.edu.etitc.programacion.entidades.Recurso;

public interface RecursoRepositorio<T extends Recurso> {

    Collection<T> buscar(String criterio);
    Collection<T> obtenerTodos();
    void agregar(T recurso);
    void eliminar(T recurso);

}
