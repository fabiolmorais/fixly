package com.projetomaisprati.fixly.services;

import com.projetomaisprati.fixly.dto.EnderecoDTO;
import com.projetomaisprati.fixly.entities.Endereco;
import com.projetomaisprati.fixly.repositories.EnderecoRepository;
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

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Transactional(readOnly = true)
    public EnderecoDTO findById(Long id) {
        Endereco endereco = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new EnderecoDTO(endereco);
    }

    @Transactional(readOnly = true)
    public Page<EnderecoDTO> findAll(Pageable pageable) {
        Page<Endereco> result = repository.findAll(pageable);
        return result.map(x -> new EnderecoDTO(x));
    }

    @Transactional
    public EnderecoDTO insert(EnderecoDTO dto) {
        Endereco entidade = new Endereco();
        copyDtoToEntity(dto, entidade);
        entidade = repository.save(entidade);
        return new EnderecoDTO(entidade);
    }

    @Transactional
    public EnderecoDTO update(Long id, EnderecoDTO dto) {
        try {
            Endereco entidade = repository.getReferenceById(id);
            copyDtoToEntity(dto, entidade);
            entidade = repository.save(entidade);
            return new EnderecoDTO(entidade);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }


    private void copyDtoToEntity(EnderecoDTO dto, Endereco entidade) {

        entidade.setLogradouro(dto.getLogradouro());
        entidade.setNumero(dto.getNumero());
        entidade.setComplemento(dto.getComplemento());
        entidade.setBairro(dto.getBairro());
        entidade.setCidade(dto.getCidade());
        entidade.setEstado(dto.getEstado());
        entidade.setCep(dto.getCep());
        entidade.setPrincipal(dto.getPrincipal());

    }
}
