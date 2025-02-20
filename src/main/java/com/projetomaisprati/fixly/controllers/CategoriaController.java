package com.projetomaisprati.fixly.controllers;

import com.projetomaisprati.fixly.dto.CategoriaDTO;
import com.projetomaisprati.fixly.services.CategoriaService;
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
@RequestMapping(value = "/categorias")
@Tag(name = "Categoria", description = "APIs para gerenciar categorias de serviços")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Buscar todas as categorias",
            description = "Retorna uma lista paginada de todas as categorias disponíveis.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping
    public ResponseEntity<Page<CategoriaDTO>> findAll(@Parameter(description = "Paginação e ordenação dos resultados") Pageable pageable) {
        Page<CategoriaDTO> dto = categoriaService.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Adicionar nova categoria",
            description = "Adiciona uma nova categoria ao sistema. Somente administradores podem realizar essa operação.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CategoriaDTO> insert(@Parameter(description = "Dados da nova categoria") @Valid @RequestBody CategoriaDTO dto) {
        dto = categoriaService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(summary = "Atualizar categoria",
            description = "Atualiza os dados de uma categoria existente. Somente administradores podem realizar essa operação.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoriaDTO> update(
            @Parameter(description = "ID da categoria a ser atualizada", example = "1") @PathVariable Long id,
            @Parameter(description = "Dados da categoria a ser atualizada") @Valid @RequestBody CategoriaDTO dto) {
        dto = categoriaService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Excluir categoria",
            description = "Exclui uma categoria existente pelo ID. Somente administradores podem realizar essa operação.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "ID da categoria a ser excluída", example = "1") @PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
