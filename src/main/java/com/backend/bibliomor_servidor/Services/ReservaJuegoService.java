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
import java.util.List;
import java.util.Optional;

@Service
public class ReservaJuegoService {

    @Autowired
    private ReservaJuegoRepository reservaJuegoRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Obtiene una reserva por su ID.
     * 
     * @param id ID de la reserva a buscar.
     * @return La reserva encontrada o null si no existe.
     */
    public ReservaJuego getReservaById(Long id) {
        return reservaJuegoRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene todas las reservas.
     * 
     * @return Lista de todas las reservas registradas.
     */
    public List<ReservaJuego> getAllReservas() {
        return reservaJuegoRepository.findAll();
    }

    /**
     * Crea una nueva reserva de juego.
     * 
     * @param fecha Fecha de la reserva.
     * @param recreo Recreo en el que se realizará la reserva.
     * @param juegoId ID del juego a reservar.
     * @param usuarioId ID del usuario que realiza la reserva.
     * @return true si la reserva se creó correctamente, false si el juego o el usuario no existen.
     */
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

    /**
     * Modifica una reserva existente.
     * 
     * @param id ID de la reserva a modificar.
     * @param fecha Nueva fecha de la reserva.
     * @param recreo Nuevo recreo de la reserva.
     * @param juegoId Nuevo ID del juego a reservar.
     * @param usuarioId Nuevo ID del usuario que realiza la reserva.
     * @return true si la reserva se modificó correctamente, false si no se encontró.
     */
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

    /**
     * Elimina una reserva por su ID.
     * 
     * @param id ID de la reserva a eliminar.
     * @return true si la reserva se eliminó correctamente, false si no se encontró.
     */
    public boolean deleteReserva(Long id) {
        if (reservaJuegoRepository.existsById(id)) {
            reservaJuegoRepository.deleteById(id);
            return true;
        }
        return false; // Reserva no encontrada
    }
}