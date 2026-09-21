package com.vittorhonorato.caderno_dev.service.impl;

import com.vittorhonorato.caderno_dev.dto.request.LoginRequestDTO;
import com.vittorhonorato.caderno_dev.dto.request.RegisterRequestDTO;
import com.vittorhonorato.caderno_dev.entity.UserEntity;
import com.vittorhonorato.caderno_dev.repository.AuthRepository;
import com.vittorhonorato.caderno_dev.service.AuthService;
import com.vittorhonorato.caderno_dev.service.TokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService, AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void registerUser(RegisterRequestDTO requestDTO) {

        boolean userExisted = authRepository.existsByUsername(requestDTO.username());

        if (userExisted) {
            throw new RuntimeException("Usuário já existe na base");
        }

        UserEntity entity = new UserEntity();
        entity.setEmail(requestDTO.email());
        entity.setName(requestDTO.username());
        entity.setPassword(passwordEncoder.encode(requestDTO.password()));

        authRepository.save(entity);
    }
}
