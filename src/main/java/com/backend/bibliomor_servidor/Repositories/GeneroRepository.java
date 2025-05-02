package com.backend.bibliomor_servidor.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.bibliomor_servidor.Models.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
    boolean existsByNombre(String nombre);
}