package com.projetomaisprati.fixly.services;

import com.projetomaisprati.fixly.dto.EmailDTO;
import com.projetomaisprati.fixly.dto.NewPasswordDTO;
import com.projetomaisprati.fixly.entities.PasswordRecover;
import com.projetomaisprati.fixly.entities.Usuario;
import com.projetomaisprati.fixly.repositories.PasswordRecoverRepository;
import com.projetomaisprati.fixly.repositories.UsuarioRepository;
import com.projetomaisprati.fixly.services.exceptions.ForbiddenException;
import com.projetomaisprati.fixly.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    @Value("${email.password-recover.token.minutes}")
    private Long tokenMinutes;

    @Value("${email.password-recover.uri}")
    private String recoverUri;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioAutenticado usuarioAutenticado;

    @Autowired
    private PasswordRecoverRepository repository;

    @Autowired
    private EmailService emailService;

    public void validateSelfOrAdmin(Long userId) {
        Usuario me = usuarioAutenticado.autenticated();

        if (!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)) {
            throw new ForbiddenException("Access denied");
        }
    }

    public void validateSelfOrAdminOrClient(Long clientId, Long providerId) {
        Usuario me = usuarioAutenticado.autenticated();

        if (!me.hasRole("ROLE_ADMIN") && !me.getId().equals(clientId)) {
            if (!me.hasRole("ROLE_ADMIN") && me.getId().equals(providerId)) {
                return;
            }
            throw new ForbiddenException("Access denied");
        }
    }

    @Transactional
    public void createRecoverToken(EmailDTO body) {
        Usuario usuario = usuarioRepository.findByEmail(body.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado"));

        String token = UUID.randomUUID().toString();

        PasswordRecover recover = new PasswordRecover();
        recover.setEmail(body.getEmail());
        recover.setToken(token);
        recover.setExpiration(Instant.now().plusSeconds(tokenMinutes * 60L));
        recover = repository.save(recover);

        String bodyEmailText = "Acesse o link para definir uma nova senha.\n\n" +
                recoverUri + token + ". Esse link tem uma validade de " + tokenMinutes + " minutos.";

        emailService.sendEmail(body.getEmail(), "Recuperação de Senha" , bodyEmailText);
    }

    @Transactional
    public void saveNewPassword(NewPasswordDTO body) {
        List<PasswordRecover> result =  repository.searchValidTokens(body.getToken(), Instant.now());
        if (result.size() == 0) {
            throw new ResourceNotFoundException("Token inválido");
        }

        Usuario usuario = usuarioRepository.findByEmail(result.get(0).getEmail()).get();
        usuario.setSenha(passwordEncoder.encode(body.getSenha()));
        usuario = usuarioRepository.save(usuario);
    }
}
