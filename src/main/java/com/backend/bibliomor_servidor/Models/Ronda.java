package com.backend.bibliomor_servidor.Models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "rondas")
public class Ronda {

    // Atributos de la clase Ronda

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int nRonda;

    @Column(nullable = false)
    private LocalDate fecha;

    @ManyToOne(optional = false)
    @JoinColumn(name = "campeonato", nullable = false)
    private Campeonato campeonato;

    @OneToMany(mappedBy = "ronda")
    private List<ParticipacionRonda> participacionesRonda;

    // Constructor vacío
    public Ronda() {
    }

    // Constructor con los campos no nulos
    public Ronda(int nRonda, LocalDate fecha, Campeonato campeonato) {
        this.nRonda = nRonda;
        this.fecha = fecha;
        this.campeonato = campeonato;
    }

    // Getters y Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNRonda() {
        return nRonda;
    }

    public void setNRonda(int nRonda) {
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