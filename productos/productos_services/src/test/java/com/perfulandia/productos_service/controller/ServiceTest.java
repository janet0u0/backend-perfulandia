package com.perfulandia.productos_service.controller;


import com.perfulandia.productos_service.model.Producto;
import com.perfulandia.productos_service.repository.ProductoRepository;
import com.perfulandia.productos_service.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private ProductoService productoService;

    @MockBean
    private ProductoRepository productoRepository;

    @Test
    public void testListarProductos() {
        when(productoRepository.findAll()).thenReturn(List.of(new Producto(1L, "Producto 1", "Marca 1", "Descripción 1", 100.0, 10)));
        List<Producto> productos = productoService.listarProductos();
        assertNotNull(productos);
        assertEquals(1, productos.size());

    }
}
