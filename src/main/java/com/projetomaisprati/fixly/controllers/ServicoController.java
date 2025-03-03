package com.projetomaisprati.fixly.controllers;

import com.projetomaisprati.fixly.config.ResourceServerConfig;
import com.projetomaisprati.fixly.dto.ServicoDTO;
import com.projetomaisprati.fixly.services.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping(value = "/servicos")
@Tag(name = "Serviço", description = "APIs para gerenciar serviços")
@SecurityRequirement(name = ResourceServerConfig.SECURITY)
public class ServicoController {

    @Autowired
    private ServicoService service;

    @Operation(summary = "Buscar serviço por ID",
            description = "Retorna um serviço com base no ID informado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Serviço encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServicoDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
            })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ServicoDTO> findById(@PathVariable Long id) {
        ServicoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Listar serviços por prestador",
            description = "Retorna uma lista paginada de serviços com base no ID do prestador informado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de serviços retornada com sucesso")
            })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping(value = "/prestadores/{prestadorId}")
    public ResponseEntity<Page<ServicoDTO>> findServicesByPrestadores(@PathVariable Long prestadorId, Pageable pageable) {
        Page<ServicoDTO> dto = service.findServicesByPrestadores(prestadorId, pageable);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Criar um novo serviço",
            description = "Cria um novo serviço com os dados fornecidos",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Serviço criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PRESTADOR')")
    @PostMapping
    public ResponseEntity<ServicoDTO> insert(@Valid @RequestBody ServicoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(summary = "Atualizar serviço",
            description = "Atualiza os dados de um serviço com base no ID informado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Serviço atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
            })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PRESTADOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ServicoDTO> update(@PathVariable Long id, @Valid @RequestBody ServicoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Excluir serviço",
            description = "Exclui um serviço com base no ID informado",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Serviço excluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
            })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PRESTADOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
