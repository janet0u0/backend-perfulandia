package com.perfulandia.inventario.controller;


import com.perfulandia.inventario.assemblers.InventarioModelAssembler;
import com.perfulandia.inventario.model.Inventario;
import com.perfulandia.inventario.service.InventarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    private final InventarioService inventarioService;
    private final InventarioModelAssembler assembler;


    public InventarioController(InventarioService inventarioService, InventarioModelAssembler assembler) {
        this.inventarioService = inventarioService;
        this.assembler = assembler;
    }


    // Obtener todo el inventario
    @GetMapping
    public ResponseEntity<List<Inventario>> listarTodo() {
        return ResponseEntity.ok(inventarioService.obtenerTodo());
    }

    // Obtener inventario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerPorId(@PathVariable Long id) {
        return inventarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear o actualizar inventario
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Inventario inventario) {
        try {
            System.out.println("Recibido: " + inventario);
            Inventario guardado = inventarioService.guardar(inventario);
            return ResponseEntity.ok(guardado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    // Eliminar inventario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por producto ID
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Inventario>> buscarPorProductoId(@PathVariable Long productoId) {
        return ResponseEntity.ok(inventarioService.buscarPorProductoId(productoId));
    }

    // Buscar por sucursal ID
    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<Inventario>> buscarPorSucursalId(@PathVariable Long sucursalId) {
        return ResponseEntity.ok(inventarioService.buscarPorSucursalId(sucursalId));
    }

    // Buscar por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Inventario>> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(inventarioService.buscarPorEstado(estado));
    }

    @GetMapping("/{id}/hateoas")
    public ResponseEntity<EntityModel<Inventario>> obtenerPorIdHateoas(@PathVariable Long id) {
        return inventarioService.obtenerPorId(id)
                .map(inventario -> ResponseEntity.ok(assembler.toModel(inventario)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/hateoas")
    public ResponseEntity<CollectionModel<EntityModel<Inventario>>> listarTodoHateoas() {
        List<Inventario> lista = inventarioService.obtenerTodo();

        List<EntityModel<Inventario>> recursos = lista.stream()
                .map(assembler::toModel)
                .toList();

        return ResponseEntity.ok(
                CollectionModel.of(recursos,
                        linkTo(methodOn(InventarioController.class).listarTodoHateoas()).withSelfRel())
        );
    }


}
