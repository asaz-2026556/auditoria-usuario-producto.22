DROP DATABASE IF EXISTS auditoria_usuario_producto_in4av;
CREATE DATABASE auditoria_usuario_producto_in4av;
USE auditoria_usuario_producto_in4av;

-- 1. Tabla de Usuarios
CREATE TABLE Users (
    name VARCHAR(50) NOT NULL CHECK(LENGTH(name) <= 50),
    lastname VARCHAR(50) NOT NULL CHECK(LENGTH(lastname) <= 50),
    email VARCHAR(50) NOT NULL CHECK(LENGTH(email) <= 50),
    user VARCHAR(25) NOT NULL CHECK(LENGTH(user) <= 25),
    password VARCHAR(35) NOT NULL CHECK(LENGTH(password) <= 35),
    id_user VARCHAR(36) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY(id_user)
);

-- 2. Tabla de Productos
CREATE TABLE Productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL
);

-- 3. Tabla de Clientes
CREATE TABLE Clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    nit VARCHAR(15) NOT NULL
);

-- 4. Tabla de Ventas
CREATE TABLE Ventas (
    id_venta INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cantidad INT NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    id_cliente INT NOT NULL,
    id_producto INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

-- Procedimiento Almacenado para Crear Usuarios
DELIMITER $$
CREATE PROCEDURE sp_crear_users(
    IN name_p VARCHAR(50),
    IN lastname_p VARCHAR(50),
    IN email_p VARCHAR(50),
    IN user_p VARCHAR(25),
    IN password_p VARCHAR(35)
)
BEGIN
    INSERT INTO Users (name, lastname, email, user, password, id_user)
    VALUES (name_p, lastname_p, email_p, user_p, password_p, UUID());
END $$
DELIMITER ;

-- Datos de Prueba
CALL sp_crear_users("a", "a", "a@", "a", "123");

INSERT INTO Productos (nombre, precio, stock) VALUES
('Leche 1L', 9.50, 50),
('Queso fresco', 22.00, 30),
('Jugo de naranja', 8.75, 40),
('Pan integral', 12.00, 25),
('Detergente 1kg', 28.50, 20);

INSERT INTO Clientes (nombre, nit) VALUES
('Juan Pérez', '1234567-8'),
('María López', '2345678-9'),
('Carlos Méndez', '3456789-0'),
('Ana Gómez', '4567890-1'),
('Luis Ramírez', '5678901-2');

INSERT INTO Ventas (fecha, cantidad, total, id_cliente, id_producto) VALUES
('2026-09-10 10:15:00', 2, 19.00, 1, 1),
('2026-09-11 11:30:00', 1, 22.00, 2, 2),
('2026-09-12 09:45:00', 4, 35.00, 3, 3),
('2026-09-13 16:20:00', 1, 12.00, 4, 4),
('2026-09-14 14:05:00', 2, 57.00, 5, 5);

USE auditoria_usuario_producto_in4av;

-- 1. Crear tabla Clientes si no existe
CREATE TABLE IF NOT EXISTS Clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    nit VARCHAR(15) NOT NULL
);

-- 2. Crear tabla Productos
CREATE TABLE IF NOT EXISTS Productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL
);

-- 3. Crear tabla Ventas
CREATE TABLE IF NOT EXISTS Ventas (
    id_venta INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cantidad INT NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    id_cliente INT NOT NULL,
    id_producto INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

-- Insertar datos iniciales de Clientes
INSERT INTO Clientes (nombre, nit) VALUES
('Juan Pérez', '1234567-8'),
('María López', '2345678-9'),
('Carlos Méndez', '3456789-0');