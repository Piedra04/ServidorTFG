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

    /**
     * Obtiene una participación por su ID.
     * 
     * @param id ID de la participación a buscar.
     * @return La participación encontrada o null si no existe.
     */
    public ParticipacionCampeonato getParticipacionById(Long id) {
        return participacionCampeonatoRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene todas las participaciones.
     * 
     * @return Lista de todas las participaciones registradas.
     */
    public List<ParticipacionCampeonato> getAllParticipaciones() {
        return participacionCampeonatoRepository.findAll();
    }

    /**
     * Crea una nueva participación.
     * 
     * @param usuarioId ID del usuario que participa.
     * @param campeonatoId ID del campeonato en el que participa.
     * @return true si la participación se creó correctamente, false si el usuario o el campeonato no existen.
     */
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

    /**
     * Modifica una participación existente.
     * 
     * @param id ID de la participación a modificar.
     * @param usuarioId Nuevo ID del usuario que participa.
     * @param campeonatoId Nuevo ID del campeonato en el que participa.
     * @return true si la participación se modificó correctamente, false si no se encontró.
     */
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

    /**
     * Elimina una participación por su ID.
     * 
     * @param id ID de la participación a eliminar.
     * @return true si la participación se eliminó correctamente, false si no se encontró.
     */
    public boolean deleteParticipacion(Long id) {
        if (participacionCampeonatoRepository.existsById(id)) {
            participacionCampeonatoRepository.deleteById(id);
            return true;
        }
        return false; // Participación no encontrada
    }
}