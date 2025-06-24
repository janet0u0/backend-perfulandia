package com.perfulandia.usuarios_service.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 8)
    private Integer runCliente;

    @Column(nullable = false, length = 1)
    private String dvRunCLiente;

    @Column(nullable = false)
    private String nombreCompleto;

    @Column(nullable = true)
    private Integer telefono;

    @Column(nullable = true)
    private String correo;
}
