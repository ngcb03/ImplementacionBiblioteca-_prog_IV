package co.edu.etitc.programacion.repositorio.implementaciones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import co.edu.etitc.programacion.entidades.Computador;
import co.edu.etitc.programacion.repositorio.interfaces.RecursoRepositorio;

@Component
public class ComputadorRepositorio implements RecursoRepositorio<Computador> {

    private List<Computador> computadores;

    public ComputadorRepositorio() {
        computadores = new ArrayList<>();
    }

    @Override
    public Collection<Computador> buscar(String criterio) {
        return computadores.stream().filter(
                b -> b.coincideConCriterio(criterio))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<Computador> obtenerTodos() {
        return computadores;
    }

    @Override
    public void agregar(Computador recurso) {
        computadores.add(recurso);
    }

    @Override
    public void eliminar(Computador recurso) {
        computadores.remove(recurso);
    }
    
}
