package com.projetomaisprati.fixly.services;

import com.projetomaisprati.fixly.entities.Usuario;
import com.projetomaisprati.fixly.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioAutenticado usuarioAutenticado;

    public boolean validateSelfOrAdmin(Long userId) {
        Usuario me = usuarioAutenticado.autenticated();

        if (!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)) {
            throw new ForbiddenException("Access denied");
        }
        return true;
    }

    public boolean validateSelfOrAdminOrClient(Long clientId, Long providerId) {
        Usuario me = usuarioAutenticado.autenticated();

        if (!me.hasRole("ROLE_ADMIN") && !me.getId().equals(clientId)) {
            if (!me.hasRole("ROLE_ADMIN") && me.getId().equals(providerId)) {
                return true;
            }
            throw new ForbiddenException("Access denied");
        }
        return true;
    }
}
