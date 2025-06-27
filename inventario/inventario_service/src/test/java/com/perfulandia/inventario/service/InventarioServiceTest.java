package com.perfulandia.inventario.service;

import com.perfulandia.inventario.model.Inventario;
import com.perfulandia.inventario.repository.InventarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class InventarioServiceTest {

    private InventarioRepository inventarioRepository;
    private InventarioService inventarioService;

    @BeforeEach
    void setUp() {
        inventarioRepository = mock(InventarioRepository.class);
        inventarioService = new InventarioService(inventarioRepository);
    }

    @Test
    void testObtenerTodo() {
        // Arrange
        Inventario inventario = Inventario.builder()
                .id(1L)
                .productoId(10L)
                .sucursalId(101L)
                .cantidad(5)
                .estado("DISPONIBLE")
                .fechaActualizacion(LocalDateTime.now())
                .build();

        when(inventarioRepository.findAll()).thenReturn(Collections.singletonList(inventario));

        // Act
        List<Inventario> resultado = inventarioService.obtenerTodo();

        // Assert
        assertEquals(1, resultado.size());
        assertEquals(10L, resultado.get(0).getProductoId());
        verify(inventarioRepository, times(1)).findAll();
    }
    @Test
    void testObtenerPorId() {
        // Arrange
        Long idBuscado = 1L;
        Inventario inventario = Inventario.builder()
                .id(idBuscado)
                .productoId(99L)
                .sucursalId(100L)
                .cantidad(10)
                .estado("DISPONIBLE")
                .fechaActualizacion(LocalDateTime.now())
                .build();

        when(inventarioRepository.findById(idBuscado)).thenReturn(Optional.of(inventario));

        // Act
        Optional<Inventario> resultado = inventarioService.obtenerPorId(idBuscado);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(99L, resultado.get().getProductoId());
        verify(inventarioRepository).findById(idBuscado);
    }
    @Test
    void testEliminarPorId() {
        // Arrange
        Long id = 7L;

        // Act
        inventarioService.eliminar(id);

        // Assert
        verify(inventarioRepository, times(1)).deleteById(id);
    }
    @Test
    void testGuardarInventario() {
        // Arrange
        Inventario inventario = Inventario.builder()
                .id(2L)
                .productoId(50L)
                .sucursalId(200L)
                .cantidad(20)
                .estado("STOCK")
                .fechaActualizacion(LocalDateTime.now())
                .build();

        when(inventarioRepository.save(inventario)).thenReturn(inventario);

        // Act
        Inventario resultado = inventarioService.guardar(inventario);

        // Assert
        assertNotNull(resultado);
        assertEquals(50L, resultado.getProductoId());
        verify(inventarioRepository, times(1)).save(inventario);
    }
    @Test
    void testObtenerPorIdNoEncontrado() {
        // Arrange
        Long idInexistente = 999L;
        when(inventarioRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act
        Optional<Inventario> resultado = inventarioService.obtenerPorId(idInexistente);

        // Assert
        assertFalse(resultado.isPresent());
        verify(inventarioRepository, times(1)).findById(idInexistente);
    }




}
