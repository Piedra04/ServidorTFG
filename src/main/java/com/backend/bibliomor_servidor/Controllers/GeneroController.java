package com.backend.bibliomor_servidor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.bibliomor_servidor.DTOs.GeneroRequest;
import com.backend.bibliomor_servidor.Services.GeneroService;

import java.util.Map;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public ResponseEntity<?> getAllGeneros() {
        if (generoService.getAllGeneros().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se han encontrado géneros"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(generoService.getAllGeneros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGeneroById(@PathVariable Long id) {
        if (generoService.getGeneroById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se ha encontrado el género"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(generoService.getGeneroById(id));
    }

    // Ruta para crear un nuevo género
    @PostMapping
    public ResponseEntity<?> createGenero(@RequestBody GeneroRequest generoRequest) {
        boolean validar = generoService.createGenero(generoRequest.getNombre());

        if (validar) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Género creado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "No se ha podido crear el género (nombre ya registrado)"));
        }
    }

    // Ruta para modificar un género existente
    @PutMapping
    public ResponseEntity<?> modifyGenero(@RequestBody GeneroRequest generoRequest) {
        boolean validar = generoService.modifyGenero(generoRequest.getId(), generoRequest.getNombre());

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Género modificado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado el género para modificar"));
        }
    }

    // Ruta para eliminar un género por su ID
    @DeleteMapping
    public ResponseEntity<?> deleteGenero(@RequestBody Map<String, Long> request) {
        Long id = request.get("id");
        boolean validar = generoService.deleteGenero(id);

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Género eliminado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado el género para eliminar"));
        }
    }
}