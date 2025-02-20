package com.projetomaisprati.fixly.controllers;

import com.projetomaisprati.fixly.dto.OrcamentoDTO;
import com.projetomaisprati.fixly.services.OrcamentoService;
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
@RequestMapping(value = "/orcamentos")
@Tag(name = "Orçamento", description = "APIs para gerenciar orçamentos entre clientes e prestadores")
public class OrcamentoController {

    @Autowired
    private OrcamentoService service;

    @Operation(summary = "Buscar orçamento por ID",
            description = "Retorna um orçamento específico pelo ID.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrcamentoDTO> findById(
            @Parameter(description = "ID do orçamento", example = "1") @PathVariable Long id) {
        OrcamentoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Buscar orçamentos de um prestador",
            description = "Retorna os orçamentos de um prestador específico.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping(value = "/prestadores/{prestadorId}")
    public ResponseEntity<Page<OrcamentoDTO>> findQuotesByProvider(
            @Parameter(description = "ID do prestador", example = "1") @PathVariable Long prestadorId,
            @Parameter(description = "Paginação e ordenação dos resultados") Pageable pageable) {
        Page<OrcamentoDTO> dto = service.findQuotesByProvider(prestadorId, pageable);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Buscar orçamentos de um cliente",
            description = "Retorna os orçamentos de um cliente específico.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping(value = "/clientes/{clienteId}")
    public ResponseEntity<Page<OrcamentoDTO>> findQuotesByCustomer(
            @Parameter(description = "ID do cliente", example = "1") @PathVariable Long clienteId,
            @Parameter(description = "Paginação e ordenação dos resultados") Pageable pageable) {
        Page<OrcamentoDTO> dto = service.findQuotesByCustomer(clienteId, pageable);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Criar novo orçamento",
            description = "Cria um novo orçamento com base nos dados fornecidos.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PRESTADOR')")
    @PostMapping
    public ResponseEntity<OrcamentoDTO> insert(
            @Parameter(description = "Dados do orçamento a ser criado") @Valid @RequestBody OrcamentoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(summary = "Atualizar orçamento",
            description = "Atualiza os dados de um orçamento existente.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PRESTADOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<OrcamentoDTO> update(
            @Parameter(description = "ID do orçamento a ser atualizado", example = "1") @PathVariable Long id,
            @Parameter(description = "Dados do orçamento a ser atualizado") @Valid @RequestBody OrcamentoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }
}
