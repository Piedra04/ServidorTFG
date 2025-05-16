package com.backend.bibliomor_servidor.DTOs;

import com.backend.bibliomor_servidor.Enum.Resultado;
import com.backend.bibliomor_servidor.Models.Ronda;
import com.backend.bibliomor_servidor.Models.Usuario;

public class ParticipacionRondaRequest {

    private Resultado resultado; 
    private Usuario usuario; 
    private Ronda ronda; 

    // Getters y Setters
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