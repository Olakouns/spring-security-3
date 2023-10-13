package io.kouns.demospringsecurity3.services;


import io.kouns.demospringsecurity3.dto.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?>  authenticateUser(LoginRequest loginRequest);

    ResponseEntity<?> getUser();
}
