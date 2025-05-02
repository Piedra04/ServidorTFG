package com.backend.bibliomor_servidor.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.bibliomor_servidor.DTOs.LoginRequest;
import com.backend.bibliomor_servidor.DTOs.RegisterRequest;
import com.backend.bibliomor_servidor.DTOs.UserAdminRequest;
import com.backend.bibliomor_servidor.Services.UsuarioService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    // Servicio de usuario
    @Autowired
    private UsuarioService usuarioService;

    // Ruta donde se crea un usuario nuevo al registrarse
    @PostMapping("/registro")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        boolean validar = usuarioService.registerUser(registerRequest.getNombre(), registerRequest.getApellidos(),
                registerRequest.getFechaNacimiento(), registerRequest.getCorreo(), registerRequest.getContraseña(),
                registerRequest.getCurso());

        if (validar) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Usuario creado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "No se ha podido crear el usuario"));
        }
    }

    // Ruta de login, para ver si las credenciales del usuario son correctas
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean validar = usuarioService.authenticateUser(loginRequest.getCorreo(), loginRequest.getContraseña());
        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Credenciales correctas"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Credenciales incorrectas"));
        }
    }


    // Ruta para crear un usuario manualmente (sin registro)
    @PostMapping("/crear")
    public ResponseEntity<?> createUser(@RequestBody UserAdminRequest createUserRequest) {
        boolean validar = usuarioService.createUser(
                createUserRequest.getNombre(),
                createUserRequest.getApellidos(),
                createUserRequest.getFechaNacimiento(),
                createUserRequest.getCorreo(),
                createUserRequest.getConstraseña(),
                createUserRequest.getCurso(),
                createUserRequest.getRol()
        );

        if (validar) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Usuario creado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "No se ha podido crear el usuario (correo ya registrado)"));
        }
    }

    // Ruta para editar un usuario existente
    @PutMapping("/editar")
    public ResponseEntity<?> modifyUser(@RequestBody UserAdminRequest modifyUserRequest) {
        boolean validar = usuarioService.modifyUser(
                modifyUserRequest.getCorreo(),
                modifyUserRequest.getNombre(),
                modifyUserRequest.getApellidos(),
                modifyUserRequest.getFechaNacimiento(),
                modifyUserRequest.getConstraseña(),
                modifyUserRequest.getCurso(),
                modifyUserRequest.getRol()
        );

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Usuario modificado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado el usuario para modificar"));
        }
    }

    // Ruta para eliminar un usuario por su correo
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> deleteUser(@RequestBody Map<String, String> request) {
        String correo = request.get("correo");
        boolean validar = usuarioService.deleteUser(correo);

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Usuario eliminado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado el usuario para eliminar"));
        }
    }
}

