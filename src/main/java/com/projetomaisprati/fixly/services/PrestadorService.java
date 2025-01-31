package com.projetomaisprati.fixly.services;

import com.projetomaisprati.fixly.dto.PrestadorDTO;
import com.projetomaisprati.fixly.dto.PrestadorDetalhadoDTO;
import com.projetomaisprati.fixly.entities.TipoUsuario;
import com.projetomaisprati.fixly.entities.Usuario;
import com.projetomaisprati.fixly.repositories.UsuarioRepository;
import com.projetomaisprati.fixly.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrestadorService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public Page<PrestadorDTO> findPrestadoresByCidade(
            TipoUsuario prestador,
            String cidade,
            Long categoriaId,
            Double precoMin,
            Double precoMax,
            Double avaliacao,
            Pageable pageable) {
        Page<Usuario> prestadores = usuarioRepository.findPrestadoresByCidadeAndFilters(
                TipoUsuario.PRESTADOR, cidade, categoriaId, precoMin, precoMax, avaliacao, pageable);

        return prestadores.map(PrestadorDTO::new);
    }

    @Transactional(readOnly = true)
    public PrestadorDetalhadoDTO findPrestadorById(Long id, TipoUsuario tipoUsuario) {
        Usuario prestador = usuarioRepository.findPrestadorById(id, TipoUsuario.PRESTADOR)
                .orElseThrow(() -> new ResourceNotFoundException("Prestador não encontrado"));

        return new PrestadorDetalhadoDTO(prestador);
    }

}
