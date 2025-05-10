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

    /**
     * Obtiene una participación por su ID.
     * 
     * @param id ID de la participación a buscar.
     * @return La participación encontrada o null si no existe.
     */
    public ParticipacionRonda getParticipacionById(Long id) {
        return participacionRondaRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene todas las participaciones.
     * 
     * @return Lista de todas las participaciones registradas.
     */
    public List<ParticipacionRonda> getAllParticipaciones() {
        return participacionRondaRepository.findAll();
    }

    /**
     * Crea una nueva participación en una ronda.
     * 
     * @param usuarioId ID del usuario que participa.
     * @param rondaId ID de la ronda en la que participa.
     * @param resultado Resultado de la participación.
     * @return true si la participación se creó correctamente, false si el usuario o la ronda no existen.
     */
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

    /**
     * Modifica una participación existente.
     * 
     * @param id ID de la participación a modificar.
     * @param usuarioId Nuevo ID del usuario que participa.
     * @param rondaId Nuevo ID de la ronda en la que participa.
     * @param resultado Nuevo resultado de la participación.
     * @return true si la participación se modificó correctamente, false si no se encontró.
     */
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

    /**
     * Elimina una participación por su ID.
     * 
     * @param id ID de la participación a eliminar.
     * @return true si la participación se eliminó correctamente, false si no se encontró.
     */
    public boolean deleteParticipacion(Long id) {
        if (participacionRondaRepository.existsById(id)) {
            participacionRondaRepository.deleteById(id);
            return true;
        }
        return false; // Participación no encontrada
    }
}