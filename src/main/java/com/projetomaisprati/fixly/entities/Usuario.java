package com.projetomaisprati.fixly.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {

    private static final int LAST_ACTIVATE_INTERVAL = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String cpfOuCnpj;
    private LocalDate nascimento;
    private String senha;
    private TipoUsuario tipo;
    private Double avaliacaoMedia;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataCriacao;
    private LocalDateTime lastSeen;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Endereco> enderecos = new HashSet<>();

    @OneToMany(mappedBy = "prestador", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Servico> servicos = new HashSet<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orcamento> orcamentosCliente = new ArrayList<>();

    @OneToMany(mappedBy = "prestador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orcamento> orcamentosPrestador = new ArrayList<>();

    @OneToMany(mappedBy = "avaliador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avaliacao> avaliacoesAvaliador = new ArrayList<>();

    @OneToMany(mappedBy = "avaliado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avaliacao> avaliacoesAvaliado = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chat> chatsAsSender;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chat> chatsAsRecipient;

    @ManyToMany
    @JoinTable(name = "tb_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Transient
    public boolean isUserOnline() {
        return lastSeen != null && lastSeen.isAfter(LocalDateTime.now().minusMinutes(LAST_ACTIVATE_INTERVAL));
    }

    public void addEndereco(Endereco endereco) {
        enderecos.add(endereco);
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public boolean hasRole(String roleName) {
        for (Role role : roles) {
            if (role.getAuthority().equals(roleName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
