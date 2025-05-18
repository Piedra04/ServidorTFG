package com.backend.bibliomor_servidor.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.bibliomor_servidor.Models.ReservaJuego;
import com.backend.bibliomor_servidor.Models.Juego;
import java.time.LocalDate;
import com.backend.bibliomor_servidor.Enum.Recreo;

@Repository
public interface ReservaJuegoRepository extends JpaRepository<ReservaJuego, Long> {
    /**
     * Cuenta el número de reservas para un juego, fecha y recreo específicos.
     *
     * @param juego  Juego reservado.
     * @param fecha  Fecha de la reserva.
     * @param recreo Recreo de la reserva.
     * @return Número de reservas que coinciden con los parámetros.
     */
    long countByJuegoAndFechaAndRecreo(Juego juego, LocalDate fecha, Recreo recreo);
}