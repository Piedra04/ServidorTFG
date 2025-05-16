package com.backend.bibliomor_servidor.DTOs;

import java.time.LocalDate;

import com.backend.bibliomor_servidor.Enum.Recreo;
import com.backend.bibliomor_servidor.Models.Juego;
import com.backend.bibliomor_servidor.Models.Usuario;

public class ReservaJuegoRequest {

    private LocalDate fecha;
    private Recreo recreo;
    private Juego juego;
    private Usuario usuario;

    // Getters y Setters
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

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}