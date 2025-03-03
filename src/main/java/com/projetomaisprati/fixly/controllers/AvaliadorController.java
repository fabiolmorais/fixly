package com.projetomaisprati.fixly.controllers;

import com.projetomaisprati.fixly.dto.AvaliacaoDTO;
import com.projetomaisprati.fixly.services.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/avaliador/{usuarioId}/avaliacoes")
@Tag(name = "Avaliador", description = "APIs para gerenciar avaliações dos usuários que submeteram uma avaliação")
public class AvaliadorController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Operation(summary = "Buscar avaliação por ID",
            description = "Recupera a avaliação de um serviço pelo o usuário que criou/fez a avaliação.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<AvaliacaoDTO> findById(
            @Parameter(description = "ID do usuário que fez a avaliação") @PathVariable Long usuarioId,
            @Parameter(description = "ID da avaliação") @PathVariable Long id
    ) {
        AvaliacaoDTO dto = avaliacaoService.findByConsumidorId(id, usuarioId);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Buscar avaliações por usuário",
            description = "Retorna uma lista paginada de avaliações feitas por um usuário específico.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping
    public ResponseEntity<Page<AvaliacaoDTO>> findReviewByUser (
            @Parameter(description = "ID do usuário que fez as avaliações") @PathVariable Long usuarioId,
            @Parameter(description = "Paginação e ordenação dos resultados") Pageable pageable
    ) {
        Page<AvaliacaoDTO> dto = avaliacaoService.findReviewByUser(usuarioId, pageable);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Adicionar uma nova avaliação",
            description = "Permite que o usuário adicione uma avaliação.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @PostMapping
    public ResponseEntity<AvaliacaoDTO> insert(@Parameter(description = "Dados da nova avaliação") @Valid @RequestBody AvaliacaoDTO dto) {
        dto = avaliacaoService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(summary = "Atualizar avaliação",
            description = "Permite que o usuário atualize uma avaliação existente.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<AvaliacaoDTO> update (
            @Parameter(description = "ID do usuário (consumidor) que fez a avaliação") @PathVariable Long usuarioId,
            @Parameter(description = "ID da avaliação a ser atualizada") @PathVariable Long id,
            @Parameter(description = "Dados da avaliação a ser atualizada") @Valid @RequestBody AvaliacaoDTO dto
    ) {
        dto = avaliacaoService.update(usuarioId, id, dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Excluir avaliação",
            description = "Permite que um usuário exclua uma avaliação específica.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do usuário (consumidor) que fez a avaliação") @PathVariable Long usuarioId,
            @Parameter(description = "ID da avaliação a ser excluída") @PathVariable Long id
    ) {
        avaliacaoService.delete(usuarioId, id);
        return ResponseEntity.noContent().build();
    }
}
