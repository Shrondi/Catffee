package utils;

import java.io.*;
import java.util.*;

/**
 * Singleton que carga y mantiene en memoria usuarios desde un archivo CSV.
 */
public class UserStorage {

    private static UserStorage instance;
    private final String filename;

    // Map email -> User
    private final Map<String, User> users = new HashMap<>();

    private UserStorage(String filename) {
        this.filename = filename;
        loadUsersFromFile();
    }

    // Inicializa la instancia y carga los usuarios en memoria
    public static UserStorage init(String filename) {
        if (instance == null) {
            instance = new UserStorage(filename);
        }
        return instance;
    }

    // Acceso a la instancia singleton
    public static UserStorage getInstance() {
        if (instance == null) {
            throw new IllegalStateException("UserStorage no ha sido inicializado. Llama primero a init(filename).");
        }
        return instance;
    }

    // Carga usuarios del archivo en el mapa
    private void loadUsersFromFile() {
        users.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 4) {
                    String email = parts[0].trim().toLowerCase();
                    String password = parts[1];
                    String nombreCompleto = parts[2];
                    String avatarPath = parts[3];
                    users.put(email, new User(email, password, nombreCompleto, avatarPath));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Creado nuevo archivo");
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }
    }

    // Verifica si el email y contraseña son válidos usando el mapa en memoria
    public boolean isValidUser(String email, String password) {
        if (email == null || password == null) return false;
        User user = users.get(email.toLowerCase());
        return user != null && user.password.equals(password);
    }

    // Añade usuario nuevo si no existe, actualiza archivo y mapa
    public synchronized boolean addUser(String email, String password, String nombreCompleto, String avatarPath) {
        String key = email.toLowerCase();
        if (users.containsKey(key)) {
            return false;
        }
        User newUser = new User(email, password, nombreCompleto, avatarPath);
        users.put(key, newUser);
        return saveUserToFile(newUser);
    }

    // Guarda solo el nuevo usuario al final del archivo
    private boolean saveUserToFile(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(user.email + "," + user.password + "," + user.nombreCompleto + "," + user.avatarPath);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar usuario: " + e.getMessage());
            // Si fallo, removemos del mapa para evitar inconsistencia
            users.remove(user.email.toLowerCase());
            return false;
        }
    }

    // Verifica si email existe en el mapa
    public boolean emailExists(String email) {
        return email != null && users.containsKey(email.toLowerCase());
    }

    // Clase interna para almacenar info del usuario
    private static class User {
        final String email;
        final String password;
        final String nombreCompleto;
        final String avatarPath;

        User(String email, String password, String nombreCompleto, String avatarPath) {
            this.email = email;
            this.password = password;
            this.nombreCompleto = nombreCompleto;
            this.avatarPath = avatarPath;
        }
    }
}
