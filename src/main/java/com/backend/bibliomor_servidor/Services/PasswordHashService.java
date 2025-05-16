package com.backend.bibliomor_servidor.Services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class PasswordHashService {

    /**
     * Hashea una contraseña utilizando el algoritmo SHA-256.
     * 
     * @param password Contraseña en texto plano a hashear.
     * @return La contraseña hasheada en formato Base64.
     * @throws RuntimeException Si ocurre un error al hashear la contraseña.
     */
    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }

    /**
     * Verifica si una contraseña coincide con su hash.
     * 
     * @param password       Contraseña en texto plano a verificar.
     * @param hashedPassword Contraseña hasheada con la que se comparará.
     * @return true si la contraseña coincide con el hash, false en caso contrario.
     */
    public boolean verifyPassword(String password, String hashedPassword) {
        String hashedInput = hashPassword(password);
        return hashedInput.equals(hashedPassword);
    }
}