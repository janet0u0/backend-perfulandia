package com.perfulandia.usuarios_service.service;

import com.perfulandia.usuarios_service.model.UsuariosModel;
import com.perfulandia.usuarios_service.repository.UsuariosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    public List<UsuariosModel> findAll(){
        return usuariosRepository.findAll();
    }

    public UsuariosModel findById(long id){
        return usuariosRepository.findById(id).get();
    }

    public UsuariosModel save(UsuariosModel usuariosModel){
        return usuariosRepository.save(usuariosModel);
    }

    public void delete(Long id){
        usuariosRepository.deleteById(id);
    }
}
