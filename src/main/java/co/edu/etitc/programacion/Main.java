package co.edu.etitc.programacion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import co.edu.etitc.programacion.configuracion.ConfiguracionAplicacion;
import co.edu.etitc.programacion.configuracion.InicioApp;
import co.edu.etitc.programacion.entidades.Computador;
import co.edu.etitc.programacion.entidades.Libro;
import co.edu.etitc.programacion.entidades.Periodico;
import co.edu.etitc.programacion.entidades.Recurso;
import co.edu.etitc.programacion.entidades.enums.TipoComputador;

public class Main {
    public static void main(String[] args) throws Exception {

        // Obtenemos instancia para registrar el bean de la clase ServicioBiblioteca en el contexto de Spring
        var contexto = new AnnotationConfigApplicationContext(ConfiguracionAplicacion.class);

        // Imprimir nombre de la aplicación que viene desde el archivo application.properties
        var inicioApp = contexto.getBean("inicioApp", InicioApp.class);
        System.out.println("\n#####################################################");
        System.out.println("La aplicación se llama -> " + inicioApp.nombreAplicacion());
        System.out.println("#####################################################\n");

        // Colección de recursos libros, periodicos y computadores a cargar en la biblioteca.
        List<Recurso> recursosAgregar = new ArrayList<>(List.of(
            new Libro("Java Programming", LocalDateTime.of(2022, 4, 15, 10, 30), true, "James Gosling", "TechPress", Year.of(2020)),
            new Libro("Clean Code", LocalDateTime.of(2019, 3, 12, 14, 45), true, "Robert C. Martin", "Prentice Hall", Year.of(2008)),
            new Libro("Effective Java", LocalDateTime.of(2020, 5, 23, 9, 15), true, "Joshua Bloch", "Addison-Wesley", Year.of(2018)),
            new Libro("The Pragmatic Programmer", LocalDateTime.of(2021, 8, 7, 11, 0), true, "Andrew Hunt", "Addison-Wesley", Year.of(1999)),
            new Libro("Design Patterns", LocalDateTime.of(2018, 2, 20, 16, 20), true, "Erich Gamma", "Pearson", Year.of(1994)),
            new Libro("Head First Java", LocalDateTime.of(2023, 7, 4, 13, 50), true, "Kathy Sierra", "O'Reilly", Year.of(2005)),
            new Periodico("El Colombiano", LocalDateTime.of(2024, 2, 20, 8, 0), true, LocalDate.of(2024, 2, 20), "Editorial Nacional"),
            new Periodico("El Tiempo", LocalDateTime.of(2023, 12, 30, 7, 45), true, LocalDate.of(2024, 1, 15), "Grupo Editorial Casa"),
            new Periodico("The New York Times", LocalDateTime.of(2024, 3, 10, 6, 30), true, LocalDate.of(2024, 3, 10), "NYT Publishing"),
            new Periodico("Le Monde", LocalDateTime.of(2024, 1, 5, 9, 10), true, LocalDate.of(2024, 1, 5), "Le Monde Group"),
            new Periodico("The Guardian", LocalDateTime.of(2024, 2, 28, 12, 0), true, LocalDate.of(2024, 2, 28), "Guardian Media"),
            new Periodico("El Espectador", LocalDateTime.of(2024, 1, 30, 10, 15), true, LocalDate.of(2024, 1, 30), "Comunican S.A."),
            new Computador("Laptop HP", LocalDateTime.of(2022, 11, 5, 15, 30), true, "HP", "Pavilion", "Windows 11", TipoComputador.PORTATIL),
            new Computador("MacBook Pro", LocalDateTime.of(2023, 1, 22, 11, 45), true, "Apple", "M2 Pro", "macOS Ventura", TipoComputador.PORTATIL),
            new Computador("ThinkPad", LocalDateTime.of(2021, 9, 18, 14, 10), true, "Lenovo", "X1 Carbon", "Windows 11", TipoComputador.PORTATIL),
            new Computador("Asus ROG", LocalDateTime.of(2020, 7, 12, 16, 25), true, "Asus", "Zephyrus G14", "Windows 11", TipoComputador.ESCRITORIO),
            new Computador("iMac", LocalDateTime.of(2022, 3, 8, 13, 35), true, "Apple", "M1", "macOS Monterey", TipoComputador.ESCRITORIO),
            new Computador("Surface Pro", LocalDateTime.of(2023, 6, 30, 17, 50), true, "Microsoft", "9", "Windows 11", TipoComputador.TABLET)
        ));

        // cargamos los recursos a la biblioteca
        var servicioBiblioteca = contexto.getBean("servicioBiblioteca", ServicioBiblioteca.class);
        recursosAgregar.forEach(servicioBiblioteca::agregar);

        // imprimir la lista de recursos que hay actualmente en la biblioteca.
        System.out.println("Recursos en la biblioteca:");
        servicioBiblioteca.obtenerTodos().forEach(System.out::println);

        // buscar los recursos que coincidan con un criterio de búsqueda y los imprimimos.
        String parametroBusqueda = "2022-04-15 10:30";
        System.out.println("\nBuscando recurso por criterio '" + parametroBusqueda + "' en la biblioteca:");
        List<Recurso> recursosBusqueda = new ArrayList<>(servicioBiblioteca.buscarRecursos(parametroBusqueda));
        recursosBusqueda.forEach(System.out::println);

        // Tomar el primer resultado de la búsqueda y los eliminamos de la biblioteca en caso de existir.
        if (!recursosBusqueda.isEmpty()) {
            Recurso recursoAEliminar = recursosBusqueda.get(0);
            servicioBiblioteca.quitarRecurso(recursoAEliminar);
            System.out.println("\nRecurso eliminado de la biblioteca: \n" + recursoAEliminar);
        } else {
            System.out.println("\nNo se encontraron recursos con '" + parametroBusqueda + "' para eliminar.");
        }

        // imprimir la lista con los recursos actualizados.
        System.out.println("\nRecursos en la biblioteca después de la eliminación:");
        servicioBiblioteca.obtenerTodos().forEach(System.out::println);


        // cerramos contexto de Spring para liberar recursos
        contexto.close();

    }

}