package com.perfulandia.logistica_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Despacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direccionDestino;
    private LocalDate fechaDespacho;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "transportista_id")
    private Transportista transportista;
}
