package com.backend.bibliomor_servidor.DTOs;

import java.time.LocalDate;

import com.backend.bibliomor_servidor.Models.Libro;
import com.backend.bibliomor_servidor.Models.Usuario;

public class ReservaLibroRequest {

    private LocalDate fechaAdquisicion;
    private LocalDate fechaDevolucion; 
    private Libro libro;
    private Usuario usuario; 

    // Getters y Setters
    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}