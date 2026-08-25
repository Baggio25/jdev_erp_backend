package com.baggio.jdev_erp_backend.model;

import com.baggio.jdev_erp_backend.model.enums.TipoMovimentacaoProduto;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movimentacao_produto")
@SequenceGenerator(name = "seq_movimentacao_produto", sequenceName = "seq_movimentacao_produto", allocationSize = 1, initialValue = 1)
public class MovimentacaoProduto {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_movimentacao_produto")
    private Long id;

    @DecimalMin(value = "0.1", message = "Valor minimo de 0.1 deve ser informado")
    @Column(nullable = false)
    private Double quantidade = 1.0;

    @Column(nullable = false)
    private LocalDate dataMovimento;

    private BigDecimal valor = BigDecimal.ZERO;

    @NotNull(message = "Informe o tipo da movimentação")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacaoProduto tipoMovimentacaoProduto;

    @NotNull(message = "Produto deve ser informada corretamente")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "produto_fk"))
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id",
            nullable = true,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "pedido_fk"))
    private Pedido pedido;

    @NotNull(message = "Empresa deve ser informado")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "empresa_fk"))
    private Empresa empresa;

}