package com.backend.bibliomor_servidor.Repositories;

import org.springframework.stereotype.Repository;

import com.backend.bibliomor_servidor.Models.Juego;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface JuegoRepository extends JpaRepository<Juego, Long> {
    
    boolean existsByNombre(String nombre); // Método para verificar si un juego ya existe por su nombre

}
