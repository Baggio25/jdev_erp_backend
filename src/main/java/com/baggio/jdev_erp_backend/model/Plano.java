package com.baggio.jdev_erp_backend.model;

import com.baggio.jdev_erp_backend.model.enums.TipoPlano;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plano")
@SequenceGenerator(name = "seq_plano", sequenceName = "seq_plano", allocationSize = 1, initialValue = 1)
public class Plano {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_plano")
    private Long id;

    @NotBlank(message = "O [nome] é obrigatório")
    @Size(max = 80, message = "O [nome] deve conter no máximo 80 caracteres")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "A [descricao] é obrigatória")
    @Size(max = 80, message = "A [descricao] deve conter no máximo 200 caracteres")
    @Column(nullable = false)
    private String descricao;

    private Boolean ativo;

    @NotNull(message = "O [valorMensal] é obrigatório")
    @Min(value = 49, message = "O [valorMensal] mínimo deve ser 49.00")
    @Max(value = 200, message = "O [valorMensal] máximo deve ser 200.00")
    @Column(name = "valor_mensal", nullable = false)
    private Double valorMensal;

    @NotNull(message = "O [limiteUsuario] é obrigatório")
    @Min(value = 1, message = "O [limiteUsuario] mínimo deve ser 1")
    @Max(value = 150, message = "O [limiteUsuario] máximo deve ser 150")
    @Column(name = "limite_usuario", nullable = false)
    private Integer limiteUsuario;

    @NotNull(message = "O [limiteClientes] é obrigatório")
    @Min(value = 1, message = "O [limiteClientes] mínimo deve ser 1")
    @Max(value = 150, message = "O [limiteClientes] máximo deve ser 150")
    @Column(name = "limite_clientes", nullable = false)
    private Integer limiteClientes;

    @NotNull(message = "O [tipoPlano] é obrigatório")
    @Column(name = "tipo_plano", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPlano tipoPlano;

}
