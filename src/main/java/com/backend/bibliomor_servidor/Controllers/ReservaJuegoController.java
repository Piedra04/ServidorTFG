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

    // Ruta para crear una nueva reserva
    @PostMapping("/crear")
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

    // Ruta para modificar una reserva existente
    @PutMapping("/editar")
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

    // Ruta para eliminar una reserva por su ID
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> deleteReserva(@RequestBody Map<String, Long> request) {
        Long id = request.get("id");
        boolean validar = reservaJuegoService.deleteReserva(id);

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Reserva eliminada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado la reserva para eliminar"));
        }
    }
}