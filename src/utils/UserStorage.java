package utils;

import java.io.*;
import java.util.*;

/**
 * Utilidad Singleton para la gestión de usuarios en Catffee.
 * Permite registrar, autenticar y consultar usuarios.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
public class UserStorage {

    private static UserStorage instance;
    private final String filename;

    // Map email -> User
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, String> usernameToEmail = new HashMap<>(); // Relacion user->email para evitar tener que recorrer en O(n)

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
            utils.Error.mostrarErrorCritico("Error crítico al registrar usuario");
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
                    String user = parts[3];
                    String avatarPath = parts[4];
                    users.put(email, new User(email, password, nombreCompleto, user, avatarPath));
                }
            }
        } catch (FileNotFoundException e) {
            utils.Error.mostrarErrorCritico("Archivo de usuarios no encontrado: " + filename);
            throw new RuntimeException("Archivo de usuarios no encontrado: " + filename, e);
        } catch (IOException e) {
            utils.Error.mostrarErrorCritico("Error crítico al registrar usuario");
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }
    }

    public synchronized boolean addUser(String email, String password, String nombreCompleto, String user, String avatarPath) {
        String key = email.toLowerCase();
        if (users.containsKey(key) || usernameToEmail.containsKey(user.toLowerCase())) {
            return false;
        }
        User newUser = new User(email, password, nombreCompleto, user, avatarPath);
        users.put(key, newUser);
        usernameToEmail.put(user.toLowerCase(), key);
        return saveUserToFile(newUser);
    }

    // Guarda solo el nuevo usuario al final del archivo
    private boolean saveUserToFile(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(user.email + "," + user.password + "," + user.nombreCompleto + "," + user.user + "," + user.avatarPath);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar usuario: " + e.getMessage());
            utils.Error.mostrarErrorCritico("Error crítico al registrar usuario");
            // Si fallo, removemos del mapa para evitar inconsistencia
            users.remove(user.email.toLowerCase());
            usernameToEmail.remove(user.user.toLowerCase());
            return false;
        }
    }

    public boolean emailExists(String email) {
        return email != null && users.containsKey(email.toLowerCase());
    }

    public boolean userExists(String user) {
        return user != null && usernameToEmail.containsKey(user.toLowerCase());
    }

    public User getUserByEmail(String email) {
        if (email == null) return null;
        return users.get(email.toLowerCase());
    }

    // Clase interna para almacenar info del usuario
    public static class User {
        private final String email;
        private final String password;
        private final String nombreCompleto;
        private final String user;
        private final String avatarPath;

        User(String email, String password, String nombreCompleto, String user, String avatarPath) {
            this.email = email;
            this.password = password;
            this.nombreCompleto = nombreCompleto;
            this.user = user;
            this.avatarPath = avatarPath;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getNombreCompleto() {
            return nombreCompleto;
        }

        public String getUser() {
            return user;
        }

        public String getAvatarPath() {
            return avatarPath;
        }
    }

}
