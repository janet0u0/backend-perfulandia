package com.perfulandia.inventario.service;

import com.perfulandia.inventario.model.Inventario;
import com.perfulandia.inventario.repository.InventarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public Inventario agregar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public Inventario actualizarStock(Long id, Integer nuevoStock) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        inventario.setStock(nuevoStock);
        inventario.setRequiereReabastecimiento(nuevoStock < inventario.getStockMinimo());
        return inventarioRepository.save(inventario);
    }

    public void eliminar(Long id) {
        inventarioRepository.deleteById(id);
    }

    public List<Inventario> obtenerTodos() {
        return inventarioRepository.findAll();
    }

    public List<Inventario> productosParaReabastecer() {
        return inventarioRepository.findByRequiereReabastecimientoTrue();
    }
}
