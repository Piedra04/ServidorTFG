package com.backend.bibliomor_servidor.DTOs;

public class JuegoRequest {

    private String nombre;
    private Integer nUnidades;

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getnUnidades() {
        return nUnidades;
    }

    public void setnUnidades(Integer nUnidades) {
        this.nUnidades = nUnidades;
    }
}