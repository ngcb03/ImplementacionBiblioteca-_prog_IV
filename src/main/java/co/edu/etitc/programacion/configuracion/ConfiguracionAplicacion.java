package co.edu.etitc.programacion.configuracion;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/* indicamos a Spring que escane automáticamente los componentes 
y repositorios en estas rutas*/

@Configuration
@ComponentScan(basePackages = {
    "co.edu.etitc.programacion.configuracion",
    "co.edu.etitc.programacion",
    "co.edu.etitc.programacion.repositorio"
}) 

// cargamos las propiedades registradas en el archivo application.properties
@PropertySource("classpath:application.properties")
public class ConfiguracionAplicacion { 
    
}
