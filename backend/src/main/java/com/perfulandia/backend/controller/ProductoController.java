package com.perfulandia.backend.controller;

import com.perfulandia.backend.model.Producto;
import com.perfulandia.backend.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Obtener todos los productos
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.listarProductos();
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo producto
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails) {
        return productoService.obtenerProductoPorId(id)
                .map(producto -> {
                    producto.setNombre(productoDetails.getNombre());
                    producto.setDescripcion(productoDetails.getDescripcion());
                    producto.setPrecio(productoDetails.getPrecio());
                    producto.setStock(productoDetails.getStock());
                    Producto actualizado = productoService.guardarProducto(producto);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (productoService.obtenerProductoPorId(id).isPresent()) {
            productoService.eliminarProducto(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
