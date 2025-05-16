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

    /**
     * Obtiene todos los libros.
     * 
     * @return ResponseEntity con la lista de libros o un mensaje NOT_FOUND si no
     *         hay libros.
     */
    @GetMapping
    public ResponseEntity<?> getAllLibros() {
        if (libroService.getAllLibros().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se han encontrado libros"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(libroService.getAllLibros());
    }

    /**
     * Obtiene un libro por su ISBN.
     * 
     * @param isbn ISBN del libro a buscar.
     * @return ResponseEntity con el libro encontrado o un mensaje NOT_FOUND si no
     *         existe.
     */
    @GetMapping("/{isbn}")
    public ResponseEntity<?> getLibroByIsbn(@PathVariable String isbn) {
        if (libroService.getLibroByIsbn(isbn) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se ha encontrado el libro"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(libroService.getLibroByIsbn(isbn));
    }

    /**
     * Crea un nuevo libro.
     * 
     * @param libroRequest Objeto con los datos del libro a crear.
     * @return ResponseEntity con estado CREATED si se creó correctamente, o
     *         BAD_REQUEST si falló.
     */
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
                libroRequest.getGeneros());

        if (validar) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Libro creado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "No se ha podido crear el libro (ISBN ya registrado)"));
        }
    }

    /**
     * Modifica un libro existente.
     * 
     * @param libroRequest Objeto con los datos actualizados del libro.
     * @return ResponseEntity con estado OK si se modificó correctamente, o
     *         NOT_FOUND si no existe.
     */
    @PutMapping("/{isbn}")
    public ResponseEntity<?> modifyLibro(@PathVariable String isbn, @RequestBody LibroRequest libroRequest) {
        boolean validar = libroService.modifyLibro(
                isbn,
                libroRequest.getTitulo(),
                libroRequest.getAutor(),
                libroRequest.getSinopsis(),
                libroRequest.getCurso(),
                libroRequest.getUnidadesTotales(),
                libroRequest.getUnidadesDisponibles(),
                libroRequest.getGeneros());

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Libro modificado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado el libro para modificar"));
        }
    }

    /**
     * Elimina un libro por su ISBN.
     * 
     * @param request Mapa que contiene el ISBN del libro a eliminar.
     * @return ResponseEntity con estado OK si se eliminó correctamente, o NOT_FOUND
     *         si no existe.
     */
    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> deleteLibro(@PathVariable String isbn) {
        boolean validar = libroService.deleteLibro(isbn);

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Libro eliminado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado el libro para eliminar"));
        }
    }
}