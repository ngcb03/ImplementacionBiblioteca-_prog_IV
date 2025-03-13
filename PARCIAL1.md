# Implementación de Spring en un Proyecto Maven

 ## 1.  Explique la diferencia entre @Component y @Configuration en Spring.
  ### **definición de @Component:** Es una anotación que sirve como una anotación genérica para que cualquier clase pueda detectarse en el área de Spring a un Beans; como lo seria con un sistema o modelo Singleton

  ### **definición de @Configuration:** Sirve como fuentes de definiciones permitiendo a su vez el llamado de otros métodos de la misma clase

  ### **Diferencias:** mientras que @Component proporciona Beans (lo cual es un objeto que se puede instanciar) mientras que @Configuration solo esta para recibir el escaneo que hace por defecto Spring para encontrar una clase correspondiente a la anotación @Configuration

 ## 2. Muestre un ejemplo práctico en código donde una clase sea inyectada como dependencia mediante el constructor. 
  ### R. El ejemplo práctico se puede ver en la línea 31 de clase ServicioBiblioteca, donde la inyección de dependencias se realiza mediante su constructor.

 ```java
@Component
public class ServicioBiblioteca {

    private final Repositorio<Libro> libroRepositorio;
    private final Repositorio<Periodico> periodicoRepositorio;
    private final Repositorio<Computador> computadorRepositorio;

    @Autowired
    public ServicioBiblioteca(Repositorio<Libro> libroRepositorio,
                              Repositorio<Periodico> periodicoRepositorio,
                              Repositorio<Computador> computadorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.periodicoRepositorio = periodicoRepositorio;
        this.computadorRepositorio = computadorRepositorio;
    }
}
```

# Uso de Repositorios Genéricos en Java
  ## 3. Explique el principio de inversión de control y cómo se aplica en esta solución.
    El principio de inversión de control básicamente es un sistema que tiene Spring que al trabajar con una biblioteca esta opción de inversión de control hace que la propia biblioteca invoque el código del usuario; esto tiene cabida a la propia inyección de dependencias por consecuente se implementa un container para la gestión de las instancias
    para la aplicación con respecto al código podemos realizar una creación de objetos y su eliminación dirigida a los objetos del usuario.

### Configuración de Spring y Lectura de Propiedades desde application.properties
  ## 4. Explique cómo funciona la inyección de propiedades en Spring utilizando @Value y qué precedencia tiene cada fuente.
    Esta anotación en una forma de mover valores que son de configuración como (credenciales, bases de datos) fuera del código principal o código fuente y colocarlo en un archivo externo, esto genera una mayor flexibilidad y la inyección de múltiples fuentes creando una adaptabilidad en diferentes entornos como (desarrollo, producción, pruebas).
 
  >Propiedades definidas en línea de comandos (--propiedad=valor):

  >>Tienen la mayor precedencia.

  >>>Ejemplo: java -jar miApp.jar --mi.propiedad=valor.

  >Propiedades del sistema (System.getProperties()):

  >>Definidas con -D en la línea de comandos.

  >>>Ejemplo: java -Dmi.propiedad=valor -jar miApp.jar.

  >Variables de entorno:

  >>Propiedades definidas en el entorno del sistema operativo.

  >>>Ejemplo: export MI_PROPIEDAD=valor.

  Archivos de configuración externos (application.properties o application.yml):

  Archivos ubicados en el sistema de archivos o en el classpath.

  Ejemplo: application.properties con mi.propiedad=valor.

  Propiedades por defecto en el código:

  Valores predeterminados definidos en el código usando @Value.

  Ejemplo: @Value("${mi.propiedad:valorPredeterminado}").
