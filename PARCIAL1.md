# Implementación Biblioteca - Cambios en la rama `parcialSegundoCorte`

## Integrantes

- Santiago Puentes
- Agudelo Mojonboy
- Nicolas G. Camargo Buelvas

## Instrucciones implementadas

Esta rama contiene los cmbios solicitados para adaptar el proyecto de biblioteca usando **Spring Data JDBC**.

---

## Cambios hechos

- Se elimina la herencia en el modelo.
- Se crea una interfaz `Recurso` con los siguientes métodos:
  - `getNombre()`
  - `getFechaIngreso()`
  - `isActivo()`
  - `darDeBaja()`
  - `toString()`
- Las clases `Libro`, `Periodico` y `Computador` implementan la interfaz `Recurso`.
  - Cada clase define:
    - Campo `id` de tipo `Integer`
    - Campos `nombre`, `fechaIngreso`, `activo`
    - Constructor vacío (requerido por Spring Data JDBC)

- Cada entidad tiene su propio repositorio que **extiende `CrudRepository`**.
- Se implementa el método `findByCriteria`, el cual compara el valor dado con **todos los campos de la entidad**.
- Se elimina el método `coincideConCriterio`.

- El servicio `ServicioBiblioteca` ahora utiliza `findByCriteria` en lugar de `coincideConCriterio`.
- La lógica del servicio se mantiene igual.

## Nueva Rama

Esta implementación se encuentra en la rama: `parcialSegundoCorte`.