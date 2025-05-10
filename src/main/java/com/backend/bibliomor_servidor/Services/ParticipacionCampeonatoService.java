package com.backend.bibliomor_servidor.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.bibliomor_servidor.Models.ParticipacionCampeonato;
import com.backend.bibliomor_servidor.Models.Usuario;
import com.backend.bibliomor_servidor.Models.Campeonato;
import com.backend.bibliomor_servidor.Repositories.ParticipacionCampeonatoRepository;
import com.backend.bibliomor_servidor.Repositories.UsuarioRepository;
import com.backend.bibliomor_servidor.Repositories.CampeonatoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipacionCampeonatoService {

    @Autowired
    private ParticipacionCampeonatoRepository participacionCampeonatoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    // Método para obtener una participación por su ID
    public ParticipacionCampeonato getParticipacionById(Long id) {
        return participacionCampeonatoRepository.findById(id).orElse(null);
    }

    // Método para obtener todas las participaciones
    public List<ParticipacionCampeonato> getAllParticipaciones() {
        return participacionCampeonatoRepository.findAll();
    }

    // Método para crear una nueva participación
    public boolean createParticipacion(Long usuarioId, Long campeonatoId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        Optional<Campeonato> campeonatoOpt = campeonatoRepository.findById(campeonatoId);

        if (usuarioOpt.isPresent() && campeonatoOpt.isPresent()) {
            ParticipacionCampeonato participacion = new ParticipacionCampeonato(usuarioOpt.get(), campeonatoOpt.get());
            participacionCampeonatoRepository.save(participacion);
            return true;
        }
        return false; // Usuario o campeonato no encontrados
    }

    // Método para modificar una participación existente
    public boolean modifyParticipacion(Long id, Long usuarioId, Long campeonatoId) {
        Optional<ParticipacionCampeonato> participacionOpt = participacionCampeonatoRepository.findById(id);

        if (participacionOpt.isPresent()) {
            ParticipacionCampeonato participacion = participacionOpt.get();

            if (usuarioId != null) {
                usuarioRepository.findById(usuarioId).ifPresent(participacion::setUsuario);
            }

            if (campeonatoId != null) {
                campeonatoRepository.findById(campeonatoId).ifPresent(participacion::setCampeonato);
            }

            participacionCampeonatoRepository.save(participacion);
            return true;
        }
        return false; // Participación no encontrada
    }

    // Método para eliminar una participación por su ID
    public boolean deleteParticipacion(Long id) {
        if (participacionCampeonatoRepository.existsById(id)) {
            participacionCampeonatoRepository.deleteById(id);
            return true;
        }
        return false; // Participación no encontrada
    }
}