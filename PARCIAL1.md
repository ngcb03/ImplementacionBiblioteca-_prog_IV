# Implementación Biblioteca - Cambios en la rama `trabajo final`

## Integrantes

- Santiago Puentes
- Agudelo Mojonboy
- Nicolas G. Camargo Buelvas


## Cambios hechos

### Objetivo Funcional

Permitir a los usuarios **agregar**, **visualizar** y **eliminar** cada uno de los recursos disponibles en la biblioteca:

- Periódico
- Computador
- Libro

---

### Requisitos Funcionales

#### 1. Controlador REST en Spring

Implementar un controlador REST (`@RestController`) que exponga todos los métodos disponibles en la clase `ServicioBiblioteca`.

El controlador debe permitir:

- **Agregar recursos** (`POST`)
- **Eliminar recursos** (`DELETE`)
- **Listar todos los recursos** (`GET`)

**Requisitos técnicos:**

- El tipo de contenido debe ser `application/json` tanto en peticiones como en respuestas.
- Utilizar las anotaciones adecuadas:
  - `@PostMapping`
  - `@GetMapping`
  - `@DeleteMapping`

---

#### 2. Frontend (HTML o SPA)

Crear una página HTML simple que consuma los servicios REST utilizando `fetch` o una biblioteca similar.

El archivo HTML debe servirse como contenido estático desde el mismo proyecto Java.

Las páginas HTML deben estar disponibles en la siguiente ruta base:

```
/static/
```

🔗 Ejemplo de acceso:  
`http://localhost:8080/static/index.html`

La página debe permitir:

- Visualizar todos los recursos actuales
- Agregar un nuevo recurso (Libro, Periódico o Computador)
- Eliminar un recurso existente

> No se exige diseño visual. Solo se requiere que la interfaz sea funcional.

---

#### 3. (Opcional) SPA con React o Angular

Se permite usar **React** o **Angular**, siempre que:

- Se compile el proyecto.
- El contenido estático generado se sirva desde:

```
http://localhost:8080/static/
```

---

#### 4. Generación de JAR Ejecutable

El proyecto debe generar un archivo **JAR ejecutable** utilizando el plugin:

```xml
<plugin>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
```

Debe poder ejecutarse con el siguiente comando:

```bash
java -jar nombre-del-archivo.jar
```

## Nueva Rama

Esta implementación se encuentra en la rama: `trabajoFinal`.