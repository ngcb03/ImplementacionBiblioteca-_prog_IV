package co.edu.etitc.programacion.persistencia.repositorio;

import java.util.Collection;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.etitc.programacion.persistencia.entidades.Libro;

@Repository // Interfáz repositorio que extiende operaciones CRUD configuradas en base a clase a mapear.
public interface LibroRepositorio extends CrudRepository<Libro, Integer> {
    
    Libro save(Libro recurso);
    void delete(Libro recurso);
    
    // Consulta personalizada para busqueda por criterio en las columnas nombre, autor, editorial y año
    @Query("""
        SELECT * FROM libro WHERE 
            LOWER(nombre) LIKE LOWER(CONCAT('%', :criterio, '%')) OR
            LOWER(autor) LIKE LOWER(CONCAT('%', :criterio, '%')) OR
            LOWER(editorial) LIKE LOWER(CONCAT('%', :criterio, '%')) OR
            CAST(anio AS VARCHAR) LIKE CONCAT('%', :criterio, '%')
    """)
    Collection<Libro> findByCriteria(@Param("criterio") String criterio);

    Collection<Libro> findAll();
    
}
