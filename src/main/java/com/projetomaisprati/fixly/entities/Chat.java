package com.projetomaisprati.fixly.entities;

import com.projetomaisprati.fixly.common.BaseAuditingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_chat")
public class Chat extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Usuario sender;
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Usuario recipient;
    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    @OrderBy("createdDate DESC")
    private List<Mensagem> mensagens;

    @Transient
    public String getChatName(final Long senderId) {
        if (recipient.getId().equals(senderId)) {
            return sender.getNome();
        }
        return recipient.getNome();
    }

    @Transient
    public long getUnreadMessages(Long senderId) {
        return this.mensagens
                .stream()
                .filter(m -> m.getReceiverId().equals(senderId))
                .filter(m -> EstadoMensagem.SENT == m.getEstado())
                .count();
    }

    @Transient
    public String getLastMessage() {
        if (mensagens != null && !mensagens.isEmpty()) {
            if (mensagens.get(0).getTipo() != TipoMensagem.TEXT) {
                return "Anexo";
            }
            return mensagens.get(0).getConteudo();
        }
        return null;
    }

    @Transient
    public LocalDateTime getLastMessageTime() {
        if (mensagens != null && !mensagens.isEmpty()) {
            return mensagens.get(0).getCreatedDate();
        }
        return null;
    }
}
