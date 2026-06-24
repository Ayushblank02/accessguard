package com.accessguard.controller;

import com.accessguard.domain.User;
import org.springframework.security.core.Authentication;
import com.accessguard.dto.LoginRequest;
import com.accessguard.dto.LoginResponse;
import com.accessguard.dto.RegisterRequest;
import com.accessguard.security.JwtService;
import com.accessguard.service.UserService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(
            UserService userService,
            JwtService jwtService) {

        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(request.getPassword());

        return userService.createUser(user);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        boolean authenticated =
                userService.authenticate(
                        request.getUsername(),
                        request.getPassword());

        if (!authenticated) {
            throw new RuntimeException(
                    "Invalid credentials");
        }

        String token =
                jwtService.generateToken(
                        request.getUsername());

        return new LoginResponse(token);
    }

    @GetMapping("/validate")
    public String validateToken(
            @RequestHeader("Authorization")
            String authHeader) {

        String token =
                authHeader.replace("Bearer ", "");

        if (!jwtService.isTokenValid(token)) {
            throw new RuntimeException(
                    "Invalid Token");
        }

        String username =
                jwtService.extractUsername(token);

        return "Token valid for user: " + username;
    }
    @GetMapping("/me")
    public String currentUser(
        Authentication authentication) {

    if (authentication == null) {
        return "No authenticated user";
    }

    return authentication.getName();
}
}