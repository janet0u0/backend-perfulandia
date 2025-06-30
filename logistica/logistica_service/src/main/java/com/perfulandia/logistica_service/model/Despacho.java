package com.perfulandia.logistica_service.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JsonBackReference  // Indica que esta referencia es la parte "hija", evita recursión
    private Transportista transportista;
}
