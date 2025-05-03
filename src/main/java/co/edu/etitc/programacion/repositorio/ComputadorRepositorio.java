package co.edu.etitc.programacion.repositorio;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.etitc.programacion.entidades.Computador;

@Repository
public interface ComputadorRepositorio extends CrudRepository<Computador, Integer> {

    Computador save(Computador recurso);
    void delete(Computador recurso);
    Collection<Computador> findByCriteria(String criterio);
    Collection<Computador> findAll();
    
}
