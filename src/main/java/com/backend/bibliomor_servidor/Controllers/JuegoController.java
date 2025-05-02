package com.backend.bibliomor_servidor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.bibliomor_servidor.DTOs.JuegoRequest;
import com.backend.bibliomor_servidor.Services.JuegoService;

import java.util.Map;

@RestController
@RequestMapping("/juego")
public class JuegoController {

    @Autowired
    private JuegoService juegoService;

    // Ruta para crear un nuevo juego
    @PostMapping("/crear")
    public ResponseEntity<?> createGame(@RequestBody JuegoRequest gameRequest) {
        boolean validar = juegoService.createGame(gameRequest.getNombre(), gameRequest.getnUnidades());

        if (validar) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Juego creado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "No se ha podido crear el juego (nombre ya registrado)"));
        }
    }

    // Ruta para modificar un juego existente
    @PutMapping("/editar")
    public ResponseEntity<?> modifyGame(@RequestBody JuegoRequest gameRequest) {
        boolean validar = juegoService.modifyGame(gameRequest.getId(), gameRequest.getNombre(), gameRequest.getnUnidades());

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Juego modificado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado el juego para modificar"));
        }
    }

    // Ruta para eliminar un juego por su ID
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> deleteGame(@RequestBody Map<String, Long> request) {
        Long id = request.get("id");
        boolean validar = juegoService.deleteGame(id);

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Juego eliminado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado el juego para eliminar"));
        }
    }
}