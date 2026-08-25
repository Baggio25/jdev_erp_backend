package com.baggio.jdev_erp_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "empresa", uniqueConstraints = {
        @UniqueConstraint(name = "unique_pessoa_empresa", columnNames = {"pessoa_id"})
})
@SequenceGenerator(name = "seq_empresa", sequenceName = "seq_empresa", allocationSize = 1, initialValue = 1)
public class Empresa {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_empresa")
    private Long id;

    @NotNull(message = "O [plano] é obrigatório")
    @ManyToOne
    @JoinColumn(name = "plano_id", nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "plano_fk"))
    private Plano plano;

    @Column(name = "total_usuarios")
    private Integer totalUsuarios;

    @Column(name = "total_clientes")
    private Integer totalClientes;

    @Column(name = "plano_ativo")
    private Boolean planoAtivo = false;

    private Boolean bloqueio = false;

    @NotEmpty(message = "A [logomarca] é obrigatória")
    @Column(columnDefinition = "text", nullable = false)
    private String logomarca;

    @Column(name = "vigencia_plano")
    private LocalDate vigenciaPlano;

    @NotNull(message = "A [pessoa] deve ser informada para cadastrar a instituição juridíca (PJ)")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
    private Pessoa pessoa;
}
