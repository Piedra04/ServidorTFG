package com.backend.bibliomor_servidor.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "juegos")
public class Juego {

    // Atributos de la clase Juego

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(nullable = false)
    private int nUnidades;

    @OneToMany(mappedBy = "juego", cascade = CascadeType.ALL)
    private List<Campeonato> campeonatos;


    @OneToMany(mappedBy = "juego", cascade = CascadeType.ALL)
    private List<ReservaJuego> reservaJuegos;

    // Constructor vacío
    public Juego() {
    }

    // Constructor con todos los campos
    public Juego(String nombre, int nUnidades) {
        this.nombre = nombre;
        this.nUnidades = nUnidades;
    }

    // Getters y Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getnUnidades() {
        return nUnidades;
    }

    public void setnUnidades(int nUnidades) {
        this.nUnidades = nUnidades;
    }
}
