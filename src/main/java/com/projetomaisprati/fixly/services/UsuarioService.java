package com.projetomaisprati.fixly.services;

import com.projetomaisprati.fixly.dto.UsuarioDTO;
import com.projetomaisprati.fixly.entities.Usuario;
import com.projetomaisprati.fixly.repositories.UsuarioRepository;
import com.projetomaisprati.fixly.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new UsuarioDTO(usuario);
    }
}
