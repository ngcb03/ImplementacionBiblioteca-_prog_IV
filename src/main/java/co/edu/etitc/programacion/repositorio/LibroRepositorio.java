package co.edu.etitc.programacion.repositorio;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.etitc.programacion.entidades.Libro;

@Repository
public interface LibroRepositorio extends CrudRepository<Libro, Integer> {
    
    Libro save(Libro recurso);
    void delete(Libro recurso);
    Collection<Libro> findByCriteria(String criterio);
    Collection<Libro> findAll();
    
}
