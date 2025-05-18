package com.backend.bibliomor_servidor.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.bibliomor_servidor.Models.ReservaJuego;
import com.backend.bibliomor_servidor.Enum.Recreo;
import com.backend.bibliomor_servidor.Models.Juego;
import com.backend.bibliomor_servidor.Models.Usuario;
import com.backend.bibliomor_servidor.Repositories.ReservaJuegoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaJuegoService {

    @Autowired
    private ReservaJuegoRepository reservaJuegoRepository;

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
     * @param fecha   Fecha de la reserva.
     * @param recreo  Recreo en el que se realizará la reserva.
     * @param juego   Juego a reservar.
     * @param usuario Usuario que realiza la reserva.
     * @return true si la reserva se creó correctamente, false si el juego o el
     *         usuario no existen.
     */
    public boolean createReserva(LocalDate fecha, Recreo recreo, Juego juego, Usuario usuario) {
        if (juego != null && usuario != null) {
            // Comprobar si ya se alcanzó el máximo de reservas para ese juego, fecha y recreo
            long reservasActuales = reservaJuegoRepository.countByJuegoAndFechaAndRecreo(juego, fecha, recreo);
            if (reservasActuales >= juego.getnUnidades()) {
                return false; // No se pueden hacer más reservas para ese juego en ese recreo y fecha
            }
            ReservaJuego reserva = new ReservaJuego(fecha, recreo, juego, usuario);
            reservaJuegoRepository.save(reserva);
            return true;
        }
        return false; // Juego o usuario no encontrados
    }

    /**
     * Modifica una reserva existente.
     *
     * @param id      ID de la reserva a modificar.
     * @param fecha   Nueva fecha de la reserva.
     * @param recreo  Nuevo recreo de la reserva.
     * @param juego   Nuevo juego a reservar.
     * @param usuario Nuevo usuario que realiza la reserva.
     * @return true si la reserva se modificó correctamente, false si no se
     *         encontró.
     */
    public boolean modifyReserva(Long id, LocalDate fecha, Recreo recreo, Juego juego, Usuario usuario) {
        Optional<ReservaJuego> reservaOpt = reservaJuegoRepository.findById(id);

        if (reservaOpt.isPresent()) {
            ReservaJuego reserva = reservaOpt.get();

            if (fecha != null) {
                reserva.setFecha(fecha);
            }

            if (recreo != null) {
                reserva.setRecreo(recreo);
            }

            if (juego != null) {
                reserva.setJuego(juego);
            }

            if (usuario != null) {
                reserva.setUsuario(usuario);
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