package com.vittorhonorato.caderno_dev.controller;

import com.vittorhonorato.caderno_dev.dto.request.LoginRequestDTO;
import com.vittorhonorato.caderno_dev.dto.request.RegisterRequestDTO;
import com.vittorhonorato.caderno_dev.dto.response.LoginResponseDTO;
import com.vittorhonorato.caderno_dev.service.AuthService;
import com.vittorhonorato.caderno_dev.service.TokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;


    public AuthController(AuthService authService, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.authService = authService;

        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDTO requestDTO) {
        authService.registerUser(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRequestDTO.username(),
                loginRequestDTO.password()
        );

        Authentication authenticated = authenticationManager.authenticate(authentication);
        String token = tokenProvider.generateToken(authenticated);
        return ResponseEntity.ok(new LoginResponseDTO(token));

    }
}
