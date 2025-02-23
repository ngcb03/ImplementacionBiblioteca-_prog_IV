import biblioteca.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 1. Crear una instancia de ServicioBiblioteca.
        ServicioBiblioteca servicioBiblioteca = new ServicioBiblioteca();

        // 2. Agregar al menos 3 recursos de cada tipo definido en el diagrama UML.
        LocalDateTime fechaIngreso = LocalDateTime.now();
        List<Recurso> recursosAgregar = new ArrayList<>(List.of(
                // 📚 Libros
                new Libro("Java Programming", fechaIngreso, true, "James Gosling", "TechPress", Year.of(2020)),
                new Libro("Clean Code", fechaIngreso, true, "Robert C. Martin", "Prentice Hall", Year.of(2008)),
                new Libro("Effective Java", fechaIngreso, true, "Joshua Bloch", "Addison-Wesley", Year.of(2018)),
                new Libro("The Pragmatic Programmer", fechaIngreso, true, "Andrew Hunt", "Addison-Wesley", Year.of(1999)),
                new Libro("Design Patterns", fechaIngreso, true, "Erich Gamma", "Pearson", Year.of(1994)),
                new Libro("Head First Java", fechaIngreso, true, "Kathy Sierra", "O'Reilly", Year.of(2005)),

                // 📰 Periódicos
                new Periodico("El Colombiano", fechaIngreso, true, LocalDate.of(2024, 2, 20), "Editorial Nacional"),
                new Periodico("El Tiempo", fechaIngreso, true, LocalDate.of(2024, 1, 15), "Grupo Editorial Casa"),
                new Periodico("The New York Times", fechaIngreso, true, LocalDate.of(2024, 3, 10), "NYT Publishing"),
                new Periodico("Le Monde", fechaIngreso, true, LocalDate.of(2024, 1, 5), "Le Monde Group"),
                new Periodico("The Guardian", fechaIngreso, true, LocalDate.of(2024, 2, 28), "Guardian Media"),
                new Periodico("El Espectador", fechaIngreso, true, LocalDate.of(2024, 1, 30), "Comunican S.A."),

                // 💻 Computadores
                new Computador("Laptop HP", fechaIngreso, true, "HP", "Pavilion", "Windows 11", TipoComputador.PORTATIL),
                new Computador("MacBook Pro", fechaIngreso, true, "Apple", "M2 Pro", "macOS Ventura", TipoComputador.PORTATIL),
                new Computador("ThinkPad", fechaIngreso, true, "Lenovo", "X1 Carbon", "Windows 11", TipoComputador.PORTATIL),
                new Computador("Asus ROG", fechaIngreso, true, "Asus", "Zephyrus G14", "Windows 11", TipoComputador.ESCRITORIO),
                new Computador("iMac", fechaIngreso, true, "Apple", "M1", "macOS Monterey", TipoComputador.ESCRITORIO),
                new Computador("Surface Pro", fechaIngreso, true, "Microsoft", "9", "Windows 11", TipoComputador.TABLET)
        ));

        recursosAgregar.forEach(servicioBiblioteca::agregar);

        // 3. Imprimir la lista de recursos actual en la biblioteca.
        System.out.println("Recursos en la biblioteca:");
        servicioBiblioteca.obtenerTodos().forEach(System.out::println);

        // 4. Buscar los recursos que coincidan con un criterio de búsqueda parcial e imprimir los resultados.
        String parametroBusqueda = "macOS";
        System.out.println("\nBuscando '" + parametroBusqueda + "':");
        List<Recurso> recursosBusqueda = new ArrayList<>(servicioBiblioteca.buscarRecursos(parametroBusqueda));
        recursosBusqueda.forEach(System.out::println);

        // 5. Tomar el primer resultado de la búsqueda y eliminarlo de la biblioteca si existe.
        if (!recursosBusqueda.isEmpty()) {
            Recurso recursoAEliminar = recursosBusqueda.get(0);
            servicioBiblioteca.quitarRecurso(recursoAEliminar);
            System.out.println("\nEliminado: " + recursoAEliminar);
        } else {
            System.out.println("\nNo se encontraron recursos con '" + parametroBusqueda + "' para eliminar.");
        }

        // 6. Volver a imprimir la lista de recursos actualizados.
        System.out.println("\nRecursos en la biblioteca después de la eliminación:");
        servicioBiblioteca.obtenerTodos().forEach(System.out::println);
    }

}
