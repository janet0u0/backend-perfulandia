package com.perfulandia.productos_service.controller;

import com.perfulandia.productos_service.assemblers.productoModelAsembler;
import com.perfulandia.productos_service.model.Producto;
import com.perfulandia.productos_service.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @Autowired
    private productoModelAsembler productoModelAsembler;

    @GetMapping
    public List<EntityModel<Producto>> listar() {
        return productoService.listarProductos()
                .stream()
                .map(productoModelAsembler::toModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Producto>> obtener(@PathVariable Long id) {
        return productoService.obtenerProducto(id)
                .map(productoModelAsembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<Producto>> guardarProducto(@RequestBody Producto producto) {
        Producto productoNuevo = productoService.guardarProducto(producto);
        EntityModel<Producto> model = productoModelAsembler.toModel(productoNuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Producto>> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        Producto actualizado = productoService.actualizarProducto(id, producto);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        EntityModel<Producto> model = productoModelAsembler.toModel(actualizado);
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}//l