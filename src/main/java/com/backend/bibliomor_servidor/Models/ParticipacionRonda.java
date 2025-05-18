package com.backend.bibliomor_servidor.Models;

import com.backend.bibliomor_servidor.Enum.Resultado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "participaciones_rondas")
public class ParticipacionRonda {

    // Atributos de la clase ParticipacionRonda
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Resultado resultado;

    @ManyToOne
    @JoinColumn(name = "usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ronda", nullable = false)
    private Ronda ronda;

    // Constructor vacío
    public ParticipacionRonda() {
    }

    // Constructor con los campos no nulos (nullable = false)
    public ParticipacionRonda(Resultado resultado, Usuario usuario, Ronda ronda) {
        this.resultado = resultado;
        this.usuario = usuario;
        this.ronda = ronda;
    }

    // Getters y Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ronda getRonda() {
        return ronda;
    }

    public void setRonda(Ronda ronda) {
        this.ronda = ronda;
    }
}
