-- ----------------------------
-- 1. CREACIÓN DE LA TABLA CLIENTES
-- ----------------------------
CREATE TABLE clientes (
    id_cliente INTEGER PRIMARY KEY AUTOINCREMENT,
    -- SQLite usa INTEGER PRIMARY KEY para auto-incremento
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100) UNIQUE
);

-- ----------------------------
-- 2. INSERTS para CLIENTES
-- ----------------------------
INSERT INTO clientes (id_cliente, nombre, telefono, email) VALUES
(101, 'Ana García López', '655123456', 'ana.garcia@email.com'),
(102, 'Juan Pérez Ruiz', '677987654', 'juan.perez@email.com'),
(103, 'María Torres Sanz', '688543210', 'maria.torres@email.com'),
(104, 'Carlos Díaz Cruz', '699112233', 'carlos.diaz@email.com');

-- ----------------------------
-- 3. CREACIÓN DE LA TABLA COCHES
-- ----------------------------
CREATE TABLE coches (
    id_coche INTEGER PRIMARY KEY AUTOINCREMENT,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    año SMALLINT NOT NULL,
    -- SMALLINT es aceptado
    bastidor VARCHAR(17) UNIQUE NOT NULL,
    precio REAL NOT NULL,
    -- Usamos REAL para números decimales
    disponible BOOLEAN NOT NULL DEFAULT 1
    -- 1 = TRUE, 0 = FALSE
);

-- ----------------------------
-- 4. INSERTS para COCHES
-- NOTA: Los coches 1, 3 y 5 se marcan como vendidos (disponible=0) ya que aparecen en la tabla VENTAS.
-- ----------------------------
INSERT INTO coches (id_coche, marca, modelo, año, bastidor, precio, disponible) VALUES
(1, 'Toyota', 'Corolla', 2022, 'TY3FJ0017X0123456', 25500.00, 0),
-- Vendido
(2, 'Ford', 'Focus', 2021, 'FR4GH9876K1987654', 18900.00, 1),
(3, 'Volkswagen', 'Golf', 2020, 'VW2AB5544P5432109', 22000.00, 0),
-- Vendido
(4, 'Nissan', 'Qashqai', 2023, 'NS9CD3322R6789012', 31500.00, 1),
(5, 'Audi', 'A4', 2024, 'AD1EF1100S3456789', 45000.00, 0);
-- Vendido

-- ----------------------------
-- 5. CREACIÓN DE LA TABLA VENTAS
-- ----------------------------
CREATE TABLE ventas (
    id_venta INTEGER PRIMARY KEY AUTOINCREMENT,
    id_coche INTEGER UNIQUE NOT NULL,
    -- UNIQUE para la relación uno-a-uno (un coche solo se vende una vez)
    id_cliente INTEGER NOT NULL,
    fecha_venta TEXT NOT NULL,
    -- Usamos TEXT para almacenar la fecha en formato YYYY-MM-DD
    precio_final REAL NOT NULL,
    FOREIGN KEY (id_coche) REFERENCES coches(id_coche),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);

-- ----------------------------
-- 6. INSERTS para VENTAS
-- ----------------------------
INSERT INTO ventas (id_venta, id_coche, id_cliente, fecha_venta, precio_final) VALUES
(1, 3, 101, '2024-05-15', 21500.00),
(2, 1, 102, '2024-06-01', 25000.00),
(3, 5, 104, '2024-06-20', 44500.00);