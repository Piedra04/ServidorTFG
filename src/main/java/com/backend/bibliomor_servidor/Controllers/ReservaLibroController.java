package com.backend.bibliomor_servidor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.bibliomor_servidor.DTOs.ReservaLibroRequest;
import com.backend.bibliomor_servidor.Services.ReservaLibroService;

import java.util.Map;

@RestController
@RequestMapping("/reserva-libro")
public class ReservaLibroController {

    @Autowired
    private ReservaLibroService reservaLibroService;

    /**
     * Obtiene todas las reservas de libros.
     * 
     * @return ResponseEntity con la lista de reservas o un mensaje NOT_FOUND si no hay reservas.
     */
    @GetMapping
    public ResponseEntity<?> getAllReservas() {
        if (reservaLibroService.getAllReservas().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se han encontrado reservas"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(reservaLibroService.getAllReservas());
    }

    /**
     * Crea una nueva reserva de libro.
     * 
     * @param request Objeto con los datos de la reserva a crear.
     * @return ResponseEntity con estado CREATED si se creó correctamente, o BAD_REQUEST si falló.
     */
    @PostMapping
    public ResponseEntity<?> createReserva(@RequestBody ReservaLibroRequest request) {
        boolean validar = reservaLibroService.createReserva(
            request.getFechaAdquisicion(), request.getFechaDevolucion(), request.getLibroId(), request.getUsuarioId()
        );

        if (validar) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Reserva creada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "No se ha podido crear la reserva (libro o usuario no encontrados)"));
        }
    }

    /**
     * Modifica una reserva existente.
     * 
     * @param request Objeto con los datos actualizados de la reserva.
     * @return ResponseEntity con estado OK si se modificó correctamente, o NOT_FOUND si no existe.
     */
    @PutMapping
    public ResponseEntity<?> modifyReserva(@RequestBody ReservaLibroRequest request) {
        boolean validar = reservaLibroService.modifyReserva(
            request.getId(), request.getFechaAdquisicion(), request.getFechaDevolucion(), request.getLibroId(), request.getUsuarioId()
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
    public ResponseEntity<?> deleteReserva(@RequestBody Map<String, Long> request) {
        Long id = request.get("id");
        boolean validar = reservaLibroService.deleteReserva(id);

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Reserva eliminada correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado la reserva para eliminar"));
        }
    }
}