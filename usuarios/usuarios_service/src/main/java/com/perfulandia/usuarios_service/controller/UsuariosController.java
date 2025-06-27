package com.perfulandia.usuarios_service.controller;

import com.perfulandia.usuarios_service.UsuariosServiceApplication;
import com.perfulandia.usuarios_service.model.UsuariosModel;
import com.perfulandia.usuarios_service.service.UsuariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping
    public ResponseEntity<List<UsuariosModel>> Listar() {
        List<UsuariosModel> usuariosModel = usuariosService.obtenerTodos();
        if (usuariosModels.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuariosModel);
    }

    @PostMapping
    public ResponseEntity<UsuariosModel> guardar(@RequestBody UsuariosModel usuariosModel){
        UsuariosModel usuariosModel1 = usuariosService.save(usuariosModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosModel1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuariosModel> buscar(@PathVariable Integer id){
        try{
            UsuariosModel usuariosModel = usuariosService.findById(id);
            return ResponseEntity.ok(usuariosModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<UsuariosModel> actualizar(@PathVariable Integer id, @RequestBody UsuariosModel usuariosModel){
        try{
            UsuariosModel user = usuariosService.findById(id);
            user.setId(id);
            user.setNombreCompleto(usuariosModel.getNombreCompleto());
            user.setRunCliente(user.getRunCliente());
            user.setDvRunCLiente(user.getDvRunCLiente());
            user.setCorreo(user.getCorreo());
            user.setTelefono(user.getTelefono());

            usuariosService.save(user);
            return ResponseEntity.ok(usuariosModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try{
            usuariosService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
