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

    // Método para obtener una ronda por su ID
    public Optional<Ronda> getRondaById(Long id) {
        return rondaRepository.findById(id);
    }

    // Método para obtener todas las rondas
    public List<Ronda> getAllRondas() {
        return rondaRepository.findAll();
    }

    // Método para crear una nueva ronda
    public boolean createRonda(int nRonda, LocalDate fecha, Long campeonatoId) {
        Optional<Campeonato> campeonatoOpt = campeonatoRepository.findById(campeonatoId);

        if (campeonatoOpt.isPresent()) {
            Ronda ronda = new Ronda(nRonda, fecha, campeonatoOpt.get());
            rondaRepository.save(ronda);
            return true;
        }
        return false; // Campeonato no encontrado
    }

    // Método para modificar una ronda existente
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

    // Método para eliminar una ronda por su ID
    public boolean deleteRonda(Long id) {
        if (rondaRepository.existsById(id)) {
            rondaRepository.deleteById(id);
            return true;
        }
        return false; // Ronda no encontrada
    }
}