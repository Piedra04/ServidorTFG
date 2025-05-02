package com.backend.bibliomor_servidor.Services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.bibliomor_servidor.Enum.Rol;
import com.backend.bibliomor_servidor.Models.Usuario;
import com.backend.bibliomor_servidor.Repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordHashService passwordHashService;

    // Método para registrar un nuevo usuario
    public boolean registerUser(String nombre, String apellidos, LocalDate fechaNacimiento, String correo,
            String constraseña, String curso) {

        if (usuarioRepository.existsByCorreo(correo)) {
            return false;
        } else {
            // Hashear la contraseña antes de guardarla
            String hashedPassword = passwordHashService.hashPassword(constraseña);

            // Ponemos el rol de profesor o alumno, y si es profesor ponemos el curso a null
            Rol rol;
            if (curso == null || curso.trim().isEmpty()) {
                rol = Rol.PROFESOR;
                curso = null;
            } else {
                rol = Rol.ALUMNO;
            }

            // Crear un nuevo usuario y guardarlo en la base de datos
            Usuario newUser = new Usuario(nombre, apellidos, fechaNacimiento, hashedPassword, correo, rol, curso, fechaNacimiento);
            // Asignar el curso
            newUser.setCurso(curso);
            // Guardar el usuario en la base de datos
            usuarioRepository.save(newUser);
            return true;
        }
    }

    // Método para verificar las credenciales del usuario
    public boolean authenticateUser(String correo, String constraseña) {

        if (usuarioRepository.existsByCorreo(correo)) {
            String hashedPassword = usuarioRepository.findByCorreo(correo).getContraseña();
            return passwordHashService.verifyPassword(constraseña, hashedPassword);
        } else {
            return false;
        }
    }

    // Pasamos ahora a los metodos que puede realizar un administrador desde un
    // panel de control

    // Método para eliminar un usuario por su correo
    public boolean deleteUser(String correo) {
        if (usuarioRepository.existsByCorreo(correo)) {
            Usuario user = usuarioRepository.findByCorreo(correo);
            usuarioRepository.delete(user);
            return true;
        } else {
            return false;
        }
    }

    // Método para modificar un usuario por su correo
    public boolean modifyUser(String correo, String nombre, String apellidos, LocalDate fechaNacimiento,
            String constraseña, String curso, Rol rol) {
        if (usuarioRepository.existsByCorreo(correo)) {
            Usuario user = usuarioRepository.findByCorreo(correo);

            // Actualizar solo si el campo no es null o vacío
            if (nombre != null && !nombre.trim().isEmpty()) {
                user.setNombre(nombre);
            }
            if (apellidos != null && !apellidos.trim().isEmpty()) {
                user.setApellidos(apellidos);
            }
            if (fechaNacimiento != null) {
                user.setFechaNacimiento(fechaNacimiento);
            }
            if (constraseña != null && !constraseña.trim().isEmpty()) {
                // Hashear la contraseña antes de guardarla
                String hashedPassword = passwordHashService.hashPassword(constraseña);
                user.setContraseña(hashedPassword);
            }
            if (curso != null && !curso.trim().isEmpty()) {
                user.setCurso(curso);
            }
            if (rol != null) {
                user.setRol(rol);
            }

            // Guardar los cambios en la base de datos
            usuarioRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    // Metodo para crear un usuario a mano, sin registro
    public boolean createUser(String nombre, String apellidos, LocalDate fechaNacimiento, String correo,
            String constraseña, String curso, Rol rol) {
        if (usuarioRepository.existsByCorreo(correo)) {
            return false;
        } else {
            // Hashear la contraseña antes de guardarla
            String hashedPassword = passwordHashService.hashPassword(constraseña);

            // Crear un nuevo usuario y guardarlo en la base de datos
            Usuario newUser = new Usuario(nombre, apellidos, fechaNacimiento, hashedPassword, correo, rol, curso, fechaNacimiento);

            // Guardar el usuario en la base de datos
            usuarioRepository.save(newUser);
            return true;
        }
    }

}
