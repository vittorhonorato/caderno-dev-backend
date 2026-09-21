package com.vittorhonorato.caderno_dev.service;

import com.vittorhonorato.caderno_dev.dto.request.CadernoRequestDTO;
import com.vittorhonorato.caderno_dev.dto.response.CadernoResponseDTO;

import java.util.List;

public interface CadernoDevService {
    CadernoResponseDTO create(CadernoRequestDTO requestDTO);
    List<CadernoResponseDTO> listAll();
    CadernoResponseDTO searchById(Long id);
    CadernoResponseDTO update(Long id, CadernoRequestDTO requestDTO);
    void delete(Long id);
}
