package com.perfulandia.logistica_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transportista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String rut;
    private String empresa;

    @OneToMany(mappedBy = "transportista", cascade = CascadeType.ALL)
    private List<Despacho> despachos;
}
