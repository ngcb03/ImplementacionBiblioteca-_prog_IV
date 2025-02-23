package biblioteca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ServicioBiblioteca {

    private Collection<Recurso> biblioteca;

    public ServicioBiblioteca() {
        biblioteca = new ArrayList<>();
    }

    public void agregar(Recurso recurso) {
        biblioteca.add(recurso);
    }

    public void quitarRecurso(Recurso recurso) {
        biblioteca.remove(recurso);
    }

    public Collection<Recurso> buscarRecursos(String criterio) {
        return biblioteca.stream().filter(
                b -> b.coincideConCriterio(criterio))
                .collect(Collectors.toSet());
    }

    public Collection<Recurso> obtenerTodos() {
        return biblioteca;
    }

}
