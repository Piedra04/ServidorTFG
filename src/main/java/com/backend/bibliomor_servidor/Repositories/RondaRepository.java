package com.backend.bibliomor_servidor.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.bibliomor_servidor.Models.Ronda;

@Repository
public interface RondaRepository extends JpaRepository<Ronda, Long> {
}