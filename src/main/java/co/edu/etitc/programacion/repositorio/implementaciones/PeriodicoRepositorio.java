package co.edu.etitc.programacion.repositorio.implementaciones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import co.edu.etitc.programacion.entidades.Periodico;
import co.edu.etitc.programacion.repositorio.interfaces.RecursoRepositorio;

@Component
public class PeriodicoRepositorio implements RecursoRepositorio<Periodico> {

    private List<Periodico> periodicos;

    public PeriodicoRepositorio(){
        periodicos = new ArrayList<>();
    }

    @Override
    public Collection<Periodico> buscar(String criterio) {
        return periodicos.stream().filter(
                b -> b.coincideConCriterio(criterio))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<Periodico> obtenerTodos() {
        return periodicos;
    }

    @Override
    public void agregar(Periodico recurso) {
        periodicos.add(recurso);
    }

    @Override
    public void eliminar(Periodico recurso) {
        periodicos.remove(recurso);
    }
    
}
