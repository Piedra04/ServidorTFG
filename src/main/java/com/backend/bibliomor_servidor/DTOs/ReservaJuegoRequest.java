package com.backend.bibliomor_servidor.DTOs;

import java.time.LocalDate;

import com.backend.bibliomor_servidor.Enum.Recreo;

public class ReservaJuegoRequest {

    private Long id;
    private LocalDate fecha;
    private Recreo recreo; // Recreo en el que se realiza la reserva
    private Long juegoId; // ID del juego reservado
    private Long usuarioId; // ID del usuario que realiza la reserva

    // Constructor vacío
    public ReservaJuegoRequest() {
    }

    // Constructor con todos los campos
    public ReservaJuegoRequest(Long id, LocalDate fecha, Recreo recreo, Long juegoId, Long usuarioId) {
        this.id = id;
        this.fecha = fecha;
        this.recreo = recreo;
        this.juegoId = juegoId;
        this.usuarioId = usuarioId;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Recreo getRecreo() {
        return recreo;
    }

    public void setRecreo(Recreo recreo) {
        this.recreo = recreo;
    }

    public Long getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(Long juegoId) {
        this.juegoId = juegoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}