package com.projetomaisprati.fixly.controllers;

import com.projetomaisprati.fixly.dto.AvaliacaoDTO;
import com.projetomaisprati.fixly.services.AvaliacaoService;
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
public class AvaliadorController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<AvaliacaoDTO> findById(@PathVariable Long usuarioId, @PathVariable Long id) {
        AvaliacaoDTO dto = avaliacaoService.findByConsumidorId(id, usuarioId);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping
    public ResponseEntity<Page<AvaliacaoDTO>> findReviewByUser (@PathVariable Long usuarioId, Pageable pageable) {
        Page<AvaliacaoDTO> dto = avaliacaoService.findReviewByUser(usuarioId, pageable);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @PostMapping
    public ResponseEntity<AvaliacaoDTO> insert(@Valid @RequestBody AvaliacaoDTO dto) {
        dto = avaliacaoService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<AvaliacaoDTO> update (@PathVariable Long usuarioId, @PathVariable Long id, @Valid @RequestBody AvaliacaoDTO dto) {
        dto = avaliacaoService.update(usuarioId, id, dto);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long usuarioId, @PathVariable Long id) {
        avaliacaoService.delete(usuarioId, id);
        return ResponseEntity.noContent().build();
    }
}
