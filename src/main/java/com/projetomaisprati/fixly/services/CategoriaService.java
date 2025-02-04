package com.projetomaisprati.fixly.services;

import com.projetomaisprati.fixly.dto.CategoriaDTO;
import com.projetomaisprati.fixly.entities.Categoria;
import com.projetomaisprati.fixly.repositories.CategoriaRepository;
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
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public Page<CategoriaDTO> findAll(Pageable pageable) {
        Page<Categoria> result = categoriaRepository.findAll(pageable);
        return result.map(CategoriaDTO::new);
    }

    @Transactional
    public CategoriaDTO insert(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        copyDtoToEntity(dto, categoria);
        categoria = categoriaRepository.save(categoria);
        return new CategoriaDTO(categoria);
    }

    @Transactional
    public CategoriaDTO update(Long id, CategoriaDTO dto) {
        try {
            Categoria categoria = categoriaRepository.getReferenceById(id);
            copyDtoToEntity(dto, categoria);
            categoria = categoriaRepository.save(categoria);
            return new CategoriaDTO(categoria);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(CategoriaDTO dto, Categoria entidade) {
        entidade.setNome(dto.getNome());
    }
}
