package com.perfulandia.usuarios_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.perfulandia.usuarios_service.model.*;
import com.perfulandia.usuarios_service.repository.*;

@Service
public class UsuariosService {

    private final UsuariosRepository repository;

    public UsuariosService(UsuariosRepository repository) {
        this.repository = repository;
    }

    public List<UsuariosModel> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<UsuariosModel> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public UsuariosModel guardar(UsuariosModel usuario) {
        return repository.save(usuario);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}