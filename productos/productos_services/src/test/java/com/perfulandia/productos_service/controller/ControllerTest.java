package com.perfulandia.productos_service.controller;

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
import com.perfulandia.productos_service.model.Producto;
import com.perfulandia.productos_service.service.ProductoService;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {

    @Mock
    private ProductoService service;

    @InjectMocks
    private ProductoController controller;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Producto producto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        producto = Producto.builder()
                .id(1L)
                .nombre("Camiseta Deportiva")
                .marca("Nike")
                .descripcion("Camiseta talla M color rojo")
                .precio(19990.0)
                .stock(50)
                .build();
    }

    @Test
    void testObtenerTodos() throws Exception {
        when(service.obtenerTodos()).thenReturn(List.of(producto));

        mockMvc.perform(get("/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Camiseta Deportiva"));
    }

    @Test
    void testObtenerPorIdExistente() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.of(producto));

        mockMvc.perform(get("/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca").value("Nike"));
    }

    @Test
    void testObtenerPorIdNoEncontrado() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/productos/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCrearProducto() throws Exception {
        when(service.guardar(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Camiseta talla M color rojo"));
    }

    @Test
    void testActualizarProductoExistente() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.of(producto));
        when(service.guardar(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(put("/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stock").value(50));
    }

    @Test
    void testActualizarProductoNoEncontrado() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testEliminarProductoExistente() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.of(producto));
        doNothing().when(service).eliminar(1L);

        mockMvc.perform(delete("/productos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testEliminarProductoNoEncontrado() throws Exception {
        when(service.obtenerPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/productos/1"))
                .andExpect(status().isNotFound());
    }
}
