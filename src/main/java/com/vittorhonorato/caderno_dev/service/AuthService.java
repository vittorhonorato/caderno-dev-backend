package com.vittorhonorato.caderno_dev.service;

import com.vittorhonorato.caderno_dev.dto.request.LoginRequestDTO;
import com.vittorhonorato.caderno_dev.dto.request.RegisterRequestDTO;

public interface AuthService {
    void registerUser(RegisterRequestDTO requestDTO);
}
