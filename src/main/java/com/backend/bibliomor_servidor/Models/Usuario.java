package com.backend.bibliomor_servidor.Models;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.backend.bibliomor_servidor.Enum.Rol;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    // Atributos de la clase Usuario

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellidos;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false, unique = true, length = 50)
    private String correo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @Column(nullable = true, length = 20)
    private String curso;

    @CreationTimestamp // Se establece la fecha de registro automáticamente al crear el usuario
    @Column(nullable = false, updatable = false) // No se puede actualizar la fecha de registro una vez creada
    private LocalDate fechaRegistro;

    @OneToMany(mappedBy = "ganador", cascade = CascadeType.ALL)
    private List<Campeonato> campeonatosGanados;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ParticipacionCampeonato> participacionCampeonato;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ParticipacionRonda> participacionRonda;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ReservaJuego> reservaJuego;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ReservaLibro> reservaLibro;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con todos los campos
    public Usuario(String nombre, String apellidos, LocalDate fechaNacimiento, String contrasena,
            String correo,
            Rol rol, String curso, LocalDate fechaRegistro) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.contrasena = contrasena;
        this.correo = correo;
        this.rol = rol;
        this.curso = curso;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}