package com.backend.bibliomor_servidor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.bibliomor_servidor.DTOs.GeneroRequest;
import com.backend.bibliomor_servidor.Services.GeneroService;

import java.util.Map;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    /**
     * Obtiene todos los géneros.
     *
     * @return ResponseEntity con la lista de géneros o un mensaje NOT_FOUND si no
     *         hay géneros.
     */
    @GetMapping
    public ResponseEntity<?> getAllGeneros() {
        if (generoService.getAllGeneros().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se han encontrado géneros"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(generoService.getAllGeneros());
    }

    /**
     * Obtiene un género por su ID.
     *
     * @param id ID del género a buscar.
     * @return ResponseEntity con el género encontrado o un mensaje NOT_FOUND si no
     *         existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getGeneroById(@PathVariable Long id) {
        if (generoService.getGeneroById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se ha encontrado el género"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(generoService.getGeneroById(id));
    }

    /**
     * Crea un nuevo género.
     *
     * @param generoRequest Objeto con los datos del género a crear (nombre).
     * @return ResponseEntity con estado CREATED si se creó correctamente, o
     *         BAD_REQUEST si falló.
     */
    @PostMapping
    public ResponseEntity<?> createGenero(@RequestBody GeneroRequest generoRequest) {
        boolean validar = generoService.createGenero(generoRequest.getNombre());

        if (validar) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Género creado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "No se ha podido crear el género (nombre ya registrado)"));
        }
    }

    /**
     * Modifica un género existente.
     *
     * @param id            ID del género a modificar.
     * @param generoRequest Objeto con los datos actualizados del género (nombre).
     * @return ResponseEntity con estado OK si se modificó correctamente, o
     *         NOT_FOUND si no existe.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> modifyGenero(@PathVariable long id, @RequestBody GeneroRequest generoRequest) {
        boolean validar = generoService.modifyGenero(id, generoRequest.getNombre());

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Género modificado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado el género para modificar"));
        }
    }

    /**
     * Elimina un género por su ID.
     *
     * @param id ID del género a eliminar.
     * @return ResponseEntity con estado OK si se eliminó correctamente, o NOT_FOUND
     *         si no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGenero(@PathVariable Long id) {
        boolean validar = generoService.deleteGenero(id);

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Género eliminado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado el género para eliminar"));
        }
    }
}