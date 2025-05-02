package com.backend.bibliomor_servidor.Models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.backend.bibliomor_servidor.Enum.EstadoLibro;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "libros")
public class Libro {

    // Atributos de la clase Libro

    @Id
    @Column(length = 13, nullable = false)
    private String isbn;

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(nullable = false, length = 50)
    private String autor;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String sinopsis;

    @Column(nullable = true, length = 10)
    private String curso;

    @Column(nullable = false)
    private int unidadesTotales;

    @Column(nullable = false)
    private int unidadesDisponibles;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoLibro estado;

    @ManyToMany
    @JoinTable(name = "libro_genero", joinColumns = @JoinColumn(name = "libro"), inverseJoinColumns = @JoinColumn(name = "genero"))
    private Set<Genero> generos = new HashSet<>();

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<ReservaLibro> libro;

    // Constructor vacío
    public Libro() {
    }

    // Constructor con todos los campos
    public Libro(String isbn, String titulo, String autor, String sinopsis, String curso, int unidadesTotales,
            int unidadesDisponibles, Set<Genero> generos) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.sinopsis = sinopsis;
        this.curso = curso;
        this.unidadesTotales = unidadesTotales;
        this.unidadesDisponibles = unidadesDisponibles;
        this.generos = generos;
        if (unidadesDisponibles == 0) {
            this.estado = EstadoLibro.NO_DISPONIBLE;
        } else {
            this.estado = EstadoLibro.DISPONIBLE;
        }
    }

    // Getters y Setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getUnidadesTotales() {
        return unidadesTotales;
    }

    public void setUnidadesTotales(int unidadesTotales) {
        this.unidadesTotales = unidadesTotales;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public EstadoLibro getEstado() {
        return estado;
    }

    public void setEstado(EstadoLibro estado) {
        this.estado = estado;
    }

    public Set<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(Set<Genero> generos) {
        this.generos = generos;
    }
}
