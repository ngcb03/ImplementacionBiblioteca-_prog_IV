package co.edu.etitc.programacion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import co.edu.etitc.programacion.configuracion.InicioApp;


@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext contexto = SpringApplication.run(Main.class, args);

        // obtener DataSource
        DataSource dataSource = contexto.getBean(DataSource.class);

        // crear las tablas usando la conexión de la base de datos
        try(Connection connection = dataSource.getConnection()) {
            crearTablas(connection);
        }

        // Imprimir nombre de la aplicación que viene desde el archivo application.properties
        var inicioApp = contexto.getBean("inicioApp", InicioApp.class);
        System.out.println("\n#####################################################");
        System.out.println("La aplicación se llama -> " + inicioApp.nombreAplicacion());
        System.out.println("#### Esperando peticiones en el controlador... ####");
        System.out.println("#####################################################\n");

    }

    // Generar tablas para la base de datos en caso de no ejecutarse el archivo schema.sql cargado desde el Bean definido en el DataSource de la configuración.
    private static void crearTablas(Connection conexion) throws SQLException {
        try {
            conexion.createStatement().execute("""
                CREATE SEQUENCE recurso_seq START WITH 1 INCREMENT BY 1;
            """);
        } catch (SQLException e) {
            if (!e.getMessage().toLowerCase().contains("already exists")) {
                throw e;
            }
        }

        conexion.createStatement().execute("""
            CREATE TABLE IF NOT EXISTS COMPUTADOR (
                id INT DEFAULT NEXT VALUE FOR recurso_seq PRIMARY KEY,
                nombre VARCHAR(255) NOT NULL,
                fecha_ingreso TIMESTAMP NOT NULL,
                activo BOOLEAN NOT NULL,
                marca VARCHAR(255),
                modelo VARCHAR(255),
                sistema_operativo VARCHAR(255),
                tipo_computador VARCHAR(50)
            );
        """);
        
        conexion.createStatement().execute("""
            CREATE TABLE IF NOT EXISTS LIBRO (
                id INT DEFAULT NEXT VALUE FOR recurso_seq PRIMARY KEY,
                nombre VARCHAR(255) NOT NULL,
                fecha_ingreso TIMESTAMP NOT NULL,
                activo BOOLEAN NOT NULL,
                autor VARCHAR(255),
                editorial VARCHAR(255),
                anio INT
            );
        """);
        
        conexion.createStatement().execute("""
            CREATE TABLE IF NOT EXISTS PERIODICO (
                id INT DEFAULT NEXT VALUE FOR recurso_seq PRIMARY KEY,
                nombre VARCHAR(255) NOT NULL,
                fecha_ingreso TIMESTAMP NOT NULL,
                activo BOOLEAN NOT NULL,
                fecha_publicacion DATE,
                editorial VARCHAR(255)
            );
        """);
    
        conexion.commit();
    }
    

}