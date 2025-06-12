package co.edu.etitc.programacion.presentacion.controlador;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.etitc.programacion.persistencia.entidades.Recurso;
import co.edu.etitc.programacion.servicio.ServicioBiblioteca;

@RestController
@RequestMapping("/api/biblioteca")
/* @CrossOrigin(origins = "*") */
public class BibliotecaControlador {

    private final ServicioBiblioteca servicioBiblioteca;

    @Autowired
    public BibliotecaControlador(ServicioBiblioteca servicioBiblioteca) {
        this.servicioBiblioteca = servicioBiblioteca;
    }

    @GetMapping(produces = "application/json")
    public Collection<Recurso> obtenerRecursos() {
        return servicioBiblioteca.obtenerTodos();
    }

    @GetMapping(
        path = "/{criterio}", 
        produces = "application/json"
        )
    public Collection<Recurso> buscarRercurso(
        @PathVariable("criterio") String criterio) {
            return servicioBiblioteca.buscarRecursos(criterio);
    }

    @PostMapping
    public ResponseEntity<?> agregar(@RequestBody Recurso recurso) {
        servicioBiblioteca.agregar(recurso);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> quitar(@RequestBody Recurso recurso) {
        servicioBiblioteca.quitarRecurso(recurso);
        return ResponseEntity.noContent().build();
    }
    
}
