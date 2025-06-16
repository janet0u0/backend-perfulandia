package com.perfulandia.usuarios_service.controller;

import com.perfulandia.usuarios_service.model.UsuariosModel;
import com.perfulandia.usuarios_service.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;


public class UsuariosController {

    package com.perfulandia.usuarios_service.controller;

import com.perfulandia.usuarios_service.model.UsuariosModel;
import com.perfulandia.usuarios_service.service.UsuariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/usuarios")
    public class UsuariosController {

        @Autowired
        private UsuariosService usuariosService;

        @GetMapping
        public List<UsuariosModel> listar() {
            return usuariosService.listarUsuarios();
        }

        @GetMapping("/{id}")
        public ResponseEntity<UsuariosModel> obtener(@PathVariable Long id) {
            return usuariosService.obtenerUsuario(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping
        public UsuariosModel crear(@RequestBody UsuariosModel usuario) {
            return usuariosService.guardarUsuario(usuario);
        }

        @PutMapping("/{id}")
        public ResponseEntity<UsuariosModel> actualizar(@PathVariable Long id, @RequestBody UsuariosModel usuario) {
            UsuariosModel actualizado = usuariosService.actualizarUsuario(id, usuario);
            if (actualizado == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(actualizado);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminar(@PathVariable Long id) {
            usuariosService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        }
    }


}
