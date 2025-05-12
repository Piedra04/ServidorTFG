package com.backend.bibliomor_servidor.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.bibliomor_servidor.DTOs.UsuarioRequest;
import com.backend.bibliomor_servidor.Services.UsuarioService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Obtiene todos los usuarios.
     * 
     * @return ResponseEntity con la lista de usuarios o un mensaje NOT_FOUND si no hay usuarios.
     */
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        if (usuarioService.getAllUsers().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se han encontrado usuarios"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getAllUsers());
    }

    /**
     * Obtiene un usuario por su correo.
     * 
     * @param correo Correo del usuario a buscar.
     * @return ResponseEntity con el usuario encontrado o un mensaje NOT_FOUND si no existe.
     */
    @GetMapping("/{correo}")
    public ResponseEntity<?> getUserByCorreo(@RequestBody String correo) {
        if (usuarioService.getUserByCorreo(correo) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se ha encontrado el usuario"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getUserByCorreo(correo));
    }

    /**
     * Obtiene un usuario por su ID.
     * 
     * @param id ID del usuario a buscar.
     * @return ResponseEntity con el usuario encontrado o un mensaje NOT_FOUND si no existe.
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        if (usuarioService.getUserById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No se ha encontrado el usuario"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getUserById(id));
    }

    /**
     * Registra un nuevo usuario.
     * 
     * @param registerRequest Objeto con los datos del usuario a registrar.
     * @return ResponseEntity con estado CREATED si se registró correctamente, o BAD_REQUEST si falló.
     */
    @PostMapping("/registro")
    public ResponseEntity<?> register(@RequestBody UsuarioRequest registerRequest) {
        boolean validar = usuarioService.registerUser(
                registerRequest.getNombre(),
                registerRequest.getApellidos(),
                registerRequest.getFechaNacimiento(),
                registerRequest.getCorreo(),
                registerRequest.getContraseña(),
                registerRequest.getCurso()
        );

        if (validar) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Usuario creado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "No se ha podido crear el usuario"));
        }
    }

    /**
     * Inicia sesión verificando las credenciales del usuario.
     * 
     * @param loginRequest Objeto con las credenciales del usuario.
     * @return ResponseEntity con estado OK si las credenciales son correctas, o UNAUTHORIZED si son incorrectas.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioRequest loginRequest) {
        boolean validar = usuarioService.authenticateUser(loginRequest.getCorreo(), loginRequest.getContraseña());
        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Credenciales correctas"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Credenciales incorrectas"));
        }
    }

    /**
     * Crea un usuario manualmente (sin registro).
     * 
     * @param createUserRequest Objeto con los datos del usuario a crear.
     * @return ResponseEntity con estado CREATED si se creó correctamente, o BAD_REQUEST si falló.
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UsuarioRequest createUserRequest) {
        boolean validar = usuarioService.createUser(
                createUserRequest.getNombre(),
                createUserRequest.getApellidos(),
                createUserRequest.getFechaNacimiento(),
                createUserRequest.getCorreo(),
                createUserRequest.getContraseña(),
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

    /**
     * Modifica un usuario existente.
     * 
     * @param modifyUserRequest Objeto con los datos actualizados del usuario.
     * @return ResponseEntity con estado OK si se modificó correctamente, o NOT_FOUND si no existe.
     */
    @PutMapping
    public ResponseEntity<?> modifyUser(@RequestBody UsuarioRequest modifyUserRequest) {
        boolean validar = usuarioService.modifyUser(
                modifyUserRequest.getCorreo(),
                modifyUserRequest.getNombre(),
                modifyUserRequest.getApellidos(),
                modifyUserRequest.getFechaNacimiento(),
                modifyUserRequest.getContraseña(),
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

    /**
     * Elimina un usuario por su correo.
     * 
     * @param request Mapa que contiene el correo del usuario a eliminar.
     * @return ResponseEntity con estado OK si se eliminó correctamente, o NOT_FOUND si no existe.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        boolean validar = usuarioService.deleteUser(id);

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Usuario eliminado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se ha encontrado el usuario para eliminar"));
        }
    }
}  