package com.perfulandia.inventario.repository;

import com.perfulandia.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    // Buscar por producto
    List<Inventario> findByProductoId(Long productoId);

    // Buscar por sucursal
    List<Inventario> findBySucursalId(Long sucursalId);

    // Buscar por estado
    List<Inventario> findByEstado(String estado);
}
