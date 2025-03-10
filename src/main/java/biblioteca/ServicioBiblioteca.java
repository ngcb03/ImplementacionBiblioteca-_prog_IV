package biblioteca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ServicioBiblioteca {


    // Colección que almacenará el memoria los recursos que se agreguen
    private Collection<Recurso> biblioteca;

    public ServicioBiblioteca() {
        biblioteca = new ArrayList<>();
    }

    // agregar un nuevo recurso a la biblioteca virtual
    public void agregar(Recurso recurso) {
        biblioteca.add(recurso);
    }

    // se quita un recurso de la biblioteca
    public void quitarRecurso(Recurso recurso) {
        biblioteca.remove(recurso);
    }

    /* busca todos los recursos que cumplan con el criterio indicado, puede ser por 
    fecha, nombre, autor, etc (revisar métodos sobreescritos de cada clase que entienda Recurso).
    */
    public Collection<Recurso> buscarRecursos(String criterio) {
        return biblioteca.stream().filter(
                b -> b.coincideConCriterio(criterio))
                .collect(Collectors.toSet());
    }

    // devuelve los todos los recursos cargados en el momento.
    public Collection<Recurso> obtenerTodos() {
        return biblioteca;
    }

}
