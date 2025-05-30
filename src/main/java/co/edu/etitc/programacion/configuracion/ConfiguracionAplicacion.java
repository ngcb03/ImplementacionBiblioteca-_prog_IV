package co.edu.etitc.programacion.configuracion;

import java.sql.Connection;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.datasource.init.ScriptUtils;

/* indicar a Spring que escane automáticamente los componentes 
y repositorios en estas rutas*/

@Configuration
@ComponentScan(basePackages = {
    "co.edu.etitc.programacion.configuracion",
    "co.edu.etitc.programacion",
    "co.edu.etitc.programacion.repositorio"
}) 
@EnableJdbcRepositories(basePackages = "co.edu.etitc.programacion.repositorio")

// se cargan las propiedades registradas en el archivo application.properties
@PropertySource("classpath:application.properties")
public class ConfiguracionAplicacion {

    // Bean de configuración para el DataSource.
    @Bean
    public DataSource dataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:file:./db-ejercicio");
        dataSource.setUser("sa");
        dataSource.setPassword("");

        // Ejecutar arhivo schema.sql
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("schema.sql"));
            System.out.println("El archivo schema.sql se ejecutó correctamente desde el bean");
        } catch (Exception e) {
            System.err.println("El archivo schema.sql NO se pudo ejecutar: " + e.getMessage());
        }

        return dataSource;
    }
    
}
