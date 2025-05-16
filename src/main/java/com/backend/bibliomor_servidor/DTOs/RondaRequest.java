package com.backend.bibliomor_servidor.DTOs;

import java.time.LocalDate;

import com.backend.bibliomor_servidor.Models.Campeonato;


public class RondaRequest {

    private int nRonda;
    private LocalDate fecha;
    private Campeonato campeonato;

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

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }
}