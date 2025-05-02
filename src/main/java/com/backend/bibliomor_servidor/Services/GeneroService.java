package com.backend.bibliomor_servidor.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.bibliomor_servidor.Models.Genero;
import com.backend.bibliomor_servidor.Repositories.GeneroRepository;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public boolean createGenero(String nombre) {

        if (nombre == null || nombre.trim().isEmpty() || generoRepository.existsByNombre(nombre)) {
            return false;
        }

        // Crear un nuevo género y guardarlo en la base de datos
        Genero nuevoGenero = new Genero(nombre);
        generoRepository.save(nuevoGenero);
        return true;
    }

    public boolean modifyGenero(Long id, String nombre) {
        if (id == null || nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        // Verificar si el género existe
        if (!generoRepository.existsById(id)) {
            return false;
        }
        // Verificar si el nuevo nombre ya está registrado
        if (generoRepository.existsByNombre(nombre) && !generoRepository.findById(id).get().getNombre().equals(nombre)) {
            return false;
        }

        // Modificar el género existente
        Genero generoExistente = generoRepository.findById(id).orElse(null);
        generoExistente.setNombre(nombre);
        generoRepository.save(generoExistente);

        return true;
    }

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
