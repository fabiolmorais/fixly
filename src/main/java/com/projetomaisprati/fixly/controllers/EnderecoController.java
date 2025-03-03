package com.projetomaisprati.fixly.controllers;

import com.projetomaisprati.fixly.dto.EnderecoDTO;
import com.projetomaisprati.fixly.services.EnderecoService;
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
@RequestMapping(value = "/usuarios/{usuarioId}/enderecos")
@Tag(name = "Endereço", description = "APIs para gerenciar os endereços dos usuários")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @Operation(summary = "Buscar endereço por ID",
            description = "Retorna o endereço de um usuário específico pelo ID do endereço.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<EnderecoDTO> findById(
            @Parameter(description = "ID do usuário", example = "1") @PathVariable Long usuarioId,
            @Parameter(description = "ID do endereço", example = "1") @PathVariable Long id
    ) {
        EnderecoDTO dto = service.findById(id, usuarioId);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Buscar endereços de um usuário",
            description = "Retorna todos os endereços de um usuário com base no ID do usuário.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping
    public ResponseEntity<Page<EnderecoDTO>> findAdressByUser (
            @Parameter(description = "ID do usuário", example = "1") @PathVariable Long usuarioId,
            @Parameter(description = "Paginação e ordenação dos resultados") Pageable pageable
    ) {
        Page<EnderecoDTO> dto = service.findAdressByUser(usuarioId, pageable);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Adicionar novo endereço",
            description = "Adiciona um novo endereço para um usuário especificado.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @PostMapping
    public ResponseEntity<EnderecoDTO> insert(
            @Parameter(description = "ID do usuário ao qual o endereço será adicionado", example = "1") @PathVariable Long usuarioId,
            @Parameter(description = "Dados do novo endereço") @Valid @RequestBody EnderecoDTO dto
    ) {
        dto = service.insert(usuarioId, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(summary = "Atualizar endereço",
            description = "Atualiza os dados de um endereço existente.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<EnderecoDTO> update(
            @Parameter(description = "ID do endereço a ser atualizado", example = "1") @PathVariable Long id,
            @Parameter(description = "Dados do endereço a ser atualizado") @Valid @RequestBody EnderecoDTO dto
    ) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Excluir endereço",
            description = "Exclui um endereço específico pelo ID.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "ID do endereço a ser excluído", example = "1") @PathVariable Long id) {
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
