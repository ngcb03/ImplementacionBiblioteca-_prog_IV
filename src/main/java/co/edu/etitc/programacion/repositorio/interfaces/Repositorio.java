package co.edu.etitc.programacion.repositorio.interfaces;

import java.util.Collection;

public interface Repositorio<T> {

    Collection<T> buscar(String criterio);
    Collection<T> obtener();
    void agregar(T recurso);
    void eliminar(T recurso);

}
