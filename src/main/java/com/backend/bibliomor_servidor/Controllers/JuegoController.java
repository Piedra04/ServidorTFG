package com.backend.bibliomor_servidor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.bibliomor_servidor.DTOs.JuegoRequest;
import com.backend.bibliomor_servidor.Services.JuegoService;

import java.util.Map;

@RestController
@RequestMapping("/juego")
public class JuegoController {

    @Autowired
    private JuegoService juegoService;

    /**
     * Obtiene todos los juegos.
     * 
     * @return ResponseEntity con la lista de juegos o un mensaje NOT_FOUND si no
     *         hay juegos.
     */
    @GetMapping
    public ResponseEntity<?> getAllGames() {
        if (juegoService.getAllGames().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se han encontrado juegos"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(juegoService.getAllGames());
    }

    /**
     * Obtiene un juego por su ID.
     * 
     * @param id ID del juego a buscar.
     * @return ResponseEntity con el juego encontrado o un mensaje NOT_FOUND si no
     *         existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getGameById(@PathVariable Long id) {
        if (juegoService.getGameById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se ha encontrado el juego"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(juegoService.getGameById(id));
    }

    /**
     * Crea un nuevo juego.
     * 
     * @param gameRequest Objeto con los datos del juego a crear.
     * @return ResponseEntity con estado CREATED si se creó correctamente, o
     *         BAD_REQUEST si falló.
     */
    @PostMapping
    public ResponseEntity<?> createGame(@RequestBody JuegoRequest gameRequest) {
        boolean validar = juegoService.createGame(gameRequest.getNombre(), gameRequest.getnUnidades());

        if (validar) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Juego creado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "No se ha podido crear el juego (nombre ya registrado)"));
        }
    }

    /**
     * Modifica un juego existente.
     *
     * @param id          ID del juego a modificar.
     * @param gameRequest Objeto con los datos actualizados del juego (nombre,
     *                    nUnidades).
     * @return ResponseEntity con estado OK si se modificó correctamente, o
     *         NOT_FOUND si no existe.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> modifyGame(@PathVariable long id, @RequestBody JuegoRequest gameRequest) {
        boolean validar = juegoService.modifyGame(id, gameRequest.getNombre(), gameRequest.getnUnidades());

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Juego modificado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado el juego para modificar"));
        }
    }

    /**
     * Elimina un juego por su ID.
     *
     * @param id ID del juego a eliminar.
     * @return ResponseEntity con estado OK si se eliminó correctamente, o NOT_FOUND
     *         si no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable Long id) {
        boolean validar = juegoService.deleteGame(id);

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Juego eliminado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado el juego para eliminar"));
        }
    }
}