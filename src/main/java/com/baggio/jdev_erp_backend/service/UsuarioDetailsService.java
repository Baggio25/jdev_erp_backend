package com.baggio.jdev_erp_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baggio.jdev_erp_backend.model.Usuario;
import com.baggio.jdev_erp_backend.repository.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.buscaPorLogin(username);

    if(usuario == null) {
      throw new UsernameNotFoundException("Usuário não encontrado");
    }

    return usuario;
  }

}
