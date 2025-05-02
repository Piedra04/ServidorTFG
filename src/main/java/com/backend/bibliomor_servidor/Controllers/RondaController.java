package com.backend.bibliomor_servidor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.bibliomor_servidor.DTOs.RondaRequest;
import com.backend.bibliomor_servidor.Services.RondaService;

import java.util.Map;

@RestController
@RequestMapping("/ronda")
public class RondaController {

    @Autowired
    private RondaService rondaService;

    // Ruta para crear una nueva ronda
    @PostMapping("/crear")
    public ResponseEntity<?> createRonda(@RequestBody RondaRequest request) {
        boolean validar = rondaService.createRonda(request.getnRonda(), request.getFecha(), request.getCampeonatoId());

        if (validar) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Ronda creada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "No se ha podido crear la ronda (campeonato no encontrado)"));
        }
    }

    // Ruta para modificar una ronda existente
    @PutMapping("/editar")
    public ResponseEntity<?> modifyRonda(@RequestBody RondaRequest request) {
        boolean validar = rondaService.modifyRonda(request.getId(), request.getnRonda(), request.getFecha(), request.getCampeonatoId());

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Ronda modificada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado la ronda para modificar"));
        }
    }

    // Ruta para eliminar una ronda por su ID
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> deleteRonda(@RequestBody Map<String, Long> request) {
        Long id = request.get("id");
        boolean validar = rondaService.deleteRonda(id);

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Ronda eliminada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado la ronda para eliminar"));
        }
    }
}