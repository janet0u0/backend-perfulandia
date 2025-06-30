package com.perfulandia.logistica_service.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data // Esto genera getters, setters, toString, equals y hashCode
@NoArgsConstructor
@AllArgsConstructor
public class Transportista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String rut;
    private String empresa;
    private String telefono;

    @OneToMany(mappedBy = "transportista", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Despacho> despachos;
}
