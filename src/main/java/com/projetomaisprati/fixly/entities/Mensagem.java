package com.projetomaisprati.fixly.entities;

import com.projetomaisprati.fixly.common.BaseAuditingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_mensagem")
public class Mensagem extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String conteudo;
    private EstadoMensagem estado;
    private TipoMensagem tipo;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;
    @Column(name = "sender_id", nullable = false)
    private Long senderId;
    @Column(name = "receiver_id", nullable = false)
    private Long receiverId;
    private String mediaFilePath;
}
