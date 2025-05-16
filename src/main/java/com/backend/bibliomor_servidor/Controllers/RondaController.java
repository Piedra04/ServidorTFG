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

    /**
     * Obtiene todas las rondas.
     * 
     * @return ResponseEntity con la lista de rondas o un mensaje NOT_FOUND si no
     *         hay rondas.
     */
    @GetMapping
    public ResponseEntity<?> getAllRondas() {
        if (rondaService.getAllRondas().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se han encontrado rondas"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(rondaService.getAllRondas());
    }

    /**
     * Obtiene una ronda por su ID.
     * 
     * @param id ID de la ronda a buscar.
     * @return ResponseEntity con la ronda encontrada o un mensaje NOT_FOUND si no
     *         existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getRondaById(@PathVariable Long id) {
        if (rondaService.getRondaById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se ha encontrado la ronda"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(rondaService.getRondaById(id));
    }

    /**
     * Crea una nueva ronda.
     *
     * @param request Objeto con los datos de la ronda a crear (nRonda, fecha,
     *                campeonato).
     * @return ResponseEntity con estado CREATED si se creó correctamente, o
     *         BAD_REQUEST si falló.
     */
    @PostMapping
    public ResponseEntity<?> createRonda(@RequestBody RondaRequest request) {
        boolean validar = rondaService.createRonda(request.getnRonda(), request.getFecha(), request.getCampeonato());

        if (validar) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Ronda creada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "No se ha podido crear la ronda (campeonato no encontrado)"));
        }
    }

    /**
     * Modifica una ronda existente.
     *
     * @param id      ID de la ronda a modificar.
     * @param request Objeto con los datos actualizados de la ronda (nRonda, fecha,
     *                campeonato).
     * @return ResponseEntity con estado OK si se modificó correctamente, o
     *         NOT_FOUND si no existe.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> modifyRonda(@PathVariable long id, @RequestBody RondaRequest request) {
        boolean validar = rondaService.modifyRonda(id, request.getnRonda(), request.getFecha(),
                request.getCampeonato());

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Ronda modificada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado la ronda para modificar"));
        }
    }

    /**
     * Elimina una ronda por su ID.
     *
     * @param id ID de la ronda a eliminar.
     * @return ResponseEntity con estado OK si se eliminó correctamente, o NOT_FOUND
     *         si no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRonda(@PathVariable Long id) {
        boolean validar = rondaService.deleteRonda(id);

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Ronda eliminada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado la ronda para eliminar"));
        }
    }
}