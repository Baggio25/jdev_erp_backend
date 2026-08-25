package com.baggio.jdev_erp_backend.model;

import com.baggio.jdev_erp_backend.model.enums.TipoClienteFuncionario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente_funcionario")
@SequenceGenerator(name = "seq_cliente_funcionario", sequenceName = "seq_cliente_funcionario", allocationSize = 1, initialValue = 1)
public class ClienteFuncionario {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente_funcionario")
    private Long id;

    @NotNull(message = "Informe o tipo de relação com a pessoa")
    @Column(nullable = false, name = "tipo_cliente_funcionario")
    @Enumerated(EnumType.STRING)
    private TipoClienteFuncionario tipoClienteFuncionario;

    @NotNull(message = "O usuário deve ser informado para criar o cadastro")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "usuario_fk"))
    private Usuario usuario;

    @NotNull(message = "A pessoa deve ser informada para criar o cadastro")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "pessoa_fk"))
    private Pessoa pessoa;

    @NotNull(message = "Empresa deve ser informado")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "empresa_fk"))
    private Empresa empresa;

}
