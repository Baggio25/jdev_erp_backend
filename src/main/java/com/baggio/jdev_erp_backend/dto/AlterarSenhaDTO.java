package com.baggio.jdev_erp_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlterarSenhaDTO {

  @NotNull(message = "Id do usuário deve ser informado")
  private Long id;

  @NotBlank(message = "Senha atual deve ser informada")
  @Size(min = 5, max = 50, message = "Senha atual deve ter entre 5 e 50 caracteres")
  private String senhaAtual;
  
  @NotBlank(message = "Senha atual deve ser informada")
  @Size(min = 5, max = 50, message = "Senha atual deve ter entre 5 e 50 caracteres")
  private String novaSenha;
  
  @NotBlank(message = "Senha atual deve ser informada")
  @Size(min = 5, max = 50, message = "Senha atual deve ter entre 5 e 50 caracteres")
  private String confirmaSenha;

}
