package com.backend.bibliomor_servidor.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.bibliomor_servidor.Enum.Resultado;
import com.backend.bibliomor_servidor.Models.ParticipacionRonda;
import com.backend.bibliomor_servidor.Models.Usuario;
import com.backend.bibliomor_servidor.Models.Ronda;
import com.backend.bibliomor_servidor.Repositories.ParticipacionRondaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipacionRondaService {

    @Autowired
    private ParticipacionRondaRepository participacionRondaRepository;

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
     * @param usuario   Usuario que participa.
     * @param ronda     Ronda en la que participa.
     * @param resultado Resultado de la participación.
     * @return true si la participación se creó correctamente, false si el usuario o
     *         la ronda no existen.
     */
    public boolean createParticipacion(Usuario usuario, Ronda ronda, Resultado resultado) {

        if (usuario != null && ronda != null) {
            ParticipacionRonda participacion = new ParticipacionRonda(resultado, usuario, ronda);
            participacionRondaRepository.save(participacion);
            return true;
        }
        return false; // Usuario o ronda no encontrados
    }

    /**
     * Modifica una participación existente.
     *
     * @param id        ID de la participación a modificar.
     * @param usuario   Nuevo usuario que participa.
     * @param ronda     Nueva ronda en la que participa.
     * @param resultado Nuevo resultado de la participación.
     * @return true si la participación se modificó correctamente, false si no se
     *         encontró.
     */
    public boolean modifyParticipacion(Long id, Usuario usuario, Ronda ronda, Resultado resultado) {
        Optional<ParticipacionRonda> participacionOpt = participacionRondaRepository.findById(id);

        if (participacionOpt.isPresent()) {
            ParticipacionRonda participacion = participacionOpt.get();

            if (usuario != null) {
                participacion.setUsuario(usuario);
            }

            if (ronda != null) {
                participacion.setRonda(ronda);
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
     * @return true si la participación se eliminó correctamente, false si no se
     *         encontró.
     */
    public boolean deleteParticipacion(Long id) {
        if (participacionRondaRepository.existsById(id)) {
            participacionRondaRepository.deleteById(id);
            return true;
        }
        return false; // Participación no encontrada
    }
}