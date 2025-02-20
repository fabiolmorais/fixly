package com.projetomaisprati.fixly.controllers;

import com.projetomaisprati.fixly.dto.AvaliacaoDTO;
import com.projetomaisprati.fixly.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/avaliado/{prestadorId}/avaliacoes")
public class AvaliadoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<AvaliacaoDTO> findById(@PathVariable Long prestadorId, @PathVariable Long id) {
        AvaliacaoDTO dto = avaliacaoService.findByPrestadorId(id, prestadorId);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping
    public ResponseEntity<Page<AvaliacaoDTO>> findReviewByPrestador (@PathVariable Long prestadorId, Pageable pageable) {
        Page<AvaliacaoDTO> dto = avaliacaoService.findReviewByPrestador(prestadorId, pageable);
        return ResponseEntity.ok(dto);
    }
}
