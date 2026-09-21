package com.vittorhonorato.caderno_dev.service;

import org.springframework.security.core.Authentication;

public interface TokenProvider {
    String generateToken(Authentication authentication);
    String getSubject(String token);
    boolean isValid(String token);
}
