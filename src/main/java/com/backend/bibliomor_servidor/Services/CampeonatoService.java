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
import java.util.Optional;

@Service
public class CampeonatoService {

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    // Método para obtener un campeonato por su ID
    public Optional<Campeonato> getCampeonatoById(Long id) {
        return campeonatoRepository.findById(id);
    }

    // Método para obtener todos los campeonatos
    public List<Campeonato> getAllCampeonatos() {
        return campeonatoRepository.findAll();
    }

    // Método para crear un nuevo campeonato
    public boolean createCampeonato(LocalDate fechaInicio, LocalDate fechaFin, String descripcion, Long idJuego,
            EstadoTorneo estado) {
        // Crear un nuevo campeonato
        if (juegoRepository.existsById(idJuego)) {
            Juego juego = juegoRepository.findById(idJuego).orElseThrow(() -> new RuntimeException("Juego no encontrado"));

            Campeonato campeonato = new Campeonato(fechaInicio, fechaFin, descripcion,
            juego, estado);
            campeonatoRepository.save(campeonato);
            return true;
        }
        return false; // No se encontró el juego
    }

    // Método para modificar un campeonato existente
    public boolean modifyCampeonato(Long id, LocalDate fechaInicio, LocalDate fechaFin, String descripcion,
            Long juegoId, EstadoTorneo estado) {
        if (campeonatoRepository.existsById(id)) {
            Campeonato campeonato = campeonatoRepository.findById(id).orElse(null);
            if (campeonato != null) {
                // Actualizar solo si los campos no son null
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

    // Método para eliminar un campeonato por su ID
    public boolean deleteCampeonato(Long id) {
        if (campeonatoRepository.existsById(id)) {
            campeonatoRepository.deleteById(id);
            return true;
        } else {
            return false; // No se encontró el campeonato
        }
    }
}