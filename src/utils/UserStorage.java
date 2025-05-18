package utils;

import java.io.*;

// Implementación de singleton
public class UserStorage {

    private static UserStorage instance;
    private final String filename;

    private UserStorage(String filename) {
        this.filename = filename;
    }

    // Inicializa con nombre de archivo
    public static UserStorage init(String filename) {
        if (instance == null) {
            instance = new UserStorage(filename);
        }
        return instance;
    }

    // Acceso seguro a la instancia
    public static UserStorage getInstance() {
        if (instance == null) {
            throw new IllegalStateException("UserStorage no ha sido inicializado. Llama primero a init(filename).");
        }
        return instance;
    }
    
    /**
     * Verifica si el email y contraseña son válidos.
     */
    public boolean isValidUser(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 2) {
                    if (parts[0].equalsIgnoreCase(email.trim()) && parts[1].equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer usuarios: " + e.getMessage());
        }
        return false;
    }

    /**
     * Añade un nuevo usuario al archivo.
     */
    public boolean addUser(String email, String password, String nombreCompleto, String avatarPath) {
        if (emailExists(email)) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(email + "," + password + "," + nombreCompleto + "," + avatarPath);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verifica si ya existe un usuario con ese email.
     */
    public boolean emailExists(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length > 0 && parts[0].equalsIgnoreCase(email.trim())) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivo para verificar email: " + e.getMessage());
        }
        return false;
    }
}
