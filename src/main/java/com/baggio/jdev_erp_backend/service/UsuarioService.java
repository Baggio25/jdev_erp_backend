package com.baggio.jdev_erp_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baggio.jdev_erp_backend.dto.LoginDTO;
import com.baggio.jdev_erp_backend.dto.TokenDTO;
import com.baggio.jdev_erp_backend.exception.MsgApiException;
import com.baggio.jdev_erp_backend.model.Usuario;
import com.baggio.jdev_erp_backend.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public TokenDTO login(LoginDTO loginDTO) {   
        Usuario usuario = buscaPorLogin(loginDTO.getLogin());
        if(usuario == null) {
            throw new MsgApiException("Usuário não econtrado");
        }

        var senhaValida = passwordEncoder.matches(loginDTO.getSenha(), usuario.getPassword());
        if(!senhaValida) {
            throw new MsgApiException("A senha ou usuário é inválido");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getSenha()));
        var token = jwtService.gerarToken(usuario);

        usuarioRepository.updateTokenSessaoLogin(usuario.getId(), token, usuario.getEmpresa().getId());

        return new TokenDTO(token);
    }

    public Usuario buscaPorLogin(String login) {
        return usuarioRepository.buscaPorLogin(login);
    }

    public List<Usuario> findAll(Long idEmpresa) {
        return usuarioRepository.findAll(idEmpresa);
    }

    public List<Usuario> buscaPorNome(String nome, Long idEmpresa) {
        return usuarioRepository.buscaPorNome(nome, idEmpresa);
    }

    public boolean existePorLogin(String login, Long idEmpresa) {
        return usuarioRepository.existePorLogin(login, idEmpresa);
    }

    public boolean existePorPessoa(Long idPessoa, Long idEmpresa) {
        return usuarioRepository.existePorPessoa(idPessoa, idEmpresa);
    }

    public boolean existePorNome(String nome, Long idEmpresa) {
        return usuarioRepository.existePorNome(nome, idEmpresa);
    }

    public boolean existePorNomeDiferenteId(Long id, String nome, Long idEmpresa) {
        return usuarioRepository.existePorNomeDiferenteId(id, nome, idEmpresa);
    }

    public boolean existeOutroUsuarioComPessoa(Long pessoaId, Long usuarioId, Long idEmpresa) {
        return usuarioRepository.existeOutroUsuarioComPessoa(pessoaId, usuarioId, idEmpresa);
    }

    public void deleteById(Long id, Long idEmpresa) {
        usuarioRepository.deleteById(id, idEmpresa);
    }

    public void updateTokenSessaoLogin(Long id, String token, Long idEmpresa) {
        usuarioRepository.updateTokenSessaoLogin(id, token, idEmpresa);
    }
}
