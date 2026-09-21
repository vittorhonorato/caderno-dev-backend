package com.vittorhonorato.caderno_dev.service.impl;

import com.vittorhonorato.caderno_dev.dto.request.CadernoRequestDTO;
import com.vittorhonorato.caderno_dev.dto.response.CadernoResponseDTO;
import com.vittorhonorato.caderno_dev.entity.CadernoEntity;
import com.vittorhonorato.caderno_dev.service.CadernoDevService;
import com.vittorhonorato.caderno_dev.repository.CadernoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadernoDevServiceImpl implements CadernoDevService {
    private final CadernoRepository cadernoRepository;

    public CadernoDevServiceImpl(CadernoRepository cadernoRepository) {
        this.cadernoRepository = cadernoRepository;
    }

    @Override
    public CadernoResponseDTO create(CadernoRequestDTO requestDTO) {
        CadernoEntity entity = new CadernoEntity();
        entity.setAssunto(requestDTO.assunto());
        entity.setResumo(requestDTO.resumo());
        entity.setCategoria(requestDTO.categoria());

        return toResponse(cadernoRepository.save(entity));
    }

    @Override
    public List<CadernoResponseDTO> listAll() {
        return cadernoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CadernoResponseDTO searchById(Long id) {
        return toResponse(findById(id));
    }

    @Override
    public CadernoResponseDTO update(Long id, CadernoRequestDTO requestDTO) {
        CadernoEntity entity = findById(id);
        entity.setAssunto(requestDTO.assunto());
        entity.setResumo(requestDTO.resumo());
        entity.setCategoria(requestDTO.categoria());

        return toResponse(cadernoRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        CadernoEntity entity = findById(id);
        cadernoRepository.delete(entity);
    }

    private CadernoEntity findById(Long id) {
        return cadernoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Caderno não encontrado"));
    }

    private CadernoResponseDTO toResponse(CadernoEntity entity) {
        return new CadernoResponseDTO(
                entity.getId(),
                entity.getAssunto(),
                entity.getResumo(),
                entity.getCategoria(),
                entity.getCriadoEm(),
                entity.getAtualizadoEm()
        );
    }
}
