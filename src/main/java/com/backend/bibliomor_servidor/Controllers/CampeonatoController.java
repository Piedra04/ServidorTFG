package com.backend.bibliomor_servidor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.bibliomor_servidor.DTOs.CampeonatoRequest;
import com.backend.bibliomor_servidor.Services.CampeonatoService;

import java.util.Map;

@RestController
@RequestMapping("/campeonato")
public class CampeonatoController {

    @Autowired
    private CampeonatoService campeonatoService;

    // Ruta para crear un nuevo campeonato
    @PostMapping("/crear")
    public ResponseEntity<?> createCampeonato(@RequestBody CampeonatoRequest campeonatoRequest) {
        boolean validar = campeonatoService.createCampeonato(
                campeonatoRequest.getFechaInicio(),
                campeonatoRequest.getFechaFin(),
                campeonatoRequest.getDescripcion(),
                campeonatoRequest.getJuego(),
                campeonatoRequest.getEstado()
        );

        if (validar) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Campeonato creado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "No se ha podido crear el campeonato"));
        }
    }

    // Ruta para modificar un campeonato existente
    @PutMapping("/editar")
    public ResponseEntity<?> modifyCampeonato(@RequestBody CampeonatoRequest campeonatoRequest) {
        boolean validar = campeonatoService.modifyCampeonato(
                campeonatoRequest.getId(),
                campeonatoRequest.getFechaInicio(),
                campeonatoRequest.getFechaFin(),
                campeonatoRequest.getDescripcion(),
                campeonatoRequest.getJuego(),
                campeonatoRequest.getEstado()
        );

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Campeonato modificado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado el campeonato para modificar"));
        }
    }

    // Ruta para eliminar un campeonato por su ID
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> deleteCampeonato(@RequestBody Map<String, Long> request) {
        Long id = request.get("id");
        boolean validar = campeonatoService.deleteCampeonato(id);

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Campeonato eliminado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado el campeonato para eliminar"));
        }
    }
}