package com.perfulandia.logistica_service.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.perfulandia.logistica_service.assembler.TransportistaModelAssembler;
import com.perfulandia.logistica_service.model.Transportista;
import com.perfulandia.logistica_service.service.TransportistaService;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/transportistas")
public class TransportistaController {

    private final TransportistaService service;
    private final TransportistaModelAssembler assembler;

    public TransportistaController(TransportistaService service, TransportistaModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Transportista>>> obtenerTodos() {
        List<EntityModel<Transportista>> transportistas = service.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(transportistas,
                        linkTo(methodOn(TransportistaController.class).obtenerTodos()).withSelfRel())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Transportista>> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<Transportista>> crear(@RequestBody Transportista transportista) {
        Transportista nuevo = service.guardar(transportista);
        return ResponseEntity
                .created(linkTo(methodOn(TransportistaController.class).obtenerPorId(nuevo.getId())).toUri())
                .body(assembler.toModel(nuevo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Transportista>> actualizar(@PathVariable Long id, @RequestBody Transportista transportista) {
        return service.obtenerPorId(id)
                .map(existing -> {
                    transportista.setId(id);
                    return ResponseEntity.ok(assembler.toModel(service.guardar(transportista)));
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
