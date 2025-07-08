package org.engripaye.iamsystem.controller;

import org.engripaye.iamsystem.model.Role;
import org.engripaye.iamsystem.model.User;
import org.engripaye.iamsystem.security.JwtUtil;
import org.engripaye.iamsystem.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        userService.register(request.getUsername(), request.getPassword(), request.getRole());
        return "Registered Successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        if (userService.validate(request.getUsername(), request.getPassword())) {
            User user = userService.getByUsername(request.getUsername());
            return jwtUtil.generateToken(user.getUsername(), user.getRole());
        }
        return "Invalid Credentials";
    }

    // ✅ DTOs
    public static class RegisterRequest {
        private String username;
        private String password;
        private Role role;

        // Getters and setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public Role getRole() { return role; }
        public void setRole(Role role) { this.role = role; }
    }

    public static class LoginRequest {
        private String username;
        private String password;

        // Getters and setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
    }
