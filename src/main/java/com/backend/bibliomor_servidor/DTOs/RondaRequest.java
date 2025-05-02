package com.backend.bibliomor_servidor.DTOs;

import java.time.LocalDate;


public class RondaRequest {

    private Long id;
    private int nRonda;
    private LocalDate fecha;
    private Long campeonatoId; // ID del campeonato asociado

    // Constructor vacío
    public RondaRequest() {
    }

    // Constructor con todos los campos
    public RondaRequest(Long id, int nRonda, LocalDate fecha, Long campeonatoId) {
        this.id = id;
        this.nRonda = nRonda;
        this.fecha = fecha;
        this.campeonatoId = campeonatoId;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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