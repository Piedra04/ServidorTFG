package com.backend.bibliomor_servidor.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.bibliomor_servidor.Models.ReservaLibro;
import com.backend.bibliomor_servidor.Models.Libro;
import com.backend.bibliomor_servidor.Models.Usuario;
import com.backend.bibliomor_servidor.Repositories.ReservaLibroRepository;
import com.backend.bibliomor_servidor.Repositories.LibroRepository;
import com.backend.bibliomor_servidor.Repositories.UsuarioRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReservaLibroService {

    @Autowired
    private ReservaLibroRepository reservaLibroRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para crear una nueva reserva de libro
    public boolean createReserva(LocalDate fechaAdquisicion, LocalDate fechaDevolucion, String libroId, Long usuarioId) {
        Optional<Libro> libroOpt = libroRepository.findById(libroId);
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);

        if (libroOpt.isPresent() && usuarioOpt.isPresent()) {
            ReservaLibro reserva = new ReservaLibro(
                fechaAdquisicion, fechaDevolucion, libroOpt.get(), usuarioOpt.get()
            );
            reservaLibroRepository.save(reserva);
            return true;
        }
        return false; // Libro o usuario no encontrados
    }

    // Método para modificar una reserva existente
    public boolean modifyReserva(Long id, LocalDate fechaAdquisicion, LocalDate fechaDevolucion, String libroId, Long usuarioId) {
        Optional<ReservaLibro> reservaOpt = reservaLibroRepository.findById(id);

        if (reservaOpt.isPresent()) {
            ReservaLibro reserva = reservaOpt.get();

            if (fechaAdquisicion != null) {
                reserva.setFechaAdquisicion(fechaAdquisicion);
            }

            if (fechaDevolucion != null) {
                reserva.setFechaDevolucion(fechaDevolucion);
            }

            if (libroId != null) {
                libroRepository.findById(libroId).ifPresent(reserva::setLibro);
            }

            if (usuarioId != null) {
                usuarioRepository.findById(usuarioId).ifPresent(reserva::setUsuario);
            }

            reservaLibroRepository.save(reserva);
            return true;
        }
        return false; // Reserva no encontrada
    }

    // Método para eliminar una reserva por su ID
    public boolean deleteReserva(Long id) {
        if (reservaLibroRepository.existsById(id)) {
            reservaLibroRepository.deleteById(id);
            return true;
        }
        return false; // Reserva no encontrada
    }
}