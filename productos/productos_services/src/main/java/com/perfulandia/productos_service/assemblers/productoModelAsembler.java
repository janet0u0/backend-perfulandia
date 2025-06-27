package com.perfulandia.productos_service.assemblers;

import com.perfulandia.productos_service.model.Producto;
import com.perfulandia.productos_service.controller.ProductoController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Component
public class productoModelAsembler implements  RepresentationModelAssembler<Producto, EntityModel<Producto>> {

    @Override
    public EntityModel<Producto> toModel(Producto producto) {
        return EntityModel.of(producto,
                linkTo(methodOn(ProductoController.class).obtener(producto.getId())).withSelfRel());
    }
}
