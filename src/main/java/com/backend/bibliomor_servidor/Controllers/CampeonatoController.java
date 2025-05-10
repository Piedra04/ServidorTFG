package com.backend.bibliomor_servidor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.bibliomor_servidor.DTOs.CampeonatoRequest;
import com.backend.bibliomor_servidor.Models.Campeonato;
import com.backend.bibliomor_servidor.Services.CampeonatoService;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/campeonato")
public class CampeonatoController {

    @Autowired
    private CampeonatoService campeonatoService;

    @GetMapping
    public ResponseEntity<?> getAllCampeonatos() {
        List<Campeonato> campeonatos = campeonatoService.getAllCampeonatos();
        
        if (campeonatos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se han encontrado campeonatos"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(campeonatos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCampeonatoById(@PathVariable Long id) {;
        
        if (campeonatoService.getCampeonatoById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se ha encontrado el campeonato"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(campeonatoService.getCampeonatoById(id));
        }
    }

    // Ruta para crear un nuevo campeonato
    @PostMapping
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
    @PutMapping
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
    @DeleteMapping
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