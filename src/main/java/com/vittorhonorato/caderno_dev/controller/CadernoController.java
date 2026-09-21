package com.vittorhonorato.caderno_dev.controller;

import com.vittorhonorato.caderno_dev.dto.request.CadernoRequestDTO;
import com.vittorhonorato.caderno_dev.dto.response.CadernoResponseDTO;
import com.vittorhonorato.caderno_dev.service.CadernoDevService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("caderno-dev")
@RestController
public class CadernoController {
    private final CadernoDevService cadernoDevService;

    public CadernoController(CadernoDevService cadernoDevService) {
        this.cadernoDevService = cadernoDevService;
    }

    @PostMapping
    public ResponseEntity<CadernoResponseDTO> criar(
            @Valid @RequestBody CadernoRequestDTO request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cadernoDevService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<CadernoResponseDTO>> listar() {
        return ResponseEntity.ok(cadernoDevService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CadernoResponseDTO> buscar(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(cadernoDevService.searchById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CadernoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody CadernoRequestDTO request
    ) {
        return ResponseEntity.ok(
                cadernoDevService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {
        cadernoDevService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
