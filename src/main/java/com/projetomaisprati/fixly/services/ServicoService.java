package com.projetomaisprati.fixly.services;

import com.projetomaisprati.fixly.dto.ServicoDTO;
import com.projetomaisprati.fixly.entities.Categoria;
import com.projetomaisprati.fixly.entities.Servico;
import com.projetomaisprati.fixly.entities.Usuario;
import com.projetomaisprati.fixly.repositories.ServicoRepository;
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
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    @Transactional(readOnly = true)
    public ServicoDTO findById (Long id) {
        Servico servico = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Recurso não encontrado"));
        return new ServicoDTO(servico);
    }

    @Transactional(readOnly = true)
    public Page<ServicoDTO> findServicesByPrestadores(Long prestadorId, Pageable pageable) {
        Page<Servico> result = repository.buscarServicosPorPrestador(prestadorId, pageable);
        return result.map(ServicoDTO::new);
    }

    @Transactional
    public ServicoDTO insert(ServicoDTO dto) {
        Servico servico = new Servico();
        copyDtoToEntity(dto, servico);
        servico = repository.save(servico);
        return new ServicoDTO(servico);
    }

    @Transactional
    public ServicoDTO update(Long id, ServicoDTO dto) {
        try {
            Servico servico = repository.getReferenceById(id);
            copyDtoToEntity(dto, servico);
            servico = repository.save(servico);
            return new ServicoDTO(servico);
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

    private void copyDtoToEntity(ServicoDTO dto, Servico entidade) {

        entidade.setNome(dto.getNome());
        entidade.setDescricao(dto.getDescricao());
        entidade.setPrecoMinimo(dto.getPrecoMinimo());
        entidade.setPrecoMaximo(dto.getPrecoMaximo());

        Usuario prestador = new Usuario();
        prestador.setId(dto.getPrestador().getId());
        entidade.setPrestador(prestador);

        Categoria categoria = new Categoria();
        categoria.setId(dto.getCategoria().getId());
        entidade.setCategoria(categoria);
    }
}
