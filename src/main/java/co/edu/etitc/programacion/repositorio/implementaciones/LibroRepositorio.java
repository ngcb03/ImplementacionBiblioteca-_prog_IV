package co.edu.etitc.programacion.repositorio.implementaciones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import co.edu.etitc.programacion.entidades.Libro;
import co.edu.etitc.programacion.repositorio.interfaces.Repositorio;

@Component
public class LibroRepositorio implements Repositorio<Libro> {

    private List<Libro> libros;

    public LibroRepositorio() {
        libros = new ArrayList<>();
    }

    @Override
    public Collection<Libro> buscar(String criterio) {
        return libros.stream().filter(
                b -> b.coincideConCriterio(criterio))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<Libro> obtener() {
        return libros;
    }

    @Override
    public void agregar(Libro recurso) {
        libros.add(recurso);
    }

    @Override
    public void eliminar(Libro recurso) {
        libros.remove(recurso);
    }
    
    
}
