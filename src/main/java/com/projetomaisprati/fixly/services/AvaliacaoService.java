package com.projetomaisprati.fixly.services;

import com.projetomaisprati.fixly.entities.Usuario;
import com.projetomaisprati.fixly.repositories.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvaliacaoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void updateAverageRating(Usuario usuario) {
        Double avgRating = entityManager.createQuery("SELECT AVG(a.nota) FROM Avaliacao a WHERE a.avaliado = :avaliado", Double.class)
                .setParameter("avaliado", usuario)
                .getSingleResult();

        usuario.setAvaliacaoMedia(avgRating != null ? avgRating : 0.0);
        usuarioRepository.save(usuario);
    }
}
