package com.backend.bibliomor_servidor.DTOs;

public class ParticipacionCampeonatoRequest {


    private Long usuarioId; // ID del usuario participante
    private Long campeonatoId; // ID del campeonato

    // Getters y Setters
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getCampeonatoId() {
        return campeonatoId;
    }

    public void setCampeonatoId(Long campeonatoId) {
        this.campeonatoId = campeonatoId;
    }
}