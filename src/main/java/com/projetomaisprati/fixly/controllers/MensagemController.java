package com.projetomaisprati.fixly.controllers;

import com.projetomaisprati.fixly.dto.MensagensDTO;
import com.projetomaisprati.fixly.entities.TipoMensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mensagens")
public class MensagemController {
    @Autowired
    private MensagemService mensagemService;

    @PostMapping
    public ResponseEntity<MensagensDTO> enviarMensagem(@RequestBody Map<String, Object> request) {
        MensagensDTO mensagem = mensagemService.enviarMensagem(
                ((Number) request.get("chatId")).longValue(),
                ((Number) request.get("remetenteId")).longValue(),
                (String) request.get("conteudo"),
                (TipoMensagem) request.get("tipo")
        );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(mensagem.getId()).toUri();
        return ResponseEntity.created(uri).body(mensagem);
    }

    @GetMapping
    public ResponseEntity<List<MensagensDTO>> listarMensagens(@RequestParam Long chatId) {
        List<MensagensDTO> dto = mensagemService.listarMensagens(chatId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/lida")
    public ResponseEntity<Void> marcarComoLida(@PathVariable Long id) {
        mensagemService.marcarComoLida(id);
        return ResponseEntity.ok().build();
    }
}
