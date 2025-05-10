package com.backend.bibliomor_servidor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.bibliomor_servidor.DTOs.LibroRequest;
import com.backend.bibliomor_servidor.Services.LibroService;

import java.util.Map;

@RestController
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // Ruta para obtener todos los libros
    @GetMapping
    public ResponseEntity<?> getAllLibros() {
        if (libroService.getAllLibros().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se han encontrado libros"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(libroService.getAllLibros());
    }

    // Ruta para obtener un libro por su ISBN
    @GetMapping("/{isbn}")
    public ResponseEntity<?> getLibroByIsbn(@PathVariable String isbn) {
        if (libroService.getLibroByIsbn(isbn) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se ha encontrado el libro"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(libroService.getLibroByIsbn(isbn));
    }

    // Ruta para crear un nuevo libro
    @PostMapping
    public ResponseEntity<?> createLibro(@RequestBody LibroRequest libroRequest) {

        boolean validar = libroService.createLibro(
                libroRequest.getIsbn(),
                libroRequest.getTitulo(),
                libroRequest.getAutor(),
                libroRequest.getSinopsis(),
                libroRequest.getCurso(),
                libroRequest.getUnidadesTotales(),
                libroRequest.getUnidadesDisponibles(),
                libroRequest.getGeneros()
        );

        if (validar) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Libro creado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "No se ha podido crear el libro (ISBN ya registrado)"));
        }
    }

    // Ruta para modificar un libro existente
    @PutMapping
    public ResponseEntity<?> modifyLibro(@RequestBody LibroRequest libroRequest) {
        boolean validar = libroService.modifyLibro(
                libroRequest.getIsbn(),
                libroRequest.getTitulo(),
                libroRequest.getAutor(),
                libroRequest.getSinopsis(),
                libroRequest.getCurso(),
                libroRequest.getUnidadesTotales(),
                libroRequest.getUnidadesDisponibles(),
                libroRequest.getGeneros()
        );

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Libro modificado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado el libro para modificar"));
        }
    }

    // Ruta para eliminar un libro por su ISBN
    @DeleteMapping
    public ResponseEntity<?> deleteLibro(@RequestBody Map<String, String> request) {
        String isbn = request.get("isbn");
        boolean validar = libroService.deleteLibro(isbn);

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Libro eliminado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado el libro para eliminar"));
        }
    }
}