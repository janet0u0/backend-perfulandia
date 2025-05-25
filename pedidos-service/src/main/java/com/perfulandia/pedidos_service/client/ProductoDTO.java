package com.perfulandia.pedidos_service.client;

import lombok.Data;

@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String marca;
    private String descripcion;
    private Double precio;
    private Integer stock;
}
