-- Tabla para Computador
CREATE TABLE COMPUTADOR (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    fecha_ingreso DATE NOT NULL,
    activo BOOLEAN NOT NULL,
    marca VARCHAR(255),
    modelo VARCHAR(255),
    sistema_operativo VARCHAR(255),
    tipo_computador VARCHAR(50)
);

-- Tabla para Libro
CREATE TABLE LIBRO (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    fecha_ingreso DATE NOT NULL,
    activo BOOLEAN NOT NULL,
    autor VARCHAR(255),
    editorial VARCHAR(255),
    anio INT
);

-- Tabla para Periodico
CREATE TABLE PERIODICO (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    fecha_ingreso DATE NOT NULL,
    activo BOOLEAN NOT NULL,
    fecha_publicacion DATE,
    editorial VARCHAR(255)
);
