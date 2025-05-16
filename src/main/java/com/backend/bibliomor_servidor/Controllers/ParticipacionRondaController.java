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

    /**
     * Obtiene todas las participaciones.
     * 
     * @return ResponseEntity con la lista de participaciones o un mensaje NOT_FOUND si no hay participaciones.
     */
    @GetMapping
    public ResponseEntity<?> getAllParticipaciones() {
        if (participacionRondaService.getAllParticipaciones().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se han encontrado participaciones"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(participacionRondaService.getAllParticipaciones());
    }

    /**
     * Obtiene una participación por su ID.
     * 
     * @param id ID de la participación a buscar.
     * @return ResponseEntity con la participación encontrada o un mensaje NOT_FOUND si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getParticipacionById(@PathVariable Long id) {
        if (participacionRondaService.getParticipacionById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se ha encontrado la participación"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(participacionRondaService.getParticipacionById(id));
    }

    /**
     * Crea una nueva participación.
     * 
     * @param request Objeto con los datos de la participación a crear.
     * @return ResponseEntity con estado CREATED si se creó correctamente, o BAD_REQUEST si falló.
     */
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

    /**
     * Modifica una participación existente.
     * 
     * @param request Objeto con los datos actualizados de la participación.
     * @return ResponseEntity con estado OK si se modificó correctamente, o NOT_FOUND si no existe.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> modifyParticipacion(@PathVariable long id,@RequestBody ParticipacionRondaRequest request) {
        boolean validar = participacionRondaService.modifyParticipacion(
            id, request.getUsuarioId(), request.getRondaId(), request.getResultado()
        );

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Participación modificada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado la participación para modificar"));
        }
    }

    /**
     * Elimina una participación por su ID.
     * 
     * @param request Mapa que contiene el ID de la participación a eliminar.
     * @return ResponseEntity con estado OK si se eliminó correctamente, o NOT_FOUND si no existe.
     */
    @DeleteMapping
    public ResponseEntity<?> deleteParticipacion(@PathVariable Long id) {
        boolean validar = participacionRondaService.deleteParticipacion(id);

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Participación eliminada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado la participación para eliminar"));
        }
    }
}