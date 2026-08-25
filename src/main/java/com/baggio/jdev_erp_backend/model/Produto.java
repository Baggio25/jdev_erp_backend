package com.baggio.jdev_erp_backend.model;

import com.baggio.jdev_erp_backend.model.enums.UnidadeMedida;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "produto", uniqueConstraints = {
        @UniqueConstraint(name="unique_sku_empresa", columnNames = {"sku", "empresa_id"}),
        @UniqueConstraint(name="unique_nome_produto_empresa", columnNames = {"nome", "empresa_id"}),
})
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", allocationSize = 1, initialValue = 1)
public class Produto {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
    private Long id;

    @NotBlank(message = "O [nome] é obrigatório")
    @Column(nullable = false)
    private String nome;

    private String descricao;

    @NotBlank(message = "Imagem deve ser informada")
    @Column(nullable = false, columnDefinition = "text")
    private String imagem;

    @Min(value = 1, message = "Preço deve ser maior que R$ 1.00 reais")
    @Column(nullable = false)
    private Double preco;

    @Min(value = 1, message = "Estoque deve ser maior que 1")
    @Column(nullable = false)
    private Double estoque;

    @Min(value = 1, message = "Estoque Minimo deve ser maior que 1")
    @Column(name = "estoque_minimo", nullable = false)
    private Double estoqueMinimo;

    @NotBlank(message = "Código SKU deve ser informado")
    @Column(nullable = false)
    private String sku;

    @Column(name = "codigo_barra")
    private String codigoBarra; /*Varios tipo de parafuso*/

    @NotBlank(message = "Unidade de medida deve ser informado")
    @Enumerated(EnumType.STRING)
    @Column(name = "unidade_medida", nullable = false)
    private UnidadeMedida unidadeMedida;

    @NotNull(message = "Categoria deve ser informada.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "categoria_fk"))
    private Categoria categoria;

    /*Refere-se ao cadastro da empresa em multitanci*/
    @NotNull(message = "Empresa deve ser informado")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "empresa_fk"))
    private Empresa empresa;

}
