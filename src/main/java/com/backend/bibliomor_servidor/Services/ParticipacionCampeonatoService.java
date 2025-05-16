package com.backend.bibliomor_servidor.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.bibliomor_servidor.Models.ParticipacionCampeonato;
import com.backend.bibliomor_servidor.Models.Usuario;
import com.backend.bibliomor_servidor.Models.Campeonato;
import com.backend.bibliomor_servidor.Repositories.ParticipacionCampeonatoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipacionCampeonatoService {

    @Autowired
    private ParticipacionCampeonatoRepository participacionCampeonatoRepository;

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
     * @param usuario    Usuario que participa.
     * @param campeonato Campeonato en el que participa.
     * @return true si la participación se creó correctamente, false si el usuario o
     *         el campeonato no existen.
     */
    public boolean createParticipacion(Usuario usuario, Campeonato campeonato) {

        if (usuario != null && campeonato != null) {
            ParticipacionCampeonato participacion = new ParticipacionCampeonato(usuario, campeonato);
            participacionCampeonatoRepository.save(participacion);
            return true;
        }
        return false; // Usuario o campeonato no encontrados
    }

    /**
     * Modifica una participación existente.
     *
     * @param id         ID de la participación a modificar.
     * @param usuario    Nuevo usuario que participa.
     * @param campeonato Nuevo campeonato en el que participa.
     * @return true si la participación se modificó correctamente, false si no se
     *         encontró.
     */
    public boolean modifyParticipacion(Long id, Usuario usuario, Campeonato campeonato) {
        Optional<ParticipacionCampeonato> participacionOpt = participacionCampeonatoRepository.findById(id);

        if (participacionOpt.isPresent()) {
            ParticipacionCampeonato participacion = participacionOpt.get();

            participacion.setUsuario(usuario);
            participacion.setCampeonato(campeonato);

            participacionCampeonatoRepository.save(participacion);
            return true;
        }
        return false; // Participación no encontrada
    }

    /**
     * Elimina una participación por su ID.
     *
     * @param id ID de la participación a eliminar.
     * @return true si la participación se eliminó correctamente, false si no se
     *         encontró.
     */
    public boolean deleteParticipacion(Long id) {
        if (participacionCampeonatoRepository.existsById(id)) {
            participacionCampeonatoRepository.deleteById(id);
            return true;
        }
        return false; // Participación no encontrada
    }
}