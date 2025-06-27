package com.perfulandia.usuarios_service.service;

import com.perfulandia.usuarios_service.model.Usuarios;
import com.perfulandia.usuarios_service.model.UsuariosModel;
import com.perfulandia.usuarios_service.repository.UsuariosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServTest {

    @Mock
    private UsuariosRepository repository;

    @InjectMocks
    private UsuariosService service;

    private UsuariosModel usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuarios();
        usuario.setId(1);
        usuario.setNombreCompleto("Juan Pérez");
        usuario.setRunCliente(12345678);
        usuario.setDvRunCLiente("9");
        usuario.setTelefono(987654321);
    }

    @Test
    void testObtenerTodos() {
        when(repository.findAll()).thenReturn(List.of(usuario));

        List<Usuarios> lista = service.obtenerTodos();

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertEquals(1, lista.size());
        assertEquals("Juan Pérez", lista.get(0).getNombre());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testObtenerPorId_Existente() {
        when(repository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuarios> resultado = service.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Juan Pérez", resultado.get().getNombre());

        verify(repository).findById(1L);
    }

    @Test
    void testObtenerPorId_NoExistente() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Optional<Usuarios> resultado = service.obtenerPorId(1L);

        assertFalse(resultado.isPresent());

        verify(repository).findById(1L);
    }

    @Test
    void testGuardar() {
        when(repository.save(any(Usuarios.class))).thenReturn(usuarios);

        Usuarios guardado = service.guardar(usuarios);

        assertNotNull(guardado);
        assertEquals("Juan Pérez", guardado.getNombre());

        verify(repository).save(usuario);
    }

    @Test
    void testEliminar() {
        doNothing().when(repository).deleteById(1L);

        service.eliminar(1L);

        verify(repository).deleteById(1L);
    }
}
