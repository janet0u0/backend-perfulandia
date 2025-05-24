package com.aplicarDescuento.aplicarDescuento.controller;

import com.aplicarDescuento.aplicarDescuento.model.Descuento;
import com.aplicarDescuento.aplicarDescuento.service.AplicarDescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/descuentos")
public class AplicarDescuentoController {

    @Autowired
    private AplicarDescuentoService descuentoService;

    // Obtener todos los descuentos
    @GetMapping
    public List<Descuento> getAllDescuentos() {
        return descuentoService.getAllDescuentos();
    }

    // Obtener un descuento por id
    @GetMapping("/{id}")
    public ResponseEntity<Descuento> getDescuentoById(@PathVariable Long id) {
        Optional<Descuento> descuento = descuentoService.getDescuentoById(id);
        if (descuento.isPresent()) {
            return ResponseEntity.ok(descuento.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo descuento
    @PostMapping
    public Descuento createDescuento(@RequestBody Descuento descuento) {
        return descuentoService.saveOrUpdateDescuento(descuento);
    }

    // Actualizar un descuento existente
    @PutMapping("/{id}")
    public ResponseEntity<Descuento> updateDescuento(@PathVariable Long id, @RequestBody Descuento descuentoDetails) {
        Optional<Descuento> optionalDescuento = descuentoService.getDescuentoById(id);
        if (optionalDescuento.isPresent()) {
            Descuento descuento = optionalDescuento.get();
            descuento.setCodigo(descuentoDetails.getCodigo());
            descuento.setPorcentaje(descuentoDetails.getPorcentaje());
            descuento.setMontoFijo(descuentoDetails.getMontoFijo());
            Descuento updatedDescuento = descuentoService.saveOrUpdateDescuento(descuento);
            return ResponseEntity.ok(updatedDescuento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un descuento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDescuento(@PathVariable Long id) {
        Optional<Descuento> descuento = descuentoService.getDescuentoById(id);
        if (descuento.isPresent()) {
            descuentoService.deleteDescuento(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
