package co.edu.etitc.programacion.configuracion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InicioApp {

    // obtenemos el valor de la pripiedad "app.name" del application.properties
    @Value("${app.name}")
    private String appName;

    public String nombreAplicacion() {
        return appName;
    }
    
}
