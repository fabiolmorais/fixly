package com.projetomaisprati.fixly.services;

import com.projetomaisprati.fixly.dto.OrcamentoDTO;
import com.projetomaisprati.fixly.entities.Categoria;
import com.projetomaisprati.fixly.entities.Endereco;
import com.projetomaisprati.fixly.entities.Orcamento;
import com.projetomaisprati.fixly.entities.Usuario;
import com.projetomaisprati.fixly.repositories.OrcamentoRepository;
import com.projetomaisprati.fixly.repositories.UsuarioRepository;
import com.projetomaisprati.fixly.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public OrcamentoDTO findById(Long id) {
        Orcamento orcamento = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new OrcamentoDTO(orcamento);
    }

    @Transactional(readOnly = true)
    public Page<OrcamentoDTO> findQuotesByProvider(Long prestadorId, Pageable pageable) {
        Page<Orcamento> result = repository.buscarOrcamentoPorPrestador(prestadorId, pageable);
        return result.map(OrcamentoDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<OrcamentoDTO> findQuotesByCustomer(Long clienteId, Pageable pageable) {
        Page<Orcamento> result = repository.buscarOrcamentoPorCliente(clienteId, pageable);
        return result.map(OrcamentoDTO::new);
    }

    @Transactional
    public OrcamentoDTO insert(OrcamentoDTO dto) {
        Orcamento orcamento = new Orcamento();
        copyDtoToEntity(dto, orcamento);

        orcamento.setDataCriacao(Instant.now());

        Usuario cliente = new Usuario();
        cliente.setId(dto.getCliente().getId());
        orcamento.setCliente(cliente);

        Usuario prestador = new Usuario();
        prestador.setId(dto.getPrestador().getId());
        orcamento.setPrestador(prestador);

        Usuario entidade = usuarioRepository.getReferenceById(dto.getCliente().getId());
        orcamento.setEndereco(entidade.getEnderecos().stream().filter(Endereco::getPrincipal).findFirst().orElse(null));

        orcamento = repository.save(orcamento);
        return new OrcamentoDTO(orcamento);
    }

    @Transactional
    public OrcamentoDTO update(Long id, OrcamentoDTO dto) {
        try {
            Orcamento orcamento = repository.getReferenceById(id);
            copyDtoToEntity(dto, orcamento);
            orcamento = repository.save(orcamento);
            return new OrcamentoDTO(orcamento);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    private void copyDtoToEntity(OrcamentoDTO dto, Orcamento entidade) {

        entidade.setDescricao(dto.getDescricao());
        entidade.setPreco(dto.getPreco());
        entidade.setDataInicio(dto.getDataInicio());
        entidade.setDataFim(dto.getDataFim());
        entidade.setStatus(dto.getStatus());
    }
}
