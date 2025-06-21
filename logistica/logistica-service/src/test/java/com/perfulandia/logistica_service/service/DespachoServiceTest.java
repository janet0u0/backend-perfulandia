package com.perfulandia.logistica_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.perfulandia.logistica_service.model.Despacho;
import com.perfulandia.logistica_service.repository.DespachoRepository;

class DespachoServiceTest {

    private DespachoRepository repository;
    private DespachoService service;

    private Despacho despacho;

    @BeforeEach
    void setUp() {
        repository = mock(DespachoRepository.class);
        service = new DespachoService(repository);

        despacho = new Despacho();
        despacho.setId(1L);
        despacho.setDireccionDestino("Calle 123");
        despacho.setFechaDespacho(LocalDate.of(2025, 6, 21));
        despacho.setEstado("En proceso");
    }

    @Test
    void testObtenerTodos() {
        when(repository.findAll()).thenReturn(List.of(despacho));

        List<Despacho> resultado = service.obtenerTodos();
        assertEquals(1, resultado.size());
        verify(repository).findAll();
    }

    @Test
    void testObtenerPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(despacho));

        Optional<Despacho> resultado = service.obtenerPorId(1L);
        assertTrue(resultado.isPresent());
        assertEquals("Calle 123", resultado.get().getDireccionDestino());
    }

    @Test
    void testGuardar() {
        when(repository.save(despacho)).thenReturn(despacho);

        Despacho resultado = service.guardar(despacho);
        assertEquals("En proceso", resultado.getEstado());
    }

    @Test
    void testEliminar() {
        doNothing().when(repository).deleteById(1L);
        service.eliminar(1L);
        verify(repository).deleteById(1L);
    }
}
