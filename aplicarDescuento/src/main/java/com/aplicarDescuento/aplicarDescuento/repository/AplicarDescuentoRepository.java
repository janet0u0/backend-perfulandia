package com.aplicarDescuento.aplicarDescuento.repository;

import com.aplicarDescuento.aplicarDescuento.model.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AplicarDescuentoRepository extends JpaRepository<Descuento, Long> {

    Descuento findByCodigo(String codigo);
    List<Descuento> findByPorcentajeGreaterThan(double porcentajeMinimo);
    List<Descuento> findByMontoFijoLessThan(double montoMaximo);
    List<Descuento> findByCodigoContaining(String texto);
}
