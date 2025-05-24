package com.aplicarDescuento.aplicarDescuento.service;

import com.aplicarDescuento.aplicarDescuento.model.Descuento;
import com.aplicarDescuento.aplicarDescuento.repository.AplicarDescuentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AplicarDescuentoService {

    @Autowired
    private AplicarDescuentoRepository descuentoRepository;

    // Obtener todos los descuentos
    public List<Descuento> getAllDescuentos() {
        return descuentoRepository.findAll();
    }

    // Obtener un descuento por id
    public Optional<Descuento> getDescuentoById(Long id) {
        return descuentoRepository.findById(id);
    }

    // Guardar o actualizar un descuento
    public Descuento saveOrUpdateDescuento(Descuento descuento) {
        return descuentoRepository.save(descuento);
    }

    // Eliminar un descuento por id
    public void deleteDescuento(Long id) {
        descuentoRepository.deleteById(id);
    }

    // Buscar descuentos con monto fijo menor a un valor dado
    public List<Descuento> findDescuentosByMontoFijoLessThan(double monto) {
        return descuentoRepository.findByMontoFijoLessThan(monto);
    }
}
