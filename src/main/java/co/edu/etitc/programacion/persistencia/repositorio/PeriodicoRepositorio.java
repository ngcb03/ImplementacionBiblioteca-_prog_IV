package co.edu.etitc.programacion.persistencia.repositorio;

import java.util.Collection;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.etitc.programacion.persistencia.entidades.Periodico;

// Interfáz repositorio que extiende operaciones CRUD configuradas en base a clase a mapear.
@Repository
public interface PeriodicoRepositorio extends CrudRepository<Periodico, Integer> {

    Periodico save(Periodico recurso);
    void delete(Periodico recurso);

    // Consulta personalizada para busqueda por criterio en las columnas nombre, editorial y fecha_publicacion
    @Query("""
        SELECT * FROM periodico WHERE 
            LOWER(nombre) LIKE LOWER(CONCAT('%', :criterio, '%')) OR
            LOWER(editorial) LIKE LOWER(CONCAT('%', :criterio, '%')) OR
            CAST(fecha_publicacion AS VARCHAR) LIKE CONCAT('%', :criterio, '%')
    """)
    Collection<Periodico> findByCriteria(@Param("criterio") String criterio);
    
    Collection<Periodico> findAll();
    
}
