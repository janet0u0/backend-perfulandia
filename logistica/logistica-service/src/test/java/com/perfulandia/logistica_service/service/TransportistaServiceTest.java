package com.perfulandia.logistica_service.service;

import com.perfulandia.logistica_service.model.Transportista;
import com.perfulandia.logistica_service.repository.TransportistaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransportistaServiceTest {

    @Mock
    private TransportistaRepository repository;

    @InjectMocks
    private TransportistaService service;

    private Transportista transportista;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transportista = new Transportista();
        transportista.setId(1L);
        transportista.setNombre("Juan Pérez");
        transportista.setRut("12.345.678-9");
        transportista.setTelefono("987654321");
    }

    @Test
    void testObtenerTodos() {
        when(repository.findAll()).thenReturn(List.of(transportista));

        List<Transportista> lista = service.obtenerTodos();

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertEquals(1, lista.size());
        assertEquals("Juan Pérez", lista.get(0).getNombre());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testObtenerPorId_Existente() {
        when(repository.findById(1L)).thenReturn(Optional.of(transportista));

        Optional<Transportista> resultado = service.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Juan Pérez", resultado.get().getNombre());

        verify(repository).findById(1L);
    }

    @Test
    void testObtenerPorId_NoExistente() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Optional<Transportista> resultado = service.obtenerPorId(1L);

        assertFalse(resultado.isPresent());

        verify(repository).findById(1L);
    }

    @Test
    void testGuardar() {
        when(repository.save(any(Transportista.class))).thenReturn(transportista);

        Transportista guardado = service.guardar(transportista);

        assertNotNull(guardado);
        assertEquals("Juan Pérez", guardado.getNombre());

        verify(repository).save(transportista);
    }

    @Test
    void testEliminar() {
        doNothing().when(repository).deleteById(1L);

        service.eliminar(1L);

        verify(repository).deleteById(1L);
    }
}
