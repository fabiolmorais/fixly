package com.projetomaisprati.fixly.services;

import com.projetomaisprati.fixly.dto.UsuarioDTO;
import com.projetomaisprati.fixly.entities.Usuario;
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
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new UsuarioDTO(usuario);
    }

    @Transactional(readOnly = true)
    public Page<UsuarioDTO> findAll(Pageable pageable) {
        Page<Usuario> result = usuarioRepository.findAll(pageable);
        return result.map(UsuarioDTO::new); // O mesmo que result.map(x -> new UsuarioDTO(x))
    }

    @Transactional
    public UsuarioDTO insert(UsuarioDTO dto) {
        Usuario entidade = new Usuario();
        copyDtoToEntity(dto, entidade);
        entidade = usuarioRepository.save(entidade);
        return new UsuarioDTO(entidade);
    }

    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        try {
            Usuario entidade = usuarioRepository.getReferenceById(id);
            entidade.setNome(dto.getNome());
            entidade.setEmail(dto.getEmail());
            entidade.setSenha(dto.getSenha());
            entidade.setTipo(dto.getTipo());
            entidade = usuarioRepository.save(entidade);
            return new UsuarioDTO(entidade);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            usuarioRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(UsuarioDTO dto, Usuario entidade) {

        entidade.setNome(dto.getNome());
        entidade.setEmail(dto.getEmail());
        entidade.setSenha(dto.getSenha());
        entidade.setTipo(dto.getTipo());
        entidade.setDataCriacao(Instant.now());
    }
}
