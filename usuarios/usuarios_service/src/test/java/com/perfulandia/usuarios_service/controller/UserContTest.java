package com.perfulandia.usuarios_service.controller;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.usuarios_service.model.UsuariosModel;
import com.perfulandia.usuarios_service.service.UsuariosService;

@ExtendWith(MockitoExtension.class)
class UserContTest {

    @Mock
    private UsuariosService service;

    @InjectMocks
    private UsuariosController controller;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private UsuariosModel usuario;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        usuario = new UsuariosModel();
        usuario.setId(1);
        usuario.setNombreCompleto("Juan Pérez");
        usuario.setRunCliente(12345678);
        usuario.setDvRunCLiente("9");
        usuario.setCorreo("Empresa@XYZ");
        usuario.setTelefono(987654321);
    }

    @Test
    void testObtenerTodos() throws Exception {
        when(service.obtenerTodos()).thenReturn(List.of(usuario));

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre_completo").value("Juan Pérez"));
    }

    @Test
    void testObtenerPorIdExistente() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.run_cliente").value(12345678));
    }

    @Test
    void testObtenerPorIdNoEncontrado() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCrearUsuario() throws Exception {
        when(service.guardar(any(UsuariosModel.class))).thenReturn(usuario);

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre_completo").value("Juan Pérez"));
    }

    @Test
    void testActualizarUsuarioExistente() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.of(usuario));
        when(service.guardar(any(UsuariosModel.class))).thenReturn(usuario);

        mockMvc.perform(put("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.telefono").value("987654321"));
    }

    @Test
    void testActualizarUsuarioNoEncontrado() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testEliminarUsuarioExistente() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.of(usuario));
        doNothing().when(service).eliminar(1L);

        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testEliminarUsuarioNoEncontrado() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isNotFound());
    }
}