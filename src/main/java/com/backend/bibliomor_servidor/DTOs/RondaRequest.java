package com.backend.bibliomor_servidor.DTOs;

import java.time.LocalDate;


public class RondaRequest {

    private int nRonda;
    private LocalDate fecha;
    private Long campeonatoId; // ID del campeonato asociado

    // Getters y Setters
    public int getnRonda() {
        return nRonda;
    }

    public void setnRonda(int nRonda) {
        this.nRonda = nRonda;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getCampeonatoId() {
        return campeonatoId;
    }

    public void setCampeonatoId(Long campeonatoId) {
        this.campeonatoId = campeonatoId;
    }
}