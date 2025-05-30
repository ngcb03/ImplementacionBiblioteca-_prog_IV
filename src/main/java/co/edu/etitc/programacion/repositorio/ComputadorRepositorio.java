package co.edu.etitc.programacion.repositorio;

import java.util.Collection;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.etitc.programacion.entidades.Computador;

@Repository // Interfáz repositorio que extiende operaciones CRUD configuradas en base a clase a mapear.
public interface ComputadorRepositorio extends CrudRepository<Computador, Integer> {

    Computador save(Computador recurso);
    void delete(Computador recurso);

    // Consulta personalizada para busqueda por criterio en las columnas nombre, marca, modelo, sistema_operativo y tipo_computador
    @Query("""
        SELECT * FROM COMPUTADOR WHERE 
            LOWER(nombre) LIKE LOWER(CONCAT('%', :criterio, '%')) OR
            LOWER(marca) LIKE LOWER(CONCAT('%', :criterio, '%')) OR
            LOWER(modelo) LIKE LOWER(CONCAT('%', :criterio, '%')) OR
            LOWER(sistema_operativo) LIKE LOWER(CONCAT('%', :criterio, '%')) OR
            LOWER(tipo_computador) LIKE LOWER(CONCAT('%', :criterio, '%'))
    """)
    Collection<Computador> findByCriteria(@Param("criterio") String criterio);

    Collection<Computador> findAll();
    
}
