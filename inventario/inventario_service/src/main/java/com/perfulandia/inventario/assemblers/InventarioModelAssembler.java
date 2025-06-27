package com.perfulandia.inventario.assemblers;

import com.perfulandia.inventario.controller.InventarioController;
import com.perfulandia.inventario.model.Inventario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<Inventario, EntityModel<Inventario>> {

    @Override
    public EntityModel<Inventario> toModel(Inventario inventario) {
        return EntityModel.of(inventario,
                linkTo(methodOn(InventarioController.class).obtenerPorId(inventario.getId())).withSelfRel(),
                linkTo(methodOn(InventarioController.class).listarTodo()).withRel("todos"),
                linkTo(methodOn(InventarioController.class).buscarPorProductoId(inventario.getProductoId())).withRel("porProductoId"),
                linkTo(methodOn(InventarioController.class).buscarPorSucursalId(inventario.getSucursalId())).withRel("porSucursalId"),
                linkTo(methodOn(InventarioController.class).buscarPorEstado(inventario.getEstado())).withRel("porEstado")
        );
    }
}

