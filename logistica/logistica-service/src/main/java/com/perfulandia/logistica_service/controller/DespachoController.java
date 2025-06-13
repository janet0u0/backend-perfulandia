package com.perfulandia.logistica_service.controller;

import com.perfulandia.logistica_service.model.Despacho;
import com.perfulandia.logistica_service.service.DespachoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despachos")
public class DespachoController {

    private final DespachoService service;

    public DespachoController(DespachoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Despacho>> obtenerTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Despacho> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Despacho> crear(@RequestBody Despacho despacho) {
        return ResponseEntity.ok(service.guardar(despacho));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Despacho> actualizar(@PathVariable Long id, @RequestBody Despacho despacho) {
        if (!service.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        despacho.setId(id);
        return ResponseEntity.ok(service.guardar(despacho));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!service.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
