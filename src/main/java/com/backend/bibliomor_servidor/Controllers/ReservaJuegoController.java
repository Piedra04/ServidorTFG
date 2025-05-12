package com.backend.bibliomor_servidor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.bibliomor_servidor.DTOs.ReservaJuegoRequest;
import com.backend.bibliomor_servidor.Services.ReservaJuegoService;

import java.util.Map;

@RestController
@RequestMapping("/reserva-juego")
public class ReservaJuegoController {

    @Autowired
    private ReservaJuegoService reservaJuegoService;

    /**
     * Obtiene todas las reservas de juegos.
     * 
     * @return ResponseEntity con la lista de reservas o un mensaje NOT_FOUND si no hay reservas.
     */
    @GetMapping
    public ResponseEntity<?> getAllReservas() {
        if (reservaJuegoService.getAllReservas().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se han encontrado reservas"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(reservaJuegoService.getAllReservas());
    }

    /**
     * Crea una nueva reserva de juego.
     * 
     * @param request Objeto con los datos de la reserva a crear.
     * @return ResponseEntity con estado CREATED si se creó correctamente, o BAD_REQUEST si falló.
     */
    @PostMapping
    public ResponseEntity<?> createReserva(@RequestBody ReservaJuegoRequest request) {
        boolean validar = reservaJuegoService.createReserva(
            request.getFecha(), request.getRecreo(), request.getJuegoId(), request.getUsuarioId()
        );

        if (validar) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Reserva creada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "No se ha podido crear la reserva (juego o usuario no encontrados)"));
        }
    }

    /**
     * Modifica una reserva existente.
     * 
     * @param request Objeto con los datos actualizados de la reserva.
     * @return ResponseEntity con estado OK si se modificó correctamente, o NOT_FOUND si no existe.
     */
    @PutMapping
    public ResponseEntity<?> modifyReserva(@RequestBody ReservaJuegoRequest request) {
        boolean validar = reservaJuegoService.modifyReserva(
            request.getId(), request.getFecha(), request.getRecreo(), request.getJuegoId(), request.getUsuarioId()
        );

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Reserva modificada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado la reserva para modificar"));
        }
    }

    /**
     * Elimina una reserva por su ID.
     * 
     * @param request Mapa que contiene el ID de la reserva a eliminar.
     * @return ResponseEntity con estado OK si se eliminó correctamente, o NOT_FOUND si no existe.
     */
    @DeleteMapping
    public ResponseEntity<?> deleteReserva(@PathVariable Long id) {
        boolean validar = reservaJuegoService.deleteReserva(id);

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Reserva eliminada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado la reserva para eliminar"));
        }
    }
}