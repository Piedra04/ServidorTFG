package com.backend.bibliomor_servidor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.bibliomor_servidor.DTOs.ParticipacionCampeonatoRequest;
import com.backend.bibliomor_servidor.Services.ParticipacionCampeonatoService;

import java.util.Map;

@RestController
@RequestMapping("/participacion-campeonato")
public class ParticipacionCampeonatoController {

    @Autowired
    private ParticipacionCampeonatoService participacionCampeonatoService;

    // Ruta para crear una nueva participación
    @PostMapping("/crear")
    public ResponseEntity<?> createParticipacion(@RequestBody ParticipacionCampeonatoRequest request) {
        boolean validar = participacionCampeonatoService.createParticipacion(request.getUsuarioId(), request.getCampeonatoId());

        if (validar) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Participación creada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "No se ha podido crear la participación (usuario o campeonato no encontrados)"));
        }
    }

    // Ruta para modificar una participación existente
    @PutMapping("/editar")
    public ResponseEntity<?> modifyParticipacion(@RequestBody ParticipacionCampeonatoRequest request) {
        boolean validar = participacionCampeonatoService.modifyParticipacion(request.getId(), request.getUsuarioId(), request.getCampeonatoId());

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Participación modificada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado la participación para modificar"));
        }
    }

    // Ruta para eliminar una participación por su ID
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> deleteParticipacion(@RequestBody Map<String, Long> request) {
        Long id = request.get("id");
        boolean validar = participacionCampeonatoService.deleteParticipacion(id);

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Participación eliminada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado la participación para eliminar"));
        }
    }
}