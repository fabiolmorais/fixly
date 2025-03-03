package com.projetomaisprati.fixly.services;

import com.projetomaisprati.fixly.dto.EnderecoDTO;
import com.projetomaisprati.fixly.entities.Endereco;
import com.projetomaisprati.fixly.entities.Usuario;
import com.projetomaisprati.fixly.repositories.EnderecoRepository;
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

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public EnderecoDTO findById(Long id, Long usuarioId) {
        Endereco endereco = repository.findByIdAndUsuarioId(id, usuarioId).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        authService.validateSelfOrAdmin(usuarioId);
        return new EnderecoDTO(endereco);
    }

    @Transactional(readOnly = true)
    public Page<EnderecoDTO> findAdressByUser(Long usuarioId, Pageable pageable) {
        Page<Endereco> result = repository.findByUsuarioId(usuarioId, pageable);
        authService.validateSelfOrAdmin(usuarioId);
        return result.map(EnderecoDTO::new); // igual isso -> result.map(x -> new EnderecoDTO(x));
    }

    @Transactional
    public EnderecoDTO insert(Long usuarioId, EnderecoDTO dto) {
        Usuario usuario = usuarioRepository.getReferenceById(usuarioId);
        authService.validateSelfOrAdmin(usuarioId);

        if (dto.getPrincipal() == Boolean.TRUE) {
            desmarcarEnderecoPrincipal(usuarioId);
        }

        Endereco entidade = new Endereco();
        copyDtoToEntity(dto, entidade);
        entidade.setUsuario(usuario);
        entidade = repository.save(entidade);
        return new EnderecoDTO(entidade);
    }

    @Transactional
    public EnderecoDTO update(Long id, EnderecoDTO dto) {

        if (dto.getPrincipal() == Boolean.TRUE) {
            desmarcarEnderecoPrincipal(dto.getUsuario().getId());
        }

        try {
            Endereco entidade = repository.getReferenceById(id);
            authService.validateSelfOrAdmin(entidade.getUsuario().getId());
            copyDtoToEntity(dto, entidade);
            entidade = repository.save(entidade);
            return new EnderecoDTO(entidade);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            Endereco entidade = repository.getReferenceById(id);
            authService.validateSelfOrAdmin(entidade.getUsuario().getId());
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

    private void desmarcarEnderecoPrincipal(Long usuarioId) {
        repository.desmarcarEnderecoPrincipalPorUsuario(usuarioId);
    }
}
