package com.perfulandia.logistica_service.assembler;

import com.perfulandia.logistica_service.controller.TransportistaController;
import com.perfulandia.logistica_service.model.Transportista;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TransportistaModelAssembler implements RepresentationModelAssembler<Transportista, EntityModel<Transportista>> {

    @Override
    public @NonNull
    EntityModel<Transportista> toModel(@NonNull Transportista transportista) {
        return EntityModel.of(transportista,
                linkTo(methodOn(TransportistaController.class).obtenerPorId(transportista.getId())).withSelfRel(),
                linkTo(methodOn(TransportistaController.class).obtenerTodos()).withRel("transportistas"));
    }
}
