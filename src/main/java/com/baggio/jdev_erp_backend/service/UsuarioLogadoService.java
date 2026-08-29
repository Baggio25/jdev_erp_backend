package com.baggio.jdev_erp_backend.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.baggio.jdev_erp_backend.model.Empresa;
import com.baggio.jdev_erp_backend.model.Usuario;
import com.baggio.jdev_erp_backend.security.UsuarioAutenticado;

@Service
public class UsuarioLogadoService {

  public UsuarioAutenticado getUsuarioAutenticado() {
    return (UsuarioAutenticado) SecurityContextHolder
                                  .getContext()
                                  .getAuthentication()
                                  .getPrincipal();
  }

  public Usuario getUsuarioLogado() {
    return getUsuarioAutenticado().getUsuario();
  }

  public Empresa getEmpresaLogada() {
    return getUsuarioLogado().getEmpresa();
  }

  public Long getEmpresaIdLogada() {
    return getEmpresaLogada().getId();
  } 

  public Long getUsuarioLogadoId() {
    return getUsuarioLogado().getId();
  }

}
