package com.backend.bibliomor_servidor.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.bibliomor_servidor.Models.ReservaLibro;
import com.backend.bibliomor_servidor.Models.Libro;
import com.backend.bibliomor_servidor.Models.Usuario;
import com.backend.bibliomor_servidor.Repositories.ReservaLibroRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaLibroService {

    @Autowired
    private ReservaLibroRepository reservaLibroRepository;

    /**
     * Obtiene una reserva por su ID.
     * 
     * @param id ID de la reserva a buscar.
     * @return La reserva encontrada o null si no existe.
     */
    public ReservaLibro getReservaById(Long id) {
        return reservaLibroRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene todas las reservas.
     * 
     * @return Lista de todas las reservas registradas.
     */
    public List<ReservaLibro> getAllReservas() {
        return reservaLibroRepository.findAll();
    }

    /**
     * Crea una nueva reserva de libro.
     *
     * @param fechaAdquisicion Fecha de adquisición del libro.
     * @param fechaDevolucion  Fecha de devolución del libro.
     * @param libro            Libro a reservar.
     * @param usuario          Usuario que realiza la reserva.
     * @return true si la reserva se creó correctamente, false si el libro o el
     *         usuario no existen.
     */
    public boolean createReserva(LocalDate fechaAdquisicion, LocalDate fechaDevolucion, Libro libro, Usuario usuario) {

        if (libro != null && usuario != null) {
            ReservaLibro reserva = new ReservaLibro(
                    fechaAdquisicion, fechaDevolucion, libro, usuario);
            reservaLibroRepository.save(reserva);
            return true;
        }
        return false; // Libro o usuario no encontrados
    }

    /**
     * Modifica una reserva existente.
     *
     * @param id               ID de la reserva a modificar.
     * @param fechaAdquisicion Nueva fecha de adquisición del libro.
     * @param fechaDevolucion  Nueva fecha de devolución del libro.
     * @param libro            Nuevo libro a reservar.
     * @param usuario          Nuevo usuario que realiza la reserva.
     * @return true si la reserva se modificó correctamente, false si no se
     *         encontró.
     */
    public boolean modifyReserva(Long id, LocalDate fechaAdquisicion, LocalDate fechaDevolucion, Libro libro,
            Usuario usuario) {
        Optional<ReservaLibro> reservaOpt = reservaLibroRepository.findById(id);

        if (reservaOpt.isPresent()) {
            ReservaLibro reserva = reservaOpt.get();

            if (fechaAdquisicion != null) {
                reserva.setFechaAdquisicion(fechaAdquisicion);
            }

            if (fechaDevolucion != null) {
                reserva.setFechaDevolucion(fechaDevolucion);
            }

            if (libro != null) {
                reserva.setLibro(libro);
            }

            if (usuario != null) {
                reserva.setUsuario(usuario);
            }

            reservaLibroRepository.save(reserva);
            return true;
        }
        return false; // Reserva no encontrada
    }

    /**
     * Elimina una reserva por su ID.
     *
     * @param id ID de la reserva a eliminar.
     * @return true si la reserva se eliminó correctamente, false si no se encontró.
     */
    public boolean deleteReserva(Long id) {
        if (reservaLibroRepository.existsById(id)) {
            reservaLibroRepository.deleteById(id);
            return true;
        }
        return false; // Reserva no encontrada
    }
}