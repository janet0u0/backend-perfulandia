package com.perfulandia.pedidos_service.service;

import com.perfulandia.pedidos_service.client.ProductoDTO;
import com.perfulandia.pedidos_service.model.PedidoModel;
import com.perfulandia.pedidos_service.model.DetallePedido;
import com.perfulandia.pedidos_service.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private RestTemplate restTemplate;

    public PedidoModel crearPedido(PedidoModel pedido) {
        for (DetallePedido detalle : pedido.getProductos()) {
            Long productoId = detalle.getProductoId();
            String url = "http://localhost:8081/productos/" + productoId;

            try {
                ProductoDTO producto = restTemplate.getForObject(url, ProductoDTO.class);
                if (producto == null) {
                    throw new RuntimeException("Producto no encontrado: ID " + productoId);
                }

                // Validación de stock opcional
                if (detalle.getCantidad() > producto.getStock()) {
                    throw new RuntimeException("Stock insuficiente para producto ID " + productoId);
                }

            } catch (Exception e) {
                throw new RuntimeException("Error al consultar producto ID " + productoId + ": " + e.getMessage());
            }
        }


        pedido.setEstado("pendiente");

        return pedidoRepository.save(pedido);
    }

    public List<PedidoModel> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public PedidoModel obtenerPedido(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }
}
