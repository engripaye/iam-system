package org.engripaye.iamsystem.controller;

import org.engripaye.iamsystem.model.Role;
import org.engripaye.iamsystem.model.User;
import org.engripaye.iamsystem.security.JwtUtil;
import org.engripaye.iamsystem.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam Role role) {
        userService.register(username, password, role);
        return "Registered Successfully";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {
        if(userService.validate(username, password)) {
            User user = userService.getByUsername(username);
            return jwtUtil.generateToken(user.getUsername(), user.getRole());
        }
        return "Invalid Credentials";
    }
}
