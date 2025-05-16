package com.backend.bibliomor_servidor.DTOs;

import java.time.LocalDate;

import com.backend.bibliomor_servidor.Enum.EstadoTorneo;
import com.backend.bibliomor_servidor.Models.Juego;
import com.backend.bibliomor_servidor.Models.Usuario;

public class CampeonatoRequest {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String descripcion;
    private Juego juego;
    private Usuario ganador;
    private EstadoTorneo estado;

    // Getters y Setters

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Usuario getGanador() {
        return ganador;
    }

    public void setGanador(Usuario ganador) {
        this.ganador = ganador;
    }

    public EstadoTorneo getEstado() {
        return estado;
    }

    public void setEstado(EstadoTorneo estado) {
        this.estado = estado;
    }
}