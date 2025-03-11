package com.projetomaisprati.fixly.services;

import com.projetomaisprati.fixly.dto.RoleDTO;
import com.projetomaisprati.fixly.dto.UsuarioDTO;
import com.projetomaisprati.fixly.dto.UsuarioInsertDTO;
import com.projetomaisprati.fixly.dto.UsuarioLogadoDTO;
import com.projetomaisprati.fixly.entities.Endereco;
import com.projetomaisprati.fixly.entities.Role;
import com.projetomaisprati.fixly.entities.Usuario;
import com.projetomaisprati.fixly.projections.UserDetailsProjection;
import com.projetomaisprati.fixly.repositories.EnderecoRepository;
import com.projetomaisprati.fixly.repositories.RoleRepository;
import com.projetomaisprati.fixly.repositories.UsuarioRepository;
import com.projetomaisprati.fixly.services.exceptions.DatabaseException;
import com.projetomaisprati.fixly.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioAutenticado usuarioAutenticado;

    @Autowired
    private AuthService authService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

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
    public UsuarioDTO insert(UsuarioInsertDTO dto) {
        Usuario entidade = new Usuario();
        copyDtoToEntity(dto, entidade);
        entidade.setDataCriacao(Instant.now());
        entidade.setSenha(passwordEncoder.encode(dto.getSenha()));

        entidade.getRoles().clear();
        Role role = roleRepository.searchByAuthority(dto.getTipo().name());
        entidade.getRoles().add(role);

        Usuario finalEntidade = entidade;
        Set<Endereco> enderecos = dto.getEnderecos().stream().map(
                endDto -> {
                    Endereco endereco = new Endereco();
                    endereco.setLogradouro(endDto.getLogradouro());
                    endereco.setNumero(endDto.getNumero());
                    endereco.setComplemento(endDto.getComplemento());
                    endereco.setBairro(endDto.getBairro());
                    endereco.setCidade(endDto.getCidade());
                    endereco.setEstado(endDto.getEstado());
                    endereco.setCep(endDto.getCep());
                    endereco.setPrincipal(true);
                    endereco.setUsuario(finalEntidade);
                    endereco = enderecoRepository.save(endereco);
                    return endereco;
                }).collect(Collectors.toSet());

        entidade.addEndereco(enderecos.stream().findFirst().get());

        entidade = usuarioRepository.save(entidade);
        return new UsuarioDTO(entidade);
    }

    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        try {
            Usuario entidade = usuarioRepository.getReferenceById(id);
            authService.validateSelfOrAdmin(entidade.getId());
            copyDtoToEntity(dto, entidade);

            entidade.getRoles().clear();
            Role role = roleRepository.searchByAuthority(dto.getTipo().name());
            entidade.getRoles().add(role);

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

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> result = usuarioRepository.searchUserAndRolesByEmail(username);
        if (result.size() == 0) {
            throw new UsernameNotFoundException("Email not found");
        }

        Usuario user = new Usuario();
        user.setEmail(result.get(0).getUsername());
        user.setSenha(result.get(0).getPassword());
        for (UserDetailsProjection projection : result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }

        return user;
    }

    @Transactional(readOnly = true)
    public UsuarioLogadoDTO getMe() {
        Usuario usuario = usuarioAutenticado.autenticated();
        return new UsuarioLogadoDTO(usuario);
    }

    private void copyDtoToEntity(UsuarioDTO dto, Usuario entidade) {
        entidade.setNome(dto.getNome());
        entidade.setEmail(dto.getEmail());
        entidade.setCpfOuCnpj(dto.getCpfOuCnpj());
        entidade.setNascimento(dto.getNascimento());
        entidade.setTipo(dto.getTipo());

        entidade.getRoles().clear();
        for (RoleDTO roleDTO : dto.getRoles()) {
            Role role = roleRepository.getReferenceById(roleDTO.getId());
            entidade.getRoles().add(role);
        }
    }
}
