package co.edu.etitc.programacion.configuracion;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

/* indicamos a Spring que escane automáticamente los componentes 
y repositorios en estas rutas*/

@Configuration
@ComponentScan(basePackages = {
    "co.edu.etitc.programacion.configuracion",
    "co.edu.etitc.programacion",
    "co.edu.etitc.programacion.repositorio"
}) 
@EnableJdbcRepositories(basePackages = "co.edu.etitc.programacion")

// cargamos las propiedades registradas en el archivo application.properties
@PropertySource("classpath:application.properties")
public class ConfiguracionAplicacion {

    @Bean
    public DataSource dataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:file:./db-ejercicio");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
}
