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

    /**
     * Obtiene todos los campeonatos.
     * 
     * @return ResponseEntity con la lista de campeonatos o un mensaje NOT_FOUND si no hay campeonatos.
     */
    @GetMapping
    public ResponseEntity<?> getAllCampeonatos() {
        List<Campeonato> campeonatos = campeonatoService.getAllCampeonatos();
        
        if (campeonatos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se han encontrado campeonatos"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(campeonatos);
        }
    }

    /**
     * Obtiene un campeonato por su ID.
     * 
     * @param id ID del campeonato a buscar.
     * @return ResponseEntity con el campeonato encontrado o un mensaje NOT_FOUND si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCampeonatoById(@PathVariable Long id) {
        if (campeonatoService.getCampeonatoById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se ha encontrado el campeonato"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(campeonatoService.getCampeonatoById(id));
        }
    }

    /**
     * Crea un nuevo campeonato.
     * 
     * @param campeonatoRequest Objeto con los datos del campeonato a crear.
     * @return ResponseEntity con estado CREATED si se creó correctamente, o BAD_REQUEST si falló.
     */
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

    /**
     * Modifica un campeonato existente.
     * 
     * @param campeonatoRequest Objeto con los datos actualizados del campeonato.
     * @return ResponseEntity con estado OK si se modificó correctamente, o NOT_FOUND si no existe.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> modifyCampeonato(@PathVariable long id ,@RequestBody CampeonatoRequest campeonatoRequest) {
        boolean validar = campeonatoService.modifyCampeonato(
                id,
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

    /**
     * Elimina un campeonato por su ID.
     * 
     * @param request Mapa que contiene el ID del campeonato a eliminar.
     * @return ResponseEntity con estado OK si se eliminó correctamente, o NOT_FOUND si no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCampeonato(@PathVariable Long id) {
        boolean validar = campeonatoService.deleteCampeonato(id);
        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Campeonato eliminado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado el campeonato para eliminar"));
        }
    }
}