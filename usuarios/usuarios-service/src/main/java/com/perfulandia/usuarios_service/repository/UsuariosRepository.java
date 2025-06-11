package com.perfulandia.usuarios_service.repository;

import com.perfulandia.usuarios_service.model.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosModel, Long>{
}
