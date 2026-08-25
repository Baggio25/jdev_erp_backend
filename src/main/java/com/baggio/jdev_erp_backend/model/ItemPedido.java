package com.baggio.jdev_erp_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item_pedido")
@SequenceGenerator(name = "seq_item_pedido", sequenceName = "seq_item_pedido", allocationSize = 1, initialValue = 1)
public class ItemPedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_pedido")
    private Long id;

    @DecimalMin(value = "0.1", message = "Valor mínimo de 0.1 deve ser informado")
    @Column(nullable = false)
    private Double quantidade = 1.0;

    private BigDecimal subTotal = BigDecimal.ZERO;
    private BigDecimal desconto = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;

    @NotNull(message = "Produto deve ser informado")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "produto_fk"))
    private Produto produto;

    @NotNull(message = "Pedido deve ser informado")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id",
            nullable = false,
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