package com.projetomaisprati.fixly.controllers;

import com.projetomaisprati.fixly.dto.EnderecoDTO;
import com.projetomaisprati.fixly.services.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable Long usuarioId, @PathVariable Long id) {
        EnderecoDTO dto = service.findById(id, usuarioId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<EnderecoDTO>> findAdressByUser (@PathVariable Long usuarioId, Pageable pageable) {
        Page<EnderecoDTO> dto = service.findAdressByUser(usuarioId, pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> insert(@PathVariable Long usuarioId, @Valid @RequestBody EnderecoDTO dto) {
        dto = service.insert(usuarioId, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EnderecoDTO> update(@PathVariable Long id, @Valid @RequestBody EnderecoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
