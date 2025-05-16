package com.backend.bibliomor_servidor.Services;

import com.backend.bibliomor_servidor.Models.Libro;
import com.backend.bibliomor_servidor.Models.Genero;
import com.backend.bibliomor_servidor.Repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    /**
     * Obtiene un libro por su ISBN.
     * 
     * @param isbn ISBN del libro a buscar.
     * @return Optional que contiene el libro si se encuentra, o vacío si no existe.
     */
    public Libro getLibroByIsbn(String isbn) {
        return libroRepository.findById(isbn).orElse(null);
    }

    /**
     * Obtiene todos los libros.
     * 
     * @return Lista de todos los libros registrados.
     */
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    /**
     * Crea un nuevo libro.
     * 
     * @param isbn ISBN del libro.
     * @param titulo Título del libro.
     * @param autor Autor del libro.
     * @param sinopsis Sinopsis del libro.
     * @param curso Curso asociado al libro.
     * @param unidadesTotales Número total de unidades del libro.
     * @param unidadesDisponibles Número de unidades disponibles del libro.
     * @param generos Lista de géneros asociados al libro.
     * @return true si el libro se creó correctamente, false si ya existe.
     */
    public boolean createLibro(String isbn, String titulo, String autor, String sinopsis, String curso,
            int unidadesTotales, int unidadesDisponibles, List<Genero> generos) {

        if (libroRepository.existsById(isbn)) {
            return false; // El libro ya existe
        }

        // Validar curso y géneros
        if (curso == null || generos == null || generos.isEmpty()) {
            curso = null;
        }

        // Crear el libro
        Libro libro = new Libro(isbn, titulo, autor, sinopsis, curso, unidadesTotales, unidadesDisponibles, new HashSet<>(generos));
        libroRepository.save(libro);
        return true;
    }

    /**
     * Modifica un libro existente.
     * 
     * @param isbn ISBN del libro a modificar.
     * @param titulo Nuevo título del libro.
     * @param autor Nuevo autor del libro.
     * @param sinopsis Nueva sinopsis del libro.
     * @param curso Nuevo curso asociado al libro.
     * @param unidadesTotales Nuevo número total de unidades.
     * @param unidadesDisponibles Nuevo número de unidades disponibles.
     * @param generos Lista de géneros asociados al libro.
     * @return true si el libro se modificó correctamente, false si no existe.
     */
    public boolean modifyLibro(String isbn, String titulo, String autor, String sinopsis, String curso,
            int unidadesTotales, int unidadesDisponibles, List<Genero> generos) {
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
        if (generos != null && !generos.isEmpty()) {
            libro.setGeneros(new HashSet<>(generos));
        }

        libroRepository.save(libro);
        return true;
    }

    /**
     * Elimina un libro por su ISBN.
     * 
     * @param isbn ISBN del libro a eliminar.
     * @return true si el libro se eliminó correctamente, false si no existe.
     */
    public boolean deleteLibro(String isbn) {
        if (!libroRepository.existsById(isbn)) {
            return false; // El libro no existe
        }

        libroRepository.deleteById(isbn);
        return true;
    }
}