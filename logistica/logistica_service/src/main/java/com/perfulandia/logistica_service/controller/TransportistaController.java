package com.perfulandia.logistica_service.controller;

import com.perfulandia.logistica_service.model.Transportista;
import com.perfulandia.logistica_service.service.TransportistaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transportistas")
public class TransportistaController {

    private final TransportistaService service;

    public TransportistaController(TransportistaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Transportista>> obtenerTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transportista> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Transportista> crear(@RequestBody Transportista transportista) {
        return ResponseEntity.ok(service.guardar(transportista));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transportista> actualizar(@PathVariable Long id, @RequestBody Transportista transportista) {
        if (!service.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        transportista.setId(id);
        return ResponseEntity.ok(service.guardar(transportista));
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
