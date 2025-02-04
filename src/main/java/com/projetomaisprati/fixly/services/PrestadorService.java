package com.projetomaisprati.fixly.services;

import com.projetomaisprati.fixly.dto.PrestadorDTO;
import com.projetomaisprati.fixly.entities.Endereco;
import com.projetomaisprati.fixly.entities.TipoUsuario;
import com.projetomaisprati.fixly.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class PrestadorService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional(readOnly = true)
    public Page<PrestadorDTO> findPrestadoresByCity(String cidade, Pageable pageable) {
        Page<Endereco> enderecos = enderecoRepository.findByCidadeTeste(cidade, pageable);
        return (Page<PrestadorDTO>) enderecos.map(endereco -> {
            if (endereco.getUsuario().getTipo() == TipoUsuario.PRESTADOR) {
                return new PrestadorDTO(endereco.getUsuario());
            }
            return null;
        }).filter(Objects::nonNull);
    }

//    @Transactional(readOnly = true)
//    public PrestadorDetalhadoDTO findPrestadorById(Long id, TipoUsuario tipoUsuario) {
//        Usuario prestador = usuarioRepository.findPrestadorById(id, TipoUsuario.PRESTADOR)
//                .orElseThrow(() -> new ResourceNotFoundException("Prestador não encontrado"));
//
//        return new PrestadorDetalhadoDTO(prestador);
//    }

}
