package com.backend.bibliomor_servidor.Services;

import com.backend.bibliomor_servidor.Models.Libro;
import com.backend.bibliomor_servidor.Models.Genero;
import com.backend.bibliomor_servidor.Repositories.LibroRepository;
import com.backend.bibliomor_servidor.Repositories.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private GeneroRepository generoRepository;

    // Método para obtener un libro por su ISBN
    public Optional<Libro> getLibroByIsbn(String isbn) {
        return libroRepository.findById(isbn);
    }

    // Método para obtener todos los libros
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    // Método para crear un libro
    public boolean createLibro(String isbn, String titulo, String autor, String sinopsis, String curso,
            int unidadesTotales, int unidadesDisponibles, Set<Long> generosIds) {

        if (libroRepository.existsById(isbn)) {
            return false; // El libro ya existe
        }

        // Pasamos ls IDs de géneros a un Set de Genero
        Set<Genero> generos = new HashSet<>();
        for (Long generoId : generosIds) {
            Optional<Genero> generoOpt = generoRepository.findById(generoId);
            if (generoOpt.isPresent()) {
                generos.add(generoOpt.get());
            }
            
        }
        
        // Crear el libro
        Libro libro = new Libro(isbn, titulo, autor, sinopsis, curso, unidadesTotales, unidadesDisponibles, generos);
        libroRepository.save(libro);
        return true;
    }

    // Método para modificar un libro
    public boolean modifyLibro(String isbn, String titulo, String autor, String sinopsis, String curso,
            int unidadesTotales, int unidadesDisponibles, Set<Long> generosIds) {
        Optional<Libro> optionalLibro = libroRepository.findById(isbn);
        if (optionalLibro.isEmpty()) {
            return false; // El libro no existe
        }

        Libro libro = optionalLibro.get();

        // Actualizar los campos solo si no son nulos
        if (titulo != null && !titulo.trim().isEmpty()) {
            libro.setTitulo(titulo);
        }
        if (autor != null && !autor.trim().isEmpty()) {
            libro.setAutor(autor);
        }
        if (sinopsis != null && !sinopsis.trim().isEmpty()) {
            libro.setSinopsis(sinopsis);
        }
        if (curso != null && !curso.trim().isEmpty()) {
            libro.setCurso(curso);
        }
        libro.setUnidadesTotales(unidadesTotales);
        libro.setUnidadesDisponibles(unidadesDisponibles);

        // Actualizar los géneros
        if (generosIds != null && !generosIds.isEmpty()) {
            Set<Genero> generos = new HashSet<>();
            for (Long generoId : generosIds) {
                Optional<Genero> genero = generoRepository.findById(generoId);
                genero.ifPresent(generos::add);
            }
            libro.setGeneros(generos);
        }

        libroRepository.save(libro);
        return true;
    }

    // Método para eliminar un libro
    public boolean deleteLibro(String isbn) {
        if (!libroRepository.existsById(isbn)) {
            return false; // El libro no existe
        }

        libroRepository.deleteById(isbn);
        return true;
    }
}