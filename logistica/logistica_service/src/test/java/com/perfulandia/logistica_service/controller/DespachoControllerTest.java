package com.perfulandia.logistica_service.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.logistica_service.model.Despacho;
import com.perfulandia.logistica_service.service.DespachoService;

@ExtendWith(MockitoExtension.class)
public class DespachoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DespachoService service;

    @InjectMocks
    private DespachoController controller;

    private Despacho despachoEjemplo;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        despachoEjemplo = new Despacho();
        despachoEjemplo.setId(1L);
        despachoEjemplo.setDireccionDestino("Calle Falsa 123");
        despachoEjemplo.setFechaDespacho(LocalDate.of(2025, 6, 21));
        despachoEjemplo.setEstado("En proceso");
    }

    @Test
    void testObtenerTodos() throws Exception {
        when(service.obtenerTodos()).thenReturn(List.of(despachoEjemplo));

        mockMvc.perform(get("/despachos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].direccionDestino").value("Calle Falsa 123"));

        verify(service, times(1)).obtenerTodos();
    }

    @Test
    void testObtenerPorId() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.of(despachoEjemplo));

        mockMvc.perform(get("/despachos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.direccionDestino").value("Calle Falsa 123"));

        verify(service).obtenerPorId(1L);
    }

    @Test
    void testObtenerPorIdNoEncontrado() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/despachos/1"))
                .andExpect(status().isNotFound());

        verify(service).obtenerPorId(1L);
    }

    @Test
    void testCrear() throws Exception {
        when(service.guardar(any(Despacho.class))).thenReturn(despachoEjemplo);

        mockMvc.perform(post("/despachos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(despachoEjemplo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("En proceso"));

        verify(service).guardar(any(Despacho.class));
    }

    @Test
    void testActualizar() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.of(despachoEjemplo));
        when(service.guardar(any(Despacho.class))).thenReturn(despachoEjemplo);

        mockMvc.perform(put("/despachos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(despachoEjemplo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.direccionDestino").value("Calle Falsa 123"));

        verify(service).obtenerPorId(1L);
        verify(service).guardar(any(Despacho.class));
    }

    @Test
    void testActualizarNoEncontrado() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/despachos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(despachoEjemplo)))
                .andExpect(status().isNotFound());

        verify(service).obtenerPorId(1L);
        verify(service, never()).guardar(any());
    }

    @Test
    void testEliminar() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.of(despachoEjemplo));
        doNothing().when(service).eliminar(1L);

        mockMvc.perform(delete("/despachos/1"))
                .andExpect(status().isNoContent());

        verify(service).obtenerPorId(1L);
        verify(service).eliminar(1L);
    }

    @Test
    void testEliminarNoEncontrado() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/despachos/1"))
                .andExpect(status().isNotFound());

        verify(service).obtenerPorId(1L);
        verify(service, never()).eliminar(anyLong());
    }
}
