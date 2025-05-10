package com.backend.bibliomor_servidor.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.bibliomor_servidor.Enum.Resultado;
import com.backend.bibliomor_servidor.Models.ParticipacionRonda;
import com.backend.bibliomor_servidor.Models.Usuario;
import com.backend.bibliomor_servidor.Models.Ronda;
import com.backend.bibliomor_servidor.Repositories.ParticipacionRondaRepository;
import com.backend.bibliomor_servidor.Repositories.UsuarioRepository;
import com.backend.bibliomor_servidor.Repositories.RondaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipacionRondaService {

    @Autowired
    private ParticipacionRondaRepository participacionRondaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RondaRepository rondaRepository;

    // Método para obtener una participación por su ID
    public ParticipacionRonda getParticipacionById(Long id) {
        return participacionRondaRepository.findById(id).orElse(null);
    }

    // Método para obtener todas las participaciones
    public List<ParticipacionRonda> getAllParticipaciones() {
        return participacionRondaRepository.findAll();
    }

    // Método para crear una nueva participación en una ronda
    public boolean createParticipacion(Long usuarioId, Long rondaId, Resultado resultado) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        Optional<Ronda> rondaOpt = rondaRepository.findById(rondaId);

        if (usuarioOpt.isPresent() && rondaOpt.isPresent()) {
            ParticipacionRonda participacion = new ParticipacionRonda(
                resultado, usuarioOpt.get(), rondaOpt.get()
            );
            participacionRondaRepository.save(participacion);
            return true;
        }
        return false; // Usuario o ronda no encontrados
    }

    // Método para modificar una participación existente
    public boolean modifyParticipacion(Long id, Long usuarioId, Long rondaId, Resultado resultado) {
        Optional<ParticipacionRonda> participacionOpt = participacionRondaRepository.findById(id);

        if (participacionOpt.isPresent()) {
            ParticipacionRonda participacion = participacionOpt.get();

            if (usuarioId != null) {
                usuarioRepository.findById(usuarioId).ifPresent(participacion::setUsuario);
            }

            if (rondaId != null) {
                rondaRepository.findById(rondaId).ifPresent(participacion::setRonda);
            }

            if (resultado != null) {
                participacion.setResultado(resultado);
            }

            participacionRondaRepository.save(participacion);
            return true;
        }
        return false; // Participación no encontrada
    }

    // Método para eliminar una participación por su ID
    public boolean deleteParticipacion(Long id) {
        if (participacionRondaRepository.existsById(id)) {
            participacionRondaRepository.deleteById(id);
            return true;
        }
        return false; // Participación no encontrada
    }
}