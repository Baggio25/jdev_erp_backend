package com.baggio.jdev_erp_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {

  @NotBlank(message = "Login deve ser informado")
  private String login;

  @NotBlank(message = "Senha deve ser informado")
  private String senha;

}
