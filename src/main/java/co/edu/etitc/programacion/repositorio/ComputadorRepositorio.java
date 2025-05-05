package co.edu.etitc.programacion.repositorio;

import java.util.Collection;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.etitc.programacion.entidades.Computador;

@Repository
public interface ComputadorRepositorio extends CrudRepository<Computador, Integer> {

    Computador save(Computador recurso);
    void delete(Computador recurso);

    @Query("""
        SELECT * FROM computador WHERE 
            LOWER(nombre) LIKE LOWER(CONCAT('%', ?1, '%')) OR
            LOWER(marca) LIKE LOWER(CONCAT('%', ?1, '%')) OR
            LOWER(modelo) LIKE LOWER(CONCAT('%', ?1, '%')) OR
            LOWER(sistema_operativo) LIKE LOWER(CONCAT('%', ?1, '%')) OR
            LOWER(tipo_computador) LIKE LOWER(CONCAT('%', ?1, '%'))
    """)
    Collection<Computador> findByCriteria(String criterio);

    Collection<Computador> findAll();
    
}
