package com.backend.bibliomor_servidor.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.bibliomor_servidor.Models.Genero;
import com.backend.bibliomor_servidor.Repositories.GeneroRepository;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    /**
     * Obtiene un género por su ID.
     *
     * @param id ID del género a buscar.
     * @return El género encontrado o null si no existe.
     */
    public Genero getGeneroById(Long id) {
        return generoRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene todos los géneros.
     *
     * @return Lista de todos los géneros registrados.
     */
    public List<Genero> getAllGeneros() {
        return generoRepository.findAll();
    }

    /**
     * Crea un nuevo género.
     *
     * @param nombre Nombre del género a crear.
     * @return true si el género se creó correctamente, false si el nombre es
     *         inválido o ya existe.
     */
    public boolean createGenero(String nombre) {
        if (nombre == null || nombre.trim().isEmpty() || generoRepository.existsByNombre(nombre)) {
            return false;
        }

        // Crear un nuevo género y guardarlo en la base de datos
        Genero nuevoGenero = new Genero(nombre);
        generoRepository.save(nuevoGenero);
        return true;
    }

    /**
     * Modifica un género existente.
     *
     * @param id     ID del género a modificar.
     * @param nombre Nuevo nombre del género.
     * @return true si el género se modificó correctamente, false si no se encontró
     *         o el nombre ya está registrado.
     */
    public boolean modifyGenero(Long id, String nombre) {
        if (id == null || nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        // Verificar si el género existe
        if (!generoRepository.existsById(id)) {
            return false;
        }
        // Verificar si el nuevo nombre ya está registrado
        if (generoRepository.existsByNombre(nombre)
                && !generoRepository.findById(id).get().getNombre().equals(nombre)) {
            return false;
        }

        // Modificar el género existente
        Genero generoExistente = generoRepository.findById(id).orElse(null);
        generoExistente.setNombre(nombre);
        generoRepository.save(generoExistente);

        return true;
    }

    /**
     * Elimina un género por su ID.
     *
     * @param id ID del género a eliminar.
     * @return true si el género se eliminó correctamente, false si no se encontró.
     */
    public boolean deleteGenero(Long id) {
        if (generoRepository.existsById(id)) {
            // Eliminar el género de la base de datos
            generoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}