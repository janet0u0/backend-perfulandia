package com.perfulandia.productos_service.repository;

import com.perfulandia.productos_service.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
}
