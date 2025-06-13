package com.perfulandia.logistica_service.service;

import com.perfulandia.logistica_service.model.Despacho;
import com.perfulandia.logistica_service.repository.DespachoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DespachoService {

    private final DespachoRepository repository;

    public DespachoService(DespachoRepository repository) {
        this.repository = repository;
    }

    public List<Despacho> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Despacho> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Despacho guardar(Despacho despacho) {
        return repository.save(despacho);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
