package com.baggio.jdev_erp_backend.model;

import com.baggio.jdev_erp_backend.model.enums.TipoPessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoa", uniqueConstraints = {
        @UniqueConstraint(name = "unique_incricao_estadual", columnNames = "inscricaoEstadual"),
        @UniqueConstraint(name = "unique_cnpj", columnNames = "cnpj"),
        @UniqueConstraint(name = "unique_cpf", columnNames = "cpf"),
        @UniqueConstraint(name = "unique_email", columnNames = "email"),
})
@SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", allocationSize = 1, initialValue = 1)
public class Pessoa {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    private Long id;

    @NotBlank(message = "Nome deve ser informado")
    @Column(nullable = false)
    private String nome;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @Column(name = "inscricao_estadual", unique = true)
    private String inscricaoEstadual;

    @CNPJ(message = "Informe o CNPJ corretamente", format = CNPJ.Format.ALPHANUMERIC)
    @Column(unique = true, nullable = false)
    private String cnpj;

    @NotBlank(message = "Informe o telefone corretamente")
    @Column(nullable = false)
    private String telefone;

    @CPF(message = "Informe o CPF corretamente")
    @Column(unique = true, nullable = false)
    private String cpf;

    @Email(message = "Informe o e-mail corretamente")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Informe o tipo da pessoa PF ou PJ")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pessoa", nullable = false)
    private TipoPessoa tipoPessoa;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(length = 1000)
    private String observacao;

    @NotBlank(message = "Informe o CEP corretamente")
    @Column(nullable = false)
    private String cep;

    @NotBlank(message = "Informe a rua/logradouro da forma correta")
    @Column(nullable = false)
    private String logradouro;

    @NotBlank(message = "Informe o Bairro corretamente")
    @Column(nullable = false)
    private String bairro;

    @NotBlank(message = "Informe o Estado corretamente")
    @Column(nullable = false)
    private String estado;

    @NotBlank(message = "Informe o Cidade corretamente")
    @Column(nullable = false)
    private String cidade;

    @NotBlank(message = "Informe o País corretamente")
    @Column(nullable = false)
    private String pais;

    @Column
    private String complemento;

    /*Refere-se ao cadastro da empresa em multitanci*/
    @NotNull(message = "Empresa deve ser informado")
    @ManyToOne(fetch = FetchType.LAZY) /* LAZY -> Carrega a empresa quando tiver necessidade */
    @JoinColumn(name = "empresa_id",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "empresa_fk"))
    private Empresa empresa;
}