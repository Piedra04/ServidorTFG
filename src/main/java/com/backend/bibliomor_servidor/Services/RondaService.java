package com.backend.bibliomor_servidor.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.bibliomor_servidor.Models.Ronda;
import com.backend.bibliomor_servidor.Models.Campeonato;
import com.backend.bibliomor_servidor.Repositories.RondaRepository;
import com.backend.bibliomor_servidor.Repositories.CampeonatoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RondaService {

    @Autowired
    private RondaRepository rondaRepository;

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    /**
     * Obtiene una ronda por su ID.
     * 
     * @param id ID de la ronda a buscar.
     * @return Optional que contiene la ronda si se encuentra, o vacío si no existe.
     */
    public Ronda getRondaById(Long id) {
        return rondaRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene todas las rondas.
     * 
     * @return Lista de todas las rondas registradas.
     */
    public List<Ronda> getAllRondas() {
        return rondaRepository.findAll();
    }

    /**
     * Crea una nueva ronda.
     * 
     * @param nRonda Número de la ronda.
     * @param fecha Fecha de la ronda.
     * @param campeonatoId ID del campeonato asociado a la ronda.
     * @return true si la ronda se creó correctamente, false si el campeonato no existe.
     */
    public boolean createRonda(int nRonda, LocalDate fecha, Long campeonatoId) {
        Optional<Campeonato> campeonatoOpt = campeonatoRepository.findById(campeonatoId);

        if (campeonatoOpt.isPresent()) {
            Ronda ronda = new Ronda(nRonda, fecha, campeonatoOpt.get());
            rondaRepository.save(ronda);
            return true;
        }
        return false; // Campeonato no encontrado
    }

    /**
     * Modifica una ronda existente.
     * 
     * @param id ID de la ronda a modificar.
     * @param nRonda Nuevo número de la ronda.
     * @param fecha Nueva fecha de la ronda.
     * @param campeonatoId Nuevo ID del campeonato asociado a la ronda.
     * @return true si la ronda se modificó correctamente, false si no se encontró.
     */
    public boolean modifyRonda(Long id, int nRonda, LocalDate fecha, Long campeonatoId) {
        Optional<Ronda> rondaOpt = rondaRepository.findById(id);

        if (rondaOpt.isPresent()) {
            Ronda ronda = rondaOpt.get();

            ronda.setNRonda(nRonda);
            ronda.setFecha(fecha);

            if (campeonatoId != null) {
                campeonatoRepository.findById(campeonatoId).ifPresent(ronda::setCampeonato);
            }

            rondaRepository.save(ronda);
            return true;
        }
        return false; // Ronda no encontrada
    }

    /**
     * Elimina una ronda por su ID.
     * 
     * @param id ID de la ronda a eliminar.
     * @return true si la ronda se eliminó correctamente, false si no se encontró.
     */
    public boolean deleteRonda(Long id) {
        if (rondaRepository.existsById(id)) {
            rondaRepository.deleteById(id);
            return true;
        }
        return false; // Ronda no encontrada
    }
}