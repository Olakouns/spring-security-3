package io.kouns.demospringsecurity3.services.impl;

import io.kouns.demospringsecurity3.dto.JwtAuthenticationResponse;
import io.kouns.demospringsecurity3.dto.LoginRequest;
import io.kouns.demospringsecurity3.security.TokenProvider;
import io.kouns.demospringsecurity3.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {


    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;


    public AuthServiceImpl(TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // TODO: 10/9/2023 Check if user account is validate

        SecurityContextHolder.getContext().setAuthentication(authentication);

        JwtAuthenticationResponse token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(token);
    }
}
