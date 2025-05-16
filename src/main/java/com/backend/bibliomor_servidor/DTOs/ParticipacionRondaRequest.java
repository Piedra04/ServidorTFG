package com.backend.bibliomor_servidor.DTOs;

import com.backend.bibliomor_servidor.Enum.Resultado;

public class ParticipacionRondaRequest {

    private Resultado resultado; // Resultado de la participación
    private Long usuarioId; // ID del usuario participante
    private Long rondaId; // ID de la ronda

    // Getters y Setters
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