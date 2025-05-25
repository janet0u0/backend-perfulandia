package com.perfulandia.pedidos_service.controller;

import com.perfulandia.pedidos_service.model.PedidoModel;
import com.perfulandia.pedidos_service.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public PedidoModel crear(@RequestBody PedidoModel pedido) {
        return pedidoService.crearPedido(pedido);
    }

    @GetMapping
    public List<PedidoModel> listar() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public PedidoModel obtener(@PathVariable Long id) {
        return pedidoService.obtenerPedido(id);
    }
}
