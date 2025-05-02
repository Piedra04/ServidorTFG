package com.backend.bibliomor_servidor.DTOs;

import java.time.LocalDate;

public class ReservaLibroRequest {

    private Long id;
    private LocalDate fechaAdquisicion;
    private LocalDate fechaDevolucion; // Puede ser null si no se ha devuelto
    private String libroId; // ID del libro reservado
    private Long usuarioId; // ID del usuario que realiza la reserva

    // Constructor vacío
    public ReservaLibroRequest() {
    }

    // Constructor con todos los campos
    public ReservaLibroRequest(Long id, LocalDate fechaAdquisicion, LocalDate fechaDevolucion, String libroId, Long usuarioId) {
        this.id = id;
        this.fechaAdquisicion = fechaAdquisicion;
        this.fechaDevolucion = fechaDevolucion;
        this.libroId = libroId;
        this.usuarioId = usuarioId;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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