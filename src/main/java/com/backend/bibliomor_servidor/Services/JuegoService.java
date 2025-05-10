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

    // Método para obtener un juego por su ID
    public Juego getGameById(Long id) {
        return juegoRepository.findById(id).orElse(null);
    }

    // Método para obtener todos los juegos
    public List<Juego> getAllGames() {
        return juegoRepository.findAll();
    }

    // Método para crear un nuevo juego
    public boolean createGame(String nombre, int nUnidades) {
        if (juegoRepository.existsByNombre(nombre)) {
            return false; // El juego ya existe
        } else {
            Juego newGame = new Juego(nombre, nUnidades);
            juegoRepository.save(newGame);
            return true;
        }
    }

    // Método para modificar un juego existente
    public boolean modifyGame(Long id, String nombre, Integer nUnidades) {
        if (juegoRepository.existsById(id)) {
            Juego game = juegoRepository.findById(id).orElse(null);
            if (game != null) {
                // Verificar si el nombre ya existe en otro juego
                if (juegoRepository.existsByNombre(nombre) && !game.getNombre().equals(nombre)) {
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
        }
        return false; // No se encontró el juego
    }

    // Método para eliminar un juego por su ID
    public boolean deleteGame(Long id) {
        if (juegoRepository.existsById(id)) {
            juegoRepository.deleteById(id);
            return true;
        } else {
            return false; // No se encontró el juego
        }
    }
}