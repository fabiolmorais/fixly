package com.projetomaisprati.fixly.controllers;

import com.projetomaisprati.fixly.dto.PrestadorDTO;
import com.projetomaisprati.fixly.dto.PrestadorDetalhadoDTO;
import com.projetomaisprati.fixly.services.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prestadores")
public class PrestadorController {

    @Autowired
    private PrestadorService prestadorService;

    @GetMapping
    public ResponseEntity<Page<PrestadorDTO>> findUsersByAddress(
            @RequestParam(name = "cidade", defaultValue = "") String cidade,
            Pageable pageable) {
        Page<PrestadorDTO> dto = prestadorService.findUsersByAddress(cidade, pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PrestadorDetalhadoDTO> findById(@PathVariable Long id) {
        PrestadorDetalhadoDTO dto = prestadorService.findById(id);
        return ResponseEntity.ok(dto);
    }
}
