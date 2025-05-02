package com.backend.bibliomor_servidor.Repositories;

import org.springframework.stereotype.Repository;

import com.backend.bibliomor_servidor.Models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Método para buscar un usuario por correo
    Usuario findByCorreo(String correo);

    // Método para verificar si un correo ya existe
    boolean existsByCorreo(String correo);

}
