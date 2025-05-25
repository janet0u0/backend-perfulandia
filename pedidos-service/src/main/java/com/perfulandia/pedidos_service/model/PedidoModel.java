package com.perfulandia.pedidos_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double total;
    private String estado; // Ej: "pendiente", "confirmado"

    @ElementCollection
    @CollectionTable(name = "pedido_detalles", joinColumns = @JoinColumn(name = "pedido_id"))
    private List<DetallePedido> productos;
}
