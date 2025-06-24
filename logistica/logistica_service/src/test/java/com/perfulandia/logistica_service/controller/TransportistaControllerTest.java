package com.perfulandia.logistica_service.controller;

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
import com.perfulandia.logistica_service.model.Transportista;
import com.perfulandia.logistica_service.service.TransportistaService;

@ExtendWith(MockitoExtension.class)
class TransportistaControllerTest {

    @Mock
    private TransportistaService service;

    @InjectMocks
    private TransportistaController controller;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Transportista transportista;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        transportista = new Transportista();
        transportista.setId(1L);
        transportista.setNombre("Juan Pérez");
        transportista.setRut("12.345.678-9");
        transportista.setEmpresa("Empresa XYZ");
        transportista.setTelefono("987654321");
    }

    @Test
    void testObtenerTodos() throws Exception {
        when(service.obtenerTodos()).thenReturn(List.of(transportista));

        mockMvc.perform(get("/transportistas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan Pérez"));
    }

    @Test
    void testObtenerPorIdExistente() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.of(transportista));

        mockMvc.perform(get("/transportistas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rut").value("12.345.678-9"));
    }

    @Test
    void testObtenerPorIdNoEncontrado() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/transportistas/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCrearTransportista() throws Exception {
        when(service.guardar(any(Transportista.class))).thenReturn(transportista);

        mockMvc.perform(post("/transportistas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transportista)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan Pérez"));
    }

    @Test
    void testActualizarTransportistaExistente() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.of(transportista));
        when(service.guardar(any(Transportista.class))).thenReturn(transportista);

        mockMvc.perform(put("/transportistas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transportista)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.telefono").value("987654321"));
    }

    @Test
    void testActualizarTransportistaNoEncontrado() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/transportistas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transportista)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testEliminarTransportistaExistente() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.of(transportista));
        doNothing().when(service).eliminar(1L);

        mockMvc.perform(delete("/transportistas/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testEliminarTransportistaNoEncontrado() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/transportistas/1"))
                .andExpect(status().isNotFound());
    }
}