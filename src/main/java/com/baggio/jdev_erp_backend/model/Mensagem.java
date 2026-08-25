package com.baggio.jdev_erp_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mensagem")
@SequenceGenerator(name = "seq_mensagem", sequenceName = "seq_mensagem", allocationSize = 1, initialValue = 1)
public class Mensagem {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mensagem")
    private Long id;

    @Column(nullable = false)
    private LocalDate dataEnvio;

    private Boolean lida = false;

    @NotBlank(message = "Conteúdo da mensagem deve ser informado")
    @Column(nullable = false, columnDefinition = "text")
    private String conteudo;

    @Column(nullable = false, columnDefinition = "text")
    private String arquivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chamado_id",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "chamado_fk"))
    private Chamado chamado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atendente_id",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "atendente_fk"))
    private Usuario atendente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "cliente_fk"))
    private Usuario cliente;

    @NotNull(message = "Empresa deve ser informado")
    @ManyToOne(fetch = FetchType.LAZY) /* LAZY -> Carrega a empresa quando tiver necessidade */
    @JoinColumn(name = "empresa_id",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "empresa_fk"))
    private Empresa empresa;

}