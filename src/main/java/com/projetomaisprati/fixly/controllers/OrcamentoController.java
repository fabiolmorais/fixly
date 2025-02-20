package com.projetomaisprati.fixly.controllers;

import com.projetomaisprati.fixly.dto.OrcamentoDTO;
import com.projetomaisprati.fixly.services.OrcamentoService;
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
public class OrcamentoController {

    @Autowired
    private OrcamentoService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrcamentoDTO> findById(@PathVariable Long id) {
        OrcamentoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping(value = "/prestadores/{prestadorId}")
    public ResponseEntity<Page<OrcamentoDTO>> findQuotesByProvider(@PathVariable Long prestadorId, Pageable pageable) {
        Page<OrcamentoDTO> dto = service.findQuotesByProvider(prestadorId, pageable);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PRESTADOR')")
    @GetMapping(value = "/clientes/{clienteId}")
    public ResponseEntity<Page<OrcamentoDTO>> findQuotesByCustomer(@PathVariable Long clienteId, Pageable pageable) {
        Page<OrcamentoDTO> dto = service.findQuotesByCustomer(clienteId, pageable);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PRESTADOR')")
    @PostMapping
    public ResponseEntity<OrcamentoDTO> insert(@Valid @RequestBody OrcamentoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PRESTADOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<OrcamentoDTO> update(@PathVariable Long id, @Valid @RequestBody OrcamentoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }
}
