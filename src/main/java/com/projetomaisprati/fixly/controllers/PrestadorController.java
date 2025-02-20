package com.projetomaisprati.fixly.controllers;

import com.projetomaisprati.fixly.dto.PrestadorDTO;
import com.projetomaisprati.fixly.dto.PrestadorDetalhadoDTO;
import com.projetomaisprati.fixly.services.PrestadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prestadores")
@Tag(name = "Prestador", description = "APIs para gerenciar prestadores de serviços")
public class PrestadorController {

    @Autowired
    private PrestadorService prestadorService;

    @Operation(summary = "Listar prestadores por cidade",
            description = "Lista prestadores que estão em uma cidade específica.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping
    public ResponseEntity<Page<PrestadorDTO>> findUsersByAddress(
            @Parameter(description = "Nome da cidade para filtrar os prestadores", example = "São Paulo")
            @RequestParam(name = "cidade", defaultValue = "") String cidade,
            @Parameter(description = "Paginação e ordenação dos resultados") Pageable pageable) {
        Page<PrestadorDTO> dto = prestadorService.findUsersByAddress(cidade, pageable);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Buscar detalhes de um prestador",
            description = "Retorna os detalhes de um prestador pelo seu ID.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<PrestadorDetalhadoDTO> findById(
            @Parameter(description = "ID do prestador", example = "1") @PathVariable Long id) {
        PrestadorDetalhadoDTO dto = prestadorService.findById(id);
        return ResponseEntity.ok(dto);
    }
}
