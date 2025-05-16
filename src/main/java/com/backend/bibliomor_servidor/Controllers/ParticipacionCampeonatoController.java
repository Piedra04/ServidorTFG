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

    /**
     * Obtiene todas las participaciones.
     * 
     * @return ResponseEntity con la lista de participaciones o un mensaje NOT_FOUND
     *         si no hay participaciones.
     */
    @GetMapping
    public ResponseEntity<?> getAllParticipaciones() {
        if (participacionCampeonatoService.getAllParticipaciones().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se han encontrado participaciones"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(participacionCampeonatoService.getAllParticipaciones());
    }

    /**
     * Obtiene una participación por su ID.
     * 
     * @param id ID de la participación a buscar.
     * @return ResponseEntity con la participación encontrada o un mensaje NOT_FOUND
     *         si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getParticipacionById(@PathVariable Long id) {
        if (participacionCampeonatoService.getParticipacionById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado la participación"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(participacionCampeonatoService.getParticipacionById(id));
    }

    /**
     * Crea una nueva participación.
     *
     * @param request Objeto con los datos de la participación a crear (usuario,
     *                campeonato).
     * @return ResponseEntity con estado CREATED si se creó correctamente, o
     *         BAD_REQUEST si falló.
     */
    @PostMapping
    public ResponseEntity<?> createParticipacion(@RequestBody ParticipacionCampeonatoRequest request) {
        boolean validar = participacionCampeonatoService.createParticipacion(request.getUsuario(),
                request.getCampeonato());

        if (validar) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("message", "Participación creada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message",
                            "No se ha podido crear la participación (usuario o campeonato no encontrados)"));
        }
    }

    /**
     * Modifica una participación existente.
     *
     * @param id      ID de la participación a modificar.
     * @param request Objeto con los datos actualizados de la participación
     *                (usuario, campeonato).
     * @return ResponseEntity con estado OK si se modificó correctamente, o
     *         NOT_FOUND si no existe.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> modifyParticipacion(@PathVariable long id,
            @RequestBody ParticipacionCampeonatoRequest request) {
        boolean validar = participacionCampeonatoService.modifyParticipacion(id, request.getUsuario(),
                request.getCampeonato());

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Map.of("message", "Participación modificada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado la participación para modificar"));
        }
    }

    /**
     * Elimina una participación por su ID.
     *
     * @param id ID de la participación a eliminar.
     * @return ResponseEntity con estado OK si se eliminó correctamente, o NOT_FOUND
     *         si no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParticipacion(@PathVariable Long id) {
        boolean validar = participacionCampeonatoService.deleteParticipacion(id);

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Map.of("message", "Participación eliminada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado la participación para eliminar"));
        }
    }
}