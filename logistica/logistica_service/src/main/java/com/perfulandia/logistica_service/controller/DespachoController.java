package com.perfulandia.logistica_service.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.perfulandia.logistica_service.assembler.DespachoModelAssembler;
import com.perfulandia.logistica_service.model.Despacho;
import com.perfulandia.logistica_service.service.DespachoService;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/despachos")
public class DespachoController {

    private final DespachoService service;
    private final DespachoModelAssembler assembler;

    public DespachoController(DespachoService service, DespachoModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Despacho>>> obtenerTodos() {
        List<EntityModel<Despacho>> despachos = service.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(despachos,
                        linkTo(methodOn(DespachoController.class).obtenerTodos()).withSelfRel())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Despacho>> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<Despacho>> crear(@RequestBody Despacho despacho) {
        Despacho nuevo = service.guardar(despacho);
        return ResponseEntity
                .created(linkTo(methodOn(DespachoController.class).obtenerPorId(nuevo.getId())).toUri())
                .body(assembler.toModel(nuevo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Despacho>> actualizar(@PathVariable Long id, @RequestBody Despacho despacho) {
        return service.obtenerPorId(id)
                .map(existing -> {
                    despacho.setId(id);
                    Despacho actualizado = service.guardar(despacho);
                    return ResponseEntity.ok(assembler.toModel(actualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!service.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
