package com.backend.bibliomor_servidor.DTOs;

import java.time.LocalDate;

public class ReservaLibroRequest {

    private LocalDate fechaAdquisicion;
    private LocalDate fechaDevolucion; // Puede ser null si no se ha devuelto
    private String libroId; // ID del libro reservado
    private Long usuarioId; // ID del usuario que realiza la reserva

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

    public String getLibroId() {
        return libroId;
    }

    public void setLibroId(String libroId) {
        this.libroId = libroId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}