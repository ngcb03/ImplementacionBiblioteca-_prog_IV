package co.edu.etitc.programacion.presentacion.controlador;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.etitc.programacion.persistencia.entidades.Recurso;
import co.edu.etitc.programacion.servicio.ServicioBiblioteca;

@RestController
@RequestMapping("/api/biblioteca")
public class BibliotecaControlador {

    private final ServicioBiblioteca servicioBiblioteca;

    @Autowired
    public BibliotecaControlador(ServicioBiblioteca servicioBiblioteca) {
        this.servicioBiblioteca = servicioBiblioteca;
    }

    @GetMapping(
        path = "/todos", 
        produces = "application/json"
        )
    public Collection<Recurso> obtenerRecursos() {
        return servicioBiblioteca.obtenerTodos();
    }

    @GetMapping(
        path = "/buscar-por-criterio/{criterio}", 
        produces = "application/json"
        )
    public Collection<Recurso> buscarRercurso(
        @PathVariable("criterio") String criterio) {
            return servicioBiblioteca.buscarRecursos(criterio);
    }


    
}
