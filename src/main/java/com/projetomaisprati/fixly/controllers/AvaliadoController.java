package com.projetomaisprati.fixly.controllers;

import com.projetomaisprati.fixly.dto.AvaliacaoDTO;
import com.projetomaisprati.fixly.services.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Avaliado", description = "APIs para gerenciar avaliações recebidas pelos usuários")
public class AvaliadoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Operation(summary = "Buscar avaliação por ID",
            description = "Recupera a avaliação recebida pelo ID.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<AvaliacaoDTO> findById(
            @Parameter(description = "ID do prestador de serviço avaliado") @PathVariable Long prestadorId,
            @Parameter(description = "ID da avaliação") @PathVariable Long id
    ) {
        AvaliacaoDTO dto = avaliacaoService.findByPrestadorId(id, prestadorId);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Buscar avaliações de um usuário",
            description = "Retorna uma lista paginada de avaliações recebidas por usuários.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping
    public ResponseEntity<Page<AvaliacaoDTO>> findReviewByPrestador (
            @Parameter(description = "ID do prestador de serviço avaliado") @PathVariable Long prestadorId,
            @Parameter(description = "Paginação e ordenação dos resultados") Pageable pageable
    ) {
        Page<AvaliacaoDTO> dto = avaliacaoService.findReviewByPrestador(prestadorId, pageable);
        return ResponseEntity.ok(dto);
    }
}
