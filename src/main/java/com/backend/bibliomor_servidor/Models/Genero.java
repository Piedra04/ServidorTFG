package com.backend.bibliomor_servidor.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "generos")
public class Genero {

    // Atributos de la clase Genero

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 20)
    private String nombre;

    // Relación ManyToMany con la entidad Libro
    @ManyToMany(mappedBy = "generos")
    private Set<Libro> libros = new HashSet<>();

    // Constructor vacío
    public Genero() {
    }

    // Constructor con todos los campos
    public Genero(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
    }
}
