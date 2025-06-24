package com.perfulandia.inventario.controller;

import com.perfulandia.inventario.model.Inventario;
import com.perfulandia.inventario.service.InventarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {
    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @PostMapping
    public Inventario agregar(@RequestBody Inventario inventarioModel) {
        return inventarioService.agregar(inventarioModel);
    }

    @PutMapping("/{id}/stock")
    public Inventario actualizarStock(@PathVariable Long id, @RequestParam Integer stock) {
        return inventarioService.actualizarStock(id, stock);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        inventarioService.eliminar(id);
    }

    @GetMapping
    public List<Inventario> listar() {
        return inventarioService.obtenerTodos();
    }

    @GetMapping("/reabastecer")
    public List<Inventario> listarParaReabastecer() {
        return inventarioService.productosParaReabastecer();
    }
}
