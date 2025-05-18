package com.backend.bibliomor_servidor.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "participaciones_campeonatos")
public class ParticipacionCampeonato {


    // Atributos de la clase ParticipacionCampeonato
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "campeonato", nullable = false)
    private Campeonato campeonato;

    // Constructor vacío
    public ParticipacionCampeonato() {
    }

    // Constructor con los campos no nulos (nullable = false)
    public ParticipacionCampeonato(Usuario usuario, Campeonato campeonato) {
        this.usuario = usuario;
        this.campeonato = campeonato;
    }

    // Métodos getter y setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
