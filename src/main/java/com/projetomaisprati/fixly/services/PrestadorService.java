package com.projetomaisprati.fixly.services;

import com.projetomaisprati.fixly.dto.PrestadorDTO;
import com.projetomaisprati.fixly.dto.PrestadorDetalhadoDTO;
import com.projetomaisprati.fixly.entities.Usuario;
import com.projetomaisprati.fixly.repositories.UsuarioRepository;
import com.projetomaisprati.fixly.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PrestadorService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public Page<PrestadorDTO> findUsersByAddress (String cidade, Pageable pageable) {
        Page<Usuario> result = usuarioRepository.buscarUsuariosPorEndereco(cidade, pageable);
        return result.map(PrestadorDTO::new);
    }

    @Transactional(readOnly = true)
    public PrestadorDetalhadoDTO findById(Long id) {
        Usuario result = usuarioRepository.buscarUsuarioPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Id passado por parâmetro não corresponde ao Id de um Prestador"));
        return new PrestadorDetalhadoDTO(result);

    }

}
