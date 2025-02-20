package com.projetomaisprati.fixly.controllers;

import com.projetomaisprati.fixly.dto.ServicoDTO;
import com.projetomaisprati.fixly.services.ServicoService;
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
public class ServicoController {

    @Autowired
    private ServicoService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ServicoDTO> findById(@PathVariable Long id) {
        ServicoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping(value = "/prestadores/{prestadorId}")
    public ResponseEntity<Page<ServicoDTO>> findServicesByPrestadores(@PathVariable Long prestadorId, Pageable pageable) {
        Page<ServicoDTO> dto = service.findServicesByPrestadores(prestadorId, pageable);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PRESTADOR')")
    @PostMapping
    public ResponseEntity<ServicoDTO> insert(@Valid @RequestBody ServicoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PRESTADOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ServicoDTO> update(@PathVariable Long id, @Valid @RequestBody ServicoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PRESTADOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
