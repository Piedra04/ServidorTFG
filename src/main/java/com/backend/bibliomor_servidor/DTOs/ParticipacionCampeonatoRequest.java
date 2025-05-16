package com.backend.bibliomor_servidor.DTOs;

import com.backend.bibliomor_servidor.Models.Campeonato;
import com.backend.bibliomor_servidor.Models.Usuario;

public class ParticipacionCampeonatoRequest {


    private Usuario usuario;
    private Campeonato campeonato;

    // Getters y Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }
}