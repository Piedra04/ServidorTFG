package com.backend.bibliomor_servidor.Services;

import java.time.LocalDate;
import java.util.List;

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

    /**
     * Obtiene todos los usuarios.
     * 
     * @return Lista de todos los usuarios registrados.
     */
    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    /**
     * Obtiene un usuario por su correo.
     * 
     * @param correo Correo del usuario a buscar.
     * @return El usuario encontrado o null si no existe.
     */
    public Usuario getUserByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    /**
     * Obtiene un usuario por su ID.
     * 
     * @param id ID del usuario a buscar.
     * @return El usuario encontrado o null si no existe.
     */
    public Usuario getUserById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    /**
     * Registra un nuevo usuario.
     * 
     * @param nombre Nombre del usuario.
     * @param apellidos Apellidos del usuario.
     * @param fechaNacimiento Fecha de nacimiento del usuario.
     * @param correo Correo del usuario.
     * @param constraseña Contraseña del usuario.
     * @param curso Curso asociado al usuario.
     * @return true si el usuario se registró correctamente, false si el correo ya está registrado.
     */
    public boolean registerUser(String nombre, String apellidos, LocalDate fechaNacimiento, String correo,
            String constraseña, String curso) {

        if (usuarioRepository.existsByCorreo(correo)) {
            return false;
        } else {
            String hashedPassword = passwordHashService.hashPassword(constraseña);

            Rol rol = (curso == null || curso.trim().isEmpty()) ? Rol.PROFESOR : Rol.ALUMNO;

            Usuario newUser = new Usuario(nombre, apellidos, fechaNacimiento, hashedPassword, correo, rol, curso, fechaNacimiento);
            usuarioRepository.save(newUser);
            return true;
        }
    }

    /**
     * Verifica las credenciales del usuario.
     * 
     * @param correo Correo del usuario.
     * @param constraseña Contraseña en texto plano.
     * @return true si las credenciales son correctas, false en caso contrario.
     */
    public boolean authenticateUser(String correo, String constraseña) {
        if (usuarioRepository.existsByCorreo(correo)) {
            String hashedPassword = usuarioRepository.findByCorreo(correo).getContraseña();
            return passwordHashService.verifyPassword(constraseña, hashedPassword);
        } else {
            return false;
        }
    }

    /**
     * Elimina un usuario por su correo.
     * 
     * @param correo Correo del usuario a eliminar.
     * @return true si el usuario se eliminó correctamente, false si no se encontró.
     */
    public boolean deleteUser(String correo) {
        if (usuarioRepository.existsByCorreo(correo)) {
            Usuario user = usuarioRepository.findByCorreo(correo);
            usuarioRepository.delete(user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Modifica un usuario existente.
     * 
     * @param correo Correo del usuario a modificar.
     * @param nombre Nuevo nombre del usuario.
     * @param apellidos Nuevos apellidos del usuario.
     * @param fechaNacimiento Nueva fecha de nacimiento del usuario.
     * @param constraseña Nueva contraseña del usuario.
     * @param curso Nuevo curso asociado al usuario.
     * @param rol Nuevo rol del usuario.
     * @return true si el usuario se modificó correctamente, false si no se encontró.
     */
    public boolean modifyUser(String correo, String nombre, String apellidos, LocalDate fechaNacimiento,
            String constraseña, String curso, Rol rol) {
        if (usuarioRepository.existsByCorreo(correo)) {
            Usuario user = usuarioRepository.findByCorreo(correo);

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
                String hashedPassword = passwordHashService.hashPassword(constraseña);
                user.setContraseña(hashedPassword);
            }
            if (curso != null && !curso.trim().isEmpty()) {
                user.setCurso(curso);
            }
            if (rol != null) {
                user.setRol(rol);
            }

            usuarioRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Crea un usuario manualmente (sin registro).
     * 
     * @param nombre Nombre del usuario.
     * @param apellidos Apellidos del usuario.
     * @param fechaNacimiento Fecha de nacimiento del usuario.
     * @param correo Correo del usuario.
     * @param constraseña Contraseña del usuario.
     * @param curso Curso asociado al usuario.
     * @param rol Rol del usuario.
     * @return true si el usuario se creó correctamente, false si el correo ya está registrado.
     */
    public boolean createUser(String nombre, String apellidos, LocalDate fechaNacimiento, String correo,
            String constraseña, String curso, Rol rol) {
        if (usuarioRepository.existsByCorreo(correo)) {
            return false;
        } else {
            String hashedPassword = passwordHashService.hashPassword(constraseña);

            Usuario newUser = new Usuario(nombre, apellidos, fechaNacimiento, hashedPassword, correo, rol, curso, fechaNacimiento);
            usuarioRepository.save(newUser);
            return true;
        }
    }
}