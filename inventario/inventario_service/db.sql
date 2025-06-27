-- Seleccionar base de datos
USE db_inventario;

-- Crear tabla (si aún no existe)
CREATE TABLE IF NOT EXISTS inventario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    producto_id BIGINT NOT NULL,
    sucursal_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    estado VARCHAR(20) NOT NULL,
    fecha_actualizacion DATETIME NOT NULL
);

-- Limpiar tabla antes de insertar (opcional)
-- TRUNCATE TABLE inventario;

-- Insertar datos de prueba
INSERT INTO inventario (producto_id, sucursal_id, cantidad, estado, fecha_actualizacion) VALUES
(1, 101, 50, 'DISPONIBLE', '2025-06-11 16:30:00'),
(2, 101, 0, 'AGOTADO', '2025-06-10 14:15:00'),
(3, 102, 10, 'BAJO_STOCK', '2025-06-09 11:45:00'),
(4, 103, 100, 'DISPONIBLE', '2025-06-08 09:00:00'),
(5, 104, 5, 'BAJO_STOCK', '2025-06-07 17:20:00'),
(6, 105, 0, 'AGOTADO', '2025-06-06 13:10:00'),
(7, 101, 75, 'DISPONIBLE', '2025-06-05 08:00:00'),
(8, 102, 1, 'BAJO_STOCK', '2025-06-04 22:30:00'),
(9, 103, 0, 'AGOTADO', '2025-06-03 12:12:00'),
(10, 104, 60, 'DISPONIBLE', '2025-06-02 15:00:00');
inventario
