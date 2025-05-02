package com.backend.bibliomor_servidor.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.bibliomor_servidor.Models.ReservaJuego;
import com.backend.bibliomor_servidor.Enum.Recreo;
import com.backend.bibliomor_servidor.Models.Juego;
import com.backend.bibliomor_servidor.Models.Usuario;
import com.backend.bibliomor_servidor.Repositories.ReservaJuegoRepository;
import com.backend.bibliomor_servidor.Repositories.JuegoRepository;
import com.backend.bibliomor_servidor.Repositories.UsuarioRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReservaJuegoService {

    @Autowired
    private ReservaJuegoRepository reservaJuegoRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para crear una nueva reserva de juego
    public boolean createReserva(LocalDate fecha, Recreo recreo, Long juegoId, Long usuarioId) {
        Optional<Juego> juegoOpt = juegoRepository.findById(juegoId);
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);

        if (juegoOpt.isPresent() && usuarioOpt.isPresent()) {
            ReservaJuego reserva = new ReservaJuego(
                fecha, recreo, juegoOpt.get(), usuarioOpt.get()
            );
            reservaJuegoRepository.save(reserva);
            return true;
        }
        return false; // Juego o usuario no encontrados
    }

    // Método para modificar una reserva existente
    public boolean modifyReserva(Long id, LocalDate fecha, Recreo recreo, Long juegoId, Long usuarioId) {
        Optional<ReservaJuego> reservaOpt = reservaJuegoRepository.findById(id);

        if (reservaOpt.isPresent()) {
            ReservaJuego reserva = reservaOpt.get();

            if (fecha != null) {
                reserva.setFecha(fecha);
            }

            if (recreo != null) {
                reserva.setRecreo(recreo);
            }

            if (juegoId != null) {
                juegoRepository.findById(juegoId).ifPresent(reserva::setJuego);
            }

            if (usuarioId != null) {
                usuarioRepository.findById(usuarioId).ifPresent(reserva::setUsuario);
            }

            reservaJuegoRepository.save(reserva);
            return true;
        }
        return false; // Reserva no encontrada
    }

    // Método para eliminar una reserva por su ID
    public boolean deleteReserva(Long id) {
        if (reservaJuegoRepository.existsById(id)) {
            reservaJuegoRepository.deleteById(id);
            return true;
        }
        return false; // Reserva no encontrada
    }
}