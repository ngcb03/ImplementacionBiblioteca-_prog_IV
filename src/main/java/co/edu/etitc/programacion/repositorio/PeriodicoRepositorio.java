package co.edu.etitc.programacion.repositorio;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.etitc.programacion.entidades.Periodico;

@Repository
public interface PeriodicoRepositorio extends CrudRepository<Periodico, Integer> {

    Periodico save(Periodico recurso);
    void delete(Periodico recurso);
    Collection<Periodico> findByCriteria(String criterio);
    Collection<Periodico> findAll();
    
}
