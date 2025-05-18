package com.backend.bibliomor_servidor.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.bibliomor_servidor.Models.Juego;
import com.backend.bibliomor_servidor.Repositories.JuegoRepository;

@Service
public class JuegoService {

    @Autowired
    private JuegoRepository juegoRepository;

    /**
     * Obtiene un juego por su ID.
     * 
     * @param id ID del juego a buscar.
     * @return El juego encontrado o null si no existe.
     */
    public Juego getGameById(Long id) {
        return juegoRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene todos los juegos.
     * 
     * @return Lista de todos los juegos registrados.
     */
    public List<Juego> getAllGames() {
        return juegoRepository.findAll();
    }

    /**
     * Crea un nuevo juego.
     * 
     * @param nombre    Nombre del juego a crear.
     * @param nUnidades Número de unidades disponibles del juego.
     * @return true si el juego se creó correctamente, false si el nombre ya está
     *         registrado.
     */
    public boolean createGame(String nombre, int nUnidades) {
        if (juegoRepository.existsByNombre(nombre)) {
            return false; // El juego ya existe
        } else {
            Juego newGame = new Juego(nombre, nUnidades);
            juegoRepository.save(newGame);
            return true;
        }
    }

    /**
     * Modifica un juego existente.
     *
     * @param id        ID del juego a modificar.
     * @param nombre    Nuevo nombre del juego.
     * @param nUnidades Nuevo número de unidades disponibles.
     * @return true si el juego se modificó correctamente, false si no se encontró o
     *         el nombre ya está registrado.
     */
    public boolean modifyGame(Long id, String nombre, Integer nUnidades) {
        if (juegoRepository.existsById(id)) {
            Juego game = juegoRepository.findById(id).get();
            // Verificar si el nombre ya existe en otro juego
            if (juegoRepository.existsByNombre(nombre) && !game.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
                return false; // El nombre ya existe
            }

            // Actualizar solo si los campos no son null
            if (nombre != null && !nombre.trim().isEmpty()) {
                game.setNombre(nombre);
            }
            if (nUnidades != null) {
                game.setnUnidades(nUnidades);
            }
            juegoRepository.save(game);
            return true;

        }
        return false; // No se encontró el juego
    }

    /**
     * Elimina un juego por su ID.
     *
     * @param id ID del juego a eliminar.
     * @return true si el juego se eliminó correctamente, false si no se encontró.
     */
    public boolean deleteGame(Long id) {
        if (juegoRepository.existsById(id)) {
            juegoRepository.deleteById(id);
            return true;
        } else {
            return false; // No se encontró el juego
        }
    }
}