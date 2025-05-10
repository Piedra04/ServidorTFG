package com.backend.bibliomor_servidor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.bibliomor_servidor.DTOs.ParticipacionRondaRequest;
import com.backend.bibliomor_servidor.Services.ParticipacionRondaService;

import java.util.Map;

@RestController
@RequestMapping("/participacion-ronda")
public class ParticipacionRondaController {

    @Autowired
    private ParticipacionRondaService participacionRondaService;

    // Ruta para obtener todas las participaciones
    @GetMapping
    public ResponseEntity<?> getAllParticipaciones() {
        if (participacionRondaService.getAllParticipaciones().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se han encontrado participaciones"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(participacionRondaService.getAllParticipaciones());
    }

    // Ruta para obtener una participación por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getParticipacionById(@PathVariable Long id) {
        if (participacionRondaService.getParticipacionById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se ha encontrado la participación"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(participacionRondaService.getParticipacionById(id));
    }

    // Ruta para crear una nueva participación
    @PostMapping
    public ResponseEntity<?> createParticipacion(@RequestBody ParticipacionRondaRequest request) {
        boolean validar = participacionRondaService.createParticipacion(
            request.getUsuarioId(), request.getRondaId(), request.getResultado()
        );

        if (validar) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Participación creada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "No se ha podido crear la participación (usuario o ronda no encontrados)"));
        }
    }

    // Ruta para modificar una participación existente
    @PutMapping
    public ResponseEntity<?> modifyParticipacion(@RequestBody ParticipacionRondaRequest request) {
        boolean validar = participacionRondaService.modifyParticipacion(
            request.getId(), request.getUsuarioId(), request.getRondaId(), request.getResultado()
        );

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Participación modificada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado la participación para modificar"));
        }
    }

    // Ruta para eliminar una participación por su ID
    @DeleteMapping
    public ResponseEntity<?> deleteParticipacion(@RequestBody Map<String, Long> request) {
        Long id = request.get("id");
        boolean validar = participacionRondaService.deleteParticipacion(id);

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Participación eliminada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado la participación para eliminar"));
        }
    }
}