package com.backend.bibliomor_servidor.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.bibliomor_servidor.Models.ParticipacionCampeonato;

@Repository
public interface ParticipacionCampeonatoRepository extends JpaRepository<ParticipacionCampeonato, Long> {
}