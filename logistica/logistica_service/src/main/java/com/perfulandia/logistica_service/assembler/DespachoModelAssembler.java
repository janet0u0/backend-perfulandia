package com.perfulandia.logistica_service.assembler;

import com.perfulandia.logistica_service.controller.DespachoController;
import com.perfulandia.logistica_service.model.Despacho;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class DespachoModelAssembler implements RepresentationModelAssembler<Despacho, EntityModel<Despacho>> {

    @Override
    public @NonNull EntityModel<Despacho> toModel(@NonNull Despacho despacho) {
        return EntityModel.of(despacho,
                linkTo(methodOn(DespachoController.class).obtenerPorId(despacho.getId())).withSelfRel(),
                linkTo(methodOn(DespachoController.class).obtenerTodos()).withRel("despachos"));
    }
}
