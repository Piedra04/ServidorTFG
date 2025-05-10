package com.backend.bibliomor_servidor.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.bibliomor_servidor.Enum.EstadoTorneo;
import com.backend.bibliomor_servidor.Models.Campeonato;
import com.backend.bibliomor_servidor.Models.Juego;
import com.backend.bibliomor_servidor.Repositories.CampeonatoRepository;
import com.backend.bibliomor_servidor.Repositories.JuegoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class CampeonatoService {

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    /**
     * Obtiene un campeonato por su ID.
     * 
     * @param id ID del campeonato a buscar.
     * @return Optional que contiene el campeonato si se encuentra, o vacío si no existe.
     */
    public Campeonato getCampeonatoById(Long id) {
        return campeonatoRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene todos los campeonatos.
     * 
     * @return Lista de todos los campeonatos registrados.
     */
    public List<Campeonato> getAllCampeonatos() {
        return campeonatoRepository.findAll();
    }

    /**
     * Crea un nuevo campeonato.
     * 
     * @param fechaInicio Fecha de inicio del campeonato.
     * @param fechaFin Fecha de finalización del campeonato.
     * @param descripcion Descripción del campeonato.
     * @param idJuego ID del juego asociado al campeonato.
     * @param estado Estado del torneo.
     * @return true si el campeonato se creó correctamente, false si no se encontró el juego.
     */
    public boolean createCampeonato(LocalDate fechaInicio, LocalDate fechaFin, String descripcion, Long idJuego,
            EstadoTorneo estado) {
        if (juegoRepository.existsById(idJuego)) {
            Juego juego = juegoRepository.findById(idJuego).orElseThrow(() -> new RuntimeException("Juego no encontrado"));

            Campeonato campeonato = new Campeonato(fechaInicio, fechaFin, descripcion, juego, estado);
            campeonatoRepository.save(campeonato);
            return true;
        }
        return false; // No se encontró el juego
    }

    /**
     * Modifica un campeonato existente.
     * 
     * @param id ID del campeonato a modificar.
     * @param fechaInicio Nueva fecha de inicio del campeonato.
     * @param fechaFin Nueva fecha de finalización del campeonato.
     * @param descripcion Nueva descripción del campeonato.
     * @param juegoId ID del juego asociado al campeonato.
     * @param estado Nuevo estado del torneo.
     * @return true si el campeonato se modificó correctamente, false si no se encontró.
     */
    public boolean modifyCampeonato(Long id, LocalDate fechaInicio, LocalDate fechaFin, String descripcion,
            Long juegoId, EstadoTorneo estado) {
        if (campeonatoRepository.existsById(id)) {
            Campeonato campeonato = campeonatoRepository.findById(id).orElse(null);
            if (campeonato != null) {
                if (fechaInicio != null) {
                    campeonato.setFechaInicio(fechaInicio);
                }
                if (fechaFin != null) {
                    campeonato.setFechaFin(fechaFin);
                }
                if (descripcion != null && !descripcion.trim().isEmpty()) {
                    campeonato.setDescripcion(descripcion);
                }
                if (estado != null) {
                    campeonato.setEstado(estado);
                }
                campeonatoRepository.save(campeonato);
                return true;
            }
        }
        return false; // No se encontró el campeonato
    }

    /**
     * Elimina un campeonato por su ID.
     * 
     * @param id ID del campeonato a eliminar.
     * @return true si el campeonato se eliminó correctamente, false si no se encontró.
     */
    public boolean deleteCampeonato(Long id) {
        if (campeonatoRepository.existsById(id)) {
            campeonatoRepository.deleteById(id);
            return true;
        } else {
            return false; // No se encontró el campeonato
        }
    }
}