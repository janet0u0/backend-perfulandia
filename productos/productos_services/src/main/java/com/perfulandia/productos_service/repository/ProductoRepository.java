package com.perfulandia.productos_service.repository;

import com.perfulandia.productos_service.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

    @Query(value = "SELECT * FROM Item WHERE precio = :precio", nativeQuery = true)
    Producto buscarPorPrecio(@Param("precio") int precio);

    @Query(value = "SELECT * FROM Item WHERE nombre = :nombre", nativeQuery = true)
    Producto buscarPorNombre(@Param("nombre") String nombre);
}
//l