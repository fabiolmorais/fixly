package com.projetomaisprati.fixly.controllers;

import com.projetomaisprati.fixly.dto.*;
import com.projetomaisprati.fixly.services.AuthService;
import com.projetomaisprati.fixly.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/auth")
@Tag(name = "Recuperação de Conta", description = "APIs para recuperação de conta e susbtituição de senhas")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/recover-token")
    @Operation(
            summary = "Cria um token para o usuário",
            description = "Cria um token para o usuário, esse token é enviado por email com o link para o usuário fazer a troca da senha posteriormente."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Token enviado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha ao enviar o email")
    })
    public ResponseEntity<Void> createRecoverToken(@Valid @RequestBody EmailDTO body) {
        authService.createRecoverToken(body);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/new-password")
    @Operation(
            summary = "Cria uma nova senha para o usuário",
            description = "Cria uma nova senha para o usuário, com o token recebido por email o usuário acessa o link com o tokem e faz a troca de senha."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Senha alterada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Token inválido"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos")
    })
    public ResponseEntity<Void> saveNewPassword(@Valid @RequestBody NewPasswordDTO body) {
        authService.saveNewPassword(body);
        return ResponseEntity.noContent().build();
    }
}
