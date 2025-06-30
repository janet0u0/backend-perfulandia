package com.perfulandia.logistica_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.perfulandia.logistica_service.model.Transportista;
import com.perfulandia.logistica_service.repository.TransportistaRepository;

@Service
public class TransportistaService {

    private final TransportistaRepository repository;

    public TransportistaService(TransportistaRepository repository) {
        this.repository = repository;
    }

    public List<Transportista> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Transportista> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Transportista guardar(Transportista transportista) {
        return repository.save(transportista);
    }

    public List<Transportista> guardarTodos(List<Transportista> transportistas) {
        return repository.saveAll(transportistas);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
