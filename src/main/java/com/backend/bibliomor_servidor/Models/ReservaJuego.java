package com.backend.bibliomor_servidor.Models;

import java.time.LocalDate;

import com.backend.bibliomor_servidor.Enum.Recreo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservas_juegos")
public class ReservaJuego {

    // Atributos de la clase ReservaJuego

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Recreo recreo;

    // Hecho
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "juego", nullable = false)
    private Juego juego;

    // Hecho
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario", nullable = false)
    private Usuario usuario;

    // Constructor vacío
    public ReservaJuego() {
    }

    // Constructor con los campos no nulos (nullable = false)
    public ReservaJuego(LocalDate fecha, Recreo recreo, Juego juego, Usuario usuario) {
        this.fecha = fecha;
        this.recreo = recreo;
        this.juego = juego;
        this.usuario = usuario;
    }

    // Getters y Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
