package com.perfulandia.pedidos_service.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePedido {
    private Long productoId;
    private Integer cantidad;
}
