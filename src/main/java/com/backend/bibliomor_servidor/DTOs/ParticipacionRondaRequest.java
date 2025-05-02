package com.backend.bibliomor_servidor.DTOs;

import com.backend.bibliomor_servidor.Enum.Resultado;

public class ParticipacionRondaRequest {

    private Long id;
    private Resultado resultado; // Resultado de la participación
    private Long usuarioId; // ID del usuario participante
    private Long rondaId; // ID de la ronda

    // Constructor vacío
    public ParticipacionRondaRequest() {
    }

    // Constructor con todos los campos
    public ParticipacionRondaRequest(Long id, Resultado resultado, Long usuarioId, Long rondaId) {
        this.id = id;
        this.resultado = resultado;
        this.usuarioId = usuarioId;
        this.rondaId = rondaId;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getRondaId() {
        return rondaId;
    }

    public void setRondaId(Long rondaId) {
        this.rondaId = rondaId;
    }
}