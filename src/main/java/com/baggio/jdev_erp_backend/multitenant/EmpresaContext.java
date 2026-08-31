package com.baggio.jdev_erp_backend.multitenant;

import org.springframework.stereotype.Component;

import com.baggio.jdev_erp_backend.service.UsuarioLogadoService;

@Component
public class EmpresaContext {

  private UsuarioLogadoService usuarioLogadoService;

  public Long getEmpresaId() {
    return usuarioLogadoService.getEmpresaIdLogada();
  }

}
