package com.backend.bibliomor_servidor.Models;

import java.time.LocalDate;
import java.util.List;

import com.backend.bibliomor_servidor.Enum.EstadoTorneo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "campeonatos")
public class Campeonato {

    // Atributos de la clase Campeonato

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @Lob
    @Column(nullable = true, columnDefinition = "TEXT")
    private String descripcion;

    // Hecho
    @ManyToOne
    @JoinColumn(name = "ganador", nullable = true)
    private Usuario ganador;

    // Hecho
    @ManyToOne
    @JoinColumn(name = "juego", nullable = false)
    private Juego juego;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTorneo estado;

    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL)
    private List<ParticipacionCampeonato> campeonato;

    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL)
    private List<Ronda> rondas;

    // Constructor vacío
    public Campeonato() {
    }

    // Constructor con todos los campos
    public Campeonato(LocalDate fechaInicio, LocalDate fechaFin, String descripcion, Usuario ganador, Juego juego, EstadoTorneo estado) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
        this.ganador = ganador;
        this.juego = juego;
        this.estado = estado;
    }

    // Getters y Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getGanador() {
        return ganador;
    }

    public void setGanador(Usuario ganador) {
        this.ganador = ganador;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public EstadoTorneo getEstado() {
        return estado;
    }

    public void setEstado(EstadoTorneo estado) {
        this.estado = estado;
    }
}
