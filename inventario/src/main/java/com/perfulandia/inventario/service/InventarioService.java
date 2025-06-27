package com.perfulandia.inventario.service;

import com.perfulandia.inventario.model.Inventario;
import com.perfulandia.inventario.repository.InventarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    // Listar todo el inventario
    public List<Inventario> obtenerTodo() {
        return inventarioRepository.findAll();
    }

    // Buscar por ID
    public Optional<Inventario> obtenerPorId(Long id) {
        return inventarioRepository.findById(id);
    }

    // Guardar nuevo registro o actualizar
    public Inventario guardar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    // Eliminar por ID
    public void eliminar(Long id) {
        inventarioRepository.deleteById(id);
    }

    // Buscar por producto
    public List<Inventario> buscarPorProductoId(Long productoId) {
        return inventarioRepository.findByProductoId(productoId);
    }

    // Buscar por sucursal
    public List<Inventario> buscarPorSucursalId(Long sucursalId) {
        return inventarioRepository.findBySucursalId(sucursalId);
    }

    // Buscar por estado
    public List<Inventario> buscarPorEstado(String estado) {
        return inventarioRepository.findByEstado(estado);
    }
}
