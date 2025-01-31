package com.projetomaisprati.fixly.controllers;

import com.projetomaisprati.fixly.dto.PrestadorDTO;
import com.projetomaisprati.fixly.dto.PrestadorDetalhadoDTO;
import com.projetomaisprati.fixly.entities.TipoUsuario;
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

    @GetMapping("/prestadores/{cidade}")
    public ResponseEntity<Page<PrestadorDTO>> findPrestadoresByCidade(
            @PathVariable String cidade,
            @RequestParam(value = "categoria", required = false) Long categoriaId,
            @RequestParam(value = "precoMin", required = false) Double precoMin,
            @RequestParam(value = "precoMax", required = false) Double precoMax,
            @RequestParam(value = "avaliacao", required = false) Double avaliacao,
            Pageable pageable) {
        Page<PrestadorDTO> dto = prestadorService.findPrestadoresByCidade(
                TipoUsuario.PRESTADOR, cidade, categoriaId, precoMin, precoMax, avaliacao, pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/prestadores/{id}")
    public ResponseEntity<PrestadorDetalhadoDTO> findPrestadorById(@PathVariable Long id) {
        PrestadorDetalhadoDTO dto = prestadorService.findPrestadorById(id, TipoUsuario.PRESTADOR);
        return ResponseEntity.ok(dto);
    }
}
