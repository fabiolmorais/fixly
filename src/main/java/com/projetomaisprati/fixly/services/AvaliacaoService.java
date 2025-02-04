package com.projetomaisprati.fixly.services;

import com.projetomaisprati.fixly.dto.AvaliacaoDTO;
import com.projetomaisprati.fixly.entities.Avaliacao;
import com.projetomaisprati.fixly.entities.Usuario;
import com.projetomaisprati.fixly.repositories.AvaliacaoRepository;
import com.projetomaisprati.fixly.repositories.UsuarioRepository;
import com.projetomaisprati.fixly.services.exceptions.DatabaseException;
import com.projetomaisprati.fixly.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public AvaliacaoDTO findByPrestadorId(Long id, Long prestadorId) {
        Avaliacao avaliacao = avaliacaoRepository.findByIdAndAvaliadoId(id, prestadorId).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new AvaliacaoDTO(avaliacao);
    }

    @Transactional(readOnly = true)
    public AvaliacaoDTO findByConsumidorId(Long id, Long usuarioId) {
        Avaliacao avaliacao = avaliacaoRepository.findByIdAndAvaliadorId(id, usuarioId).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new AvaliacaoDTO(avaliacao);
    }

    @Transactional(readOnly = true)
    public Page<AvaliacaoDTO> findReviewByPrestador(Long prestadorId, Pageable pageable) {
        Page<Avaliacao> result = avaliacaoRepository.findByAvaliadoId(prestadorId, pageable);
        return result.map(AvaliacaoDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<AvaliacaoDTO> findReviewByUser(Long usuarioId, Pageable pageable) {
        Page<Avaliacao> result = avaliacaoRepository.findByAvaliadorId(usuarioId, pageable);
        return result.map(AvaliacaoDTO::new);
    }

    @Transactional
    public AvaliacaoDTO insert(AvaliacaoDTO dto) {

        Avaliacao avaliacao = new Avaliacao();
        copyDtoToEntity(dto, avaliacao);

        Usuario avaliador = new Usuario();
        avaliador.setId(dto.getAvaliador().getId());
        avaliacao.setAvaliador(avaliador);

        Usuario avaliado = new Usuario();
        avaliado.setId(dto.getAvaliado().getId());
        avaliacao.setAvaliado(avaliado);

        avaliacao.setCriadoEm(Instant.now());
        avaliacao.setAtualizadoEm(Instant.now());
        avaliacao = avaliacaoRepository.save(avaliacao);
        return new AvaliacaoDTO(avaliacao);
    }

    @Transactional
    public AvaliacaoDTO update(Long id, AvaliacaoDTO dto) {
        try {
            Avaliacao avaliacao = avaliacaoRepository.getReferenceById(id);
            copyDtoToEntity(dto, avaliacao);
            avaliacao.setAtualizadoEm(Instant.now());
            avaliacao = avaliacaoRepository.save(avaliacao);
            return new AvaliacaoDTO(avaliacao);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!avaliacaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            avaliacaoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(AvaliacaoDTO dto, Avaliacao entidade) {
        entidade.setNota(dto.getNota());
        entidade.setComentario(dto.getComentario());
    }

}
