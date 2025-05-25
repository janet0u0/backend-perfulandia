package com.perfulandia.pedidos_service.repository;

import com.perfulandia.pedidos_service.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoModel, Long>{
}
