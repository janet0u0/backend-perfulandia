package com.perfulandia.backend.service;

import com.perfulandia.backend.model.Producto;
import com.perfulandia.backend.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Obtener todos los productos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // Buscar producto por id
    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    // Guardar o actualizar producto
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Eliminar producto por id
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}