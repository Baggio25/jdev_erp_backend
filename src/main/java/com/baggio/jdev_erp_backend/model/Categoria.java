package com.baggio.jdev_erp_backend.model;

import jakarta.persistence.*;
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
@Table(name = "categoria")
@SequenceGenerator(name = "seq_categoria", sequenceName = "seq_categoria", allocationSize = 1, initialValue = 1)
public class Categoria {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_categoria")
    private Long id;

    @NotBlank(message = "O [nome] é obrigatório")
    @Column(nullable = false)
    private String nome;

    /*Refere-se ao cadastro da empresa em multitanci*/
    @NotNull(message = "A [empresa] é obrigatória")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "empresa_fk"))
    private Empresa empresa;

}
