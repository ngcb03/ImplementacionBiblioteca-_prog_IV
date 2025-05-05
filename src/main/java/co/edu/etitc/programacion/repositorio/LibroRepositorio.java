package co.edu.etitc.programacion.repositorio;

import java.util.Collection;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.etitc.programacion.entidades.Libro;

@Repository
public interface LibroRepositorio extends CrudRepository<Libro, Integer> {
    
    Libro save(Libro recurso);
    void delete(Libro recurso);
    
    @Query("""
        SELECT * FROM libro WHERE 
            LOWER(nombre) LIKE LOWER(CONCAT('%', ?1, '%')) OR
            LOWER(autor) LIKE LOWER(CONCAT('%', ?1, '%')) OR
            LOWER(editorial) LIKE LOWER(CONCAT('%', ?1, '%')) OR
            CAST(anio AS VARCHAR) LIKE CONCAT('%', ?1, '%')
    """)
    Collection<Libro> findByCriteria(String criterio);

    Collection<Libro> findAll();
    
}
