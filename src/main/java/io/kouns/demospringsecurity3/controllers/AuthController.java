package io.kouns.demospringsecurity3.controllers;


import io.kouns.demospringsecurity3.dto.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.kouns.demospringsecurity3.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) {
        return this.authService.authenticateUser(loginRequest);
    }

    @GetMapping("user")
    public ResponseEntity<?> getUser(){
        return authService.getUser();
    }
}
