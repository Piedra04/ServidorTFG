package com.backend.bibliomor_servidor.DTOs;

public class ParticipacionCampeonatoRequest {

    private Long id;
    private Long usuarioId; // ID del usuario participante
    private Long campeonatoId; // ID del campeonato

    // Constructor vacío
    public ParticipacionCampeonatoRequest() {
    }

    // Constructor con todos los campos
    public ParticipacionCampeonatoRequest(Long id, Long usuarioId, Long campeonatoId) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.campeonatoId = campeonatoId;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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