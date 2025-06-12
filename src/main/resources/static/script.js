// =========================================================
// Declaramos aquí la URL base de la API (backend Spring Boot)
const API_BASE = "http://localhost:8080/api/biblioteca";

// --------– UTILIDADES PARA FECHAS --------–

/**
 * A partir de un valor de <input type="datetime-local"> (p.ej. "2021-09-18T14:10"),
 * devuelve un array [YYYY, MM, DD, hh, mm] con valores numéricos.
 */
function parseFechaIngreso(inputValue) {
  if (!inputValue) return null;
  // inputValue: "YYYY-MM-DDThh:mm"
  const [fechaParte, horaParte] = inputValue.split("T");
  const [year, month, day] = fechaParte.split("-").map(x => parseInt(x, 10));
  const [hour, minute]    = horaParte.split(":").map(x => parseInt(x, 10));
  return [year, month, day, hour, minute];
}

/**
 * A partir de un valor de <input type="date"> (p.ej. "2021-09-18"),
 * devuelve un array [YYYY, MM, DD] con valores numéricos.
 */
function parseFechaPublicacion(inputValue) {
  if (!inputValue) return null;
  const [year, month, day] = inputValue.split("-").map(x => parseInt(x, 10));
  return [year, month, day];
}

/**
 * Dado un array [YYYY, MM, DD, hh, mm], regresa un string "YYYY-MM-DD hh:mm".
 */
function formatArrayFechaIngreso(arr) {
  if (!Array.isArray(arr) || arr.length < 5) return "";
  const [y, m, d, h, min] = arr;
  const mm = String(m).padStart(2, "0");
  const dd = String(d).padStart(2, "0");
  const hh = String(h).padStart(2, "0");
  const mi = String(min).padStart(2, "0");
  return `${y}-${mm}-${dd} ${hh}:${mi}`;
}

/**
 * Dado un array [YYYY, MM, DD], regresa un string "YYYY-MM-DD".
 */
function formatArrayFechaPublicacion(arr) {
  if (!Array.isArray(arr) || arr.length < 3) return "";
  const [y, m, d] = arr;
  const mm = String(m).padStart(2, "0");
  const dd = String(d).padStart(2, "0");
  return `${y}-${mm}-${dd}`;
}

// Crea un <td> con texto y lo agrega a la fila (<tr>)
function crearCelda(tr, texto) {
  const td = document.createElement("td");
  td.textContent = texto === null || texto === undefined ? "" : texto;
  tr.appendChild(td);
}

// Limpia todo el contenido interior de un nodo dado su id y devuelve el nodo
function limpiarNodo(id) {
  const nodo = document.getElementById(id);
  nodo.innerHTML = "";
  return nodo;
}

// --------– FUNCIONES PRINCIPALES --------–

// 1. Listar todos los recursos
async function obtenerTodosRecursos() {
    const contenedor = limpiarNodo("lista-recursos");
    try {
      const resp = await fetch(`${API_BASE}`);
      if (!resp.ok) {
        throw new Error(`Error ${resp.status}`);
      }
      const datos = await resp.json();
  
      if (datos.length === 0) {
        contenedor.innerHTML = "<p>No hay recursos registrados.</p>";
        return;
      }
  
      // Construcción de la tabla
      const tabla = document.createElement("table");
      const thead = document.createElement("thead");
      const headerRow = document.createElement("tr");
      const encabezados = [
        "ID",
        "Nombre",
        "Fecha Ingreso",
        "Activo",
        "Tipo Recurso",
        "Campos específicos",
        "Acciones"
      ];
      encabezados.forEach(texto => {
        const th = document.createElement("th");
        th.textContent = texto;
        headerRow.appendChild(th);
      });
      thead.appendChild(headerRow);
      tabla.appendChild(thead);
  
      const tbody = document.createElement("tbody");
  
      datos.forEach(recurso => {
        const tr = document.createElement("tr");
        crearCelda(tr, recurso.id);
        crearCelda(tr, recurso.nombre);
  
        let fechaDisplay = "";
        if (Array.isArray(recurso.fechaIngreso)) {
          fechaDisplay = formatArrayFechaIngreso(recurso.fechaIngreso);
        }

        crearCelda(tr, fechaDisplay);
        crearCelda(tr, recurso.activo ? "Sí" : "No");
  
        let tipo = "Desconocido";
        let camposExtra = "";
  
        if (recurso.autor !== undefined) {
          tipo = "Libro";
          camposExtra = `Autor: ${recurso.autor || ""}, Editorial: ${recurso.editorial || ""}, Año: ${recurso.anio || ""}`;
        } else if (Array.isArray(recurso.fechaPublicacion)) {
          tipo = "Periódico";
          const fp = formatArrayFechaPublicacion(recurso.fechaPublicacion);
          camposExtra = `Fecha Publicación: ${fp || ""}, Editorial: ${recurso.editorial || ""}`;
        } else if (recurso.marca !== undefined) {
          tipo = "Computador";
          camposExtra = `Marca: ${recurso.marca || ""}, Modelo: ${recurso.modelo || ""}, SO: ${recurso.sistemaOperativo || ""}, Tipo: ${recurso.tipoComputador || ""}`;
        }
  
        crearCelda(tr, tipo);
        crearCelda(tr, camposExtra);
  
        const tdAcciones = document.createElement("td");
        const btnEliminar = document.createElement("button");
        btnEliminar.textContent = "Eliminar";
        btnEliminar.style.backgroundColor = "#dc3545";
        btnEliminar.style.margin = "0";
        btnEliminar.addEventListener("click", () => {
          eliminarRecurso(recurso.id, tipo.toLowerCase());
        });
        tdAcciones.appendChild(btnEliminar);
        tr.appendChild(tdAcciones);
  
        tbody.appendChild(tr);
      });
  
      tabla.appendChild(tbody);
      contenedor.appendChild(tabla);
    } catch (error) {
      contenedor.innerHTML = `<p style="color:red;">Error al cargar recursos: ${error.message}</p>`;
    }
  }

// 2. Buscar recursos por criterio
async function buscarRecursos() {
  const criterio = document.getElementById("input-criterio").value.trim();
  const contenedor = limpiarNodo("resultado-busqueda");
  if (!criterio) {
    contenedor.innerHTML = "<p>Ingresa un criterio para buscar.</p>";
    return;
  }
  try {
    const resp = await fetch(`${API_BASE}/${encodeURIComponent(criterio)}`);
    if (!resp.ok) {
      throw new Error(`Error ${resp.status}`);
    }
    const datos = await resp.json();
    if (datos.length === 0) {
      contenedor.innerHTML = "<p>No se encontraron recursos.</p>";
      return;
    }

    // Crear tabla parecida a “todos”, pero sin columna de acciones
    const tabla = document.createElement("table");
    const thead = document.createElement("thead");
    const headerRow = document.createElement("tr");
    const encabezados = [
      "ID",
      "Nombre",
      "Fecha Ingreso",
      "Activo",
      "Tipo Recurso",
      "Campos específicos"
    ];
    encabezados.forEach(texto => {
      const th = document.createElement("th");
      th.textContent = texto;
      headerRow.appendChild(th);
    });
    thead.appendChild(headerRow);
    tabla.appendChild(thead);

    const tbody = document.createElement("tbody");
    datos.forEach(recurso => {
      const tr = document.createElement("tr");
      crearCelda(tr, recurso.id);
      crearCelda(tr, recurso.nombre);

      let fechaDisplay = "";
      if (Array.isArray(recurso.fechaIngreso)) {
        fechaDisplay = formatArrayFechaIngreso(recurso.fechaIngreso);
      }
      crearCelda(tr, fechaDisplay);

      crearCelda(tr, recurso.activo);

      let tipo = "Desconocido";
      let camposExtra = "";

      if (recurso.autor !== undefined) {
        tipo = "Libro";
        camposExtra = `Autor: ${recurso.autor || ""}, Editorial: ${recurso.editorial || ""}, Año: ${recurso.anio || ""}`;
      } else if (Array.isArray(recurso.fechaPublicacion)) {
        tipo = "Periódico";
        const fp = formatArrayFechaPublicacion(recurso.fechaPublicacion);
        camposExtra = `Fecha Publicación: ${fp || ""}, Editorial: ${recurso.editorial || ""}`;
      } else if (recurso.marca !== undefined) {
        tipo = "Computador";
        camposExtra = `Marca: ${recurso.marca || ""}, Modelo: ${recurso.modelo || ""}, SO: ${recurso.sistemaOperativo || ""}, Tipo: ${recurso.tipoComputador || ""}`;
      }

      crearCelda(tr, tipo);
      crearCelda(tr, camposExtra);

      tbody.appendChild(tr);
    });

    tabla.appendChild(tbody);
    contenedor.appendChild(tabla);
  } catch (error) {
    contenedor.innerHTML = `<p style="color:red;">Error en búsqueda: ${error.message}</p>`;
  }
}

// 3. Mostrar campos específicos según tipo seleccionado
function mostrarCamposEspecificos() {
    const tipo = document.getElementById("select-tipo").value;
    const cont = limpiarNodo("campos-especificos");
  
    if (tipo === "LIBRO") {
      cont.innerHTML = `
        <label for="autor">Autor</label>
        <input type="text" id="autor" />
  
        <label for="editorial_libro">Editorial</label>
        <input type="text" id="editorial_libro" />
  
        <label for="anio">Año</label>
        <input type="number" id="anio" min="0" />
      `;
    } else if (tipo === "PERIODICO") {
      cont.innerHTML = `
        <label for="fecha_publicacion">Fecha de publicación</label>
        <input type="date" id="fecha_publicacion" />
  
        <label for="editorial_periodico">Editorial</label>
        <input type="text" id="editorial_periodico" />
      `;
    } else if (tipo === "COMPUTADOR") {
      cont.innerHTML = `
        <label for="marca">Marca</label>
        <input type="text" id="marca" />
  
        <label for="modelo">Modelo</label>
        <input type="text" id="modelo" />
  
        <label for="sistema_operativo">Sistema Operativo</label>
        <input type="text" id="sistema_operativo" />
  
        <label for="tipo_computador">Tipo de computador</label>
        <input type="text" id="tipo_computador" />
      `;
    }
  }

// 3.1. Agregar un recurso nuevo (modificado para usar arrays de fecha)
async function agregarRecurso(event) {
    event.preventDefault();
    const tipo = document.getElementById("select-tipo").value; // "LIBRO", "PERIODICO" o "COMPUTADOR"
    const mensajeDiv = document.getElementById("mensaje-agregar");
    mensajeDiv.textContent = "";
  
    if (!tipo) {
      mensajeDiv.innerHTML = '<span style="color:red;">Selecciona un tipo de recurso.</span>';
      return;
    }
  
    const nombre = document.getElementById("nombre").value.trim();
    const fechaIngresoRaw = document.getElementById("fecha_ingreso").value; // "YYYY-MM-DDThh:mm"
    // Obtenemos el valor del select "activo_select", que será "true" o "false" (strings)
    const activoRaw = document.getElementById("activo_select").value;
  
    if (!nombre || !fechaIngresoRaw || !activoRaw) {
      mensajeDiv.innerHTML = '<span style="color:red;">Completa nombre, fecha de ingreso y estado “Activo”.</span>';
      return;
    }
  
    // Convertimos a array [YYYY, MM, DD, hh, mm]
    const fechaIngreso = parseFechaIngreso(fechaIngresoRaw);
  
    // Convertimos el string "true"/"false" a booleano
    const activo = activoRaw === "true";
  
    // Construimos el objeto con el campo "tipo"
    const recurso = {
      tipo: tipo.toLowerCase(), // "libro", "periodico" o "computador"
      nombre,
      fechaIngreso,
      activo
    };
  
    if (tipo === "LIBRO") {
      recurso.autor     = document.getElementById("autor").value.trim() || null;
      recurso.editorial = document.getElementById("editorial_libro").value.trim() || null;
      const anioVal = document.getElementById("anio").value;
      recurso.anio = anioVal ? parseInt(anioVal, 10) : null;
    } else if (tipo === "PERIODICO") {
      const fpRaw = document.getElementById("fecha_publicacion").value; // "YYYY-MM-DD"
      recurso.fechaPublicacion = parseFechaPublicacion(fpRaw);
      recurso.editorial       = document.getElementById("editorial_periodico").value.trim() || null;
    } else if (tipo === "COMPUTADOR") {
      recurso.marca            = document.getElementById("marca").value.trim() || null;
      recurso.modelo           = document.getElementById("modelo").value.trim() || null;
      recurso.sistemaOperativo = document.getElementById("sistema_operativo").value.trim() || null;
      recurso.tipoComputador   = document.getElementById("tipo_computador").value.trim().toUpperCase() || null;
    }
  
    try {
      const resp = await fetch(`${API_BASE}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(recurso)
      });
      if (!resp.ok) {
        throw new Error(`HTTP ${resp.status}`);
      }
      mensajeDiv.innerHTML = '<span style="color:green;">Recurso agregado con éxito.</span>';
      document.getElementById("form-agregar").reset();
      limpiarNodo("campos-especificos");
      obtenerTodosRecursos();
    } catch (error) {
      mensajeDiv.innerHTML = `<span style="color:red;">Error al agregar: ${error.message}</span>`;
    }
  }

// 4. Eliminar recurso por ID y TIPO
async function eliminarRecurso(id, tipo) {
  if (!confirm(`¿Seguro que deseas eliminar el recurso con ID ${id} (tipo: ${tipo})?`)) return;
  try {
    const resp = await fetch(`${API_BASE}`, {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ id, tipo })
    });
    if (resp.status === 204 || resp.ok) {
      obtenerTodosRecursos();
    } else {
      throw new Error(`HTTP ${resp.status}`);
    }
  } catch (error) {
    alert("Error al eliminar recurso: " + error.message);
  }
}

// --------– ESCUCHAR EVENTOS --------–
document.addEventListener("DOMContentLoaded", () => {
    obtenerTodosRecursos();
  
    document.getElementById("btn-refrescar")
      .addEventListener("click", e => {
        e.preventDefault();
        obtenerTodosRecursos();
      });
  
    document.getElementById("btn-buscar")
      .addEventListener("click", e => {
        e.preventDefault();
        buscarRecursos();
      });
  
    document.getElementById("select-tipo")
      .addEventListener("change", mostrarCamposEspecificos);
  
    document.getElementById("form-agregar")
      .addEventListener("submit", agregarRecurso);
  });
