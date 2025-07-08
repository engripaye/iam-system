package org.engripaye.iamsystem.service;

import org.engripaye.iamsystem.model.Role;
import org.engripaye.iamsystem.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    // Simulated in-memory user database
    private final Map<String, User> users = new HashMap<>();

    /**
     * Registers a new user.
     *
     * @param username the user's unique username
     * @param password the user's password (plain or hashed depending on real use case)
     * @param role     the user's role (ADMIN, USER, etc.)
     */
    public void register(String username, String password, Role role) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank.");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role must not be null.");
        }

        if (users.containsKey(username)) {
            throw new IllegalStateException("User already exists with username: " + username);
        }

        User user = new User(username, password, role);
        users.put(username, user);

        System.out.println("✅ Registered user: " + username + " with role: " + role.name());
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username the username to search for
     * @return the User object or null if not found
     */
    public User getByUsername(String username) {
        if (!users.containsKey(username)) {
            System.out.println("⚠️ No user found with username: " + username);
        }
        return users.get(username);
    }

    /**
     * Validates a user's credentials.
     *
     * @param username the user's username
     * @param password the user's password
     * @return true if credentials are valid, false otherwise
     */
    public boolean validate(String username, String password) {
        User user = users.get(username);

        if (user == null) {
            System.out.println("❌ Validation failed: user not found");
            return false;
        }

        if (!user.getPassword().equals(password)) {
            System.out.println("❌ Validation failed: incorrect password");
            return false;
        }

        System.out.println("✅ User validated successfully: " + username);
        return true;
    }
}
