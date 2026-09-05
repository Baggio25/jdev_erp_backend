package com.baggio.jdev_erp_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baggio.jdev_erp_backend.dto.AlterarSenhaDTO;
import com.baggio.jdev_erp_backend.dto.LoginDTO;
import com.baggio.jdev_erp_backend.dto.TokenDTO;
import com.baggio.jdev_erp_backend.exception.MsgApiException;
import com.baggio.jdev_erp_backend.model.ClienteFuncionario;
import com.baggio.jdev_erp_backend.model.Role;
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

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Autowired
    private ClienteFuncionarioService clienteFuncionarioService;

    @Autowired
    private RoleService roleService;

    public TokenDTO login(LoginDTO loginDTO) {
        Usuario usuario = buscaPorLogin(loginDTO.getLogin());
        if (usuario == null) {
            throw new MsgApiException("Usuário não econtrado");
        }

        var senhaValida = passwordEncoder.matches(loginDTO.getSenha(), usuario.getPassword());
        if (!senhaValida) {
            throw new MsgApiException("A senha ou usuário é inválido");
        }

        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getSenha()));
        var token = jwtService.gerarToken(usuario);

        usuarioRepository.updateTokenSessaoLogin(usuario.getId(), token, usuario.getEmpresa().getId());

        return new TokenDTO(token);
    }

    public Usuario salvar(Usuario usuario) {
        if (usuarioRepository.existePorLogin(usuario.getLogin(), usuarioLogadoService.getEmpresaIdLogada())) {
            throw new MsgApiException("O e-email escolhido já existe. Informe outro e-mail para login.");
        }

        if (usuario.getSenha().length() < 5) {
            throw new MsgApiException("A senha deve ter mais de 5 caracteres.");
        }

        if (usuarioRepository.existePorPessoa(usuario.getClienteFuncionario().getPessoa().getId(),
                usuarioLogadoService.getEmpresaIdLogada())) {
            throw new MsgApiException("Já existe um usuário vinculado a esta pessoa.");
        }

        if (usuario.getClienteFuncionario() == null) {
            throw new MsgApiException("Não foi informado o registro de pessoa/ cliente ou funcioário para o usuário.");
        }

        ClienteFuncionario clienteFuncionario = clienteFuncionarioService
                .findByPessoa(usuario.getClienteFuncionario().getPessoa().getId(),
                        usuarioLogadoService.getEmpresaIdLogada());
        List<Role> roles = roleService.buscaPorAcesso("ROLE_USER");

        usuario.setAcessos(roles);
        usuario.setClienteFuncionario(clienteFuncionario);
        usuario.setEmpresa(usuarioLogadoService.getEmpresaLogada());

        usuario = usuarioRepository.saveAndFlush(usuario);

        clienteFuncionario.setUsuario(usuario);
        clienteFuncionarioService.salvar(clienteFuncionario);

        return usuario;
    }

    public Usuario atualizar(Usuario usuario) {
        if (usuarioRepository.existeOutroUsuarioComPessoa(usuario.getClienteFuncionario().getPessoa().getId(),
                usuario.getId(), usuarioLogadoService.getEmpresaIdLogada())) {
            throw new MsgApiException("Existe um usuário associado a pessoa que foi selecionada.");
        }

        Usuario usuarioBanco = buscarPorId(usuario.getId(), usuarioLogadoService.getEmpresaIdLogada()).get();
        if (usuario.getAcessos() == null || usuario.getAcessos().isEmpty()) {
            usuario.setAcessos(usuarioBanco.getAcessos());
        }

        ClienteFuncionario clienteFuncionario = clienteFuncionarioService.findByPessoa(
                usuario.getClienteFuncionario().getId(),
                usuarioLogadoService.getEmpresaIdLogada());
        usuario.setSenha(usuarioBanco.getSenha());
        usuario.setClienteFuncionario(clienteFuncionario);
        usuario.setEmpresa(usuarioLogadoService.getEmpresaLogada());

        return usuarioRepository.save(usuario);
    }

    public void alterarSenha(AlterarSenhaDTO dto) {
        Usuario usuario = usuarioRepository.buscarPorId(dto.getId(), usuarioLogadoService.getEmpresaIdLogada()).get();

        if (usuario == null) {
            throw new MsgApiException("Usuário não encontrado.");
        }

        if (usuario.isEnabled()) {
            throw new MsgApiException("Usuário bloqueado, entre em contato com o administrador do sistema.",
                    HttpStatus.UNAUTHORIZED);
        }

        if (usuario.getEmpresa().getBloqueio()) {
            throw new MsgApiException("Empresa bloqueada, entre em contato com o administrador do sistema.",
                    HttpStatus.UNAUTHORIZED);
        }

        if (!dto.getNovaSenha().equals(dto.getConfirmaSenha())) {
            throw new MsgApiException("A confirmação da senha não confere.");
        }

        /* COnferencia se a nova senha igual a do banco e emite msg */
        if (passwordEncoder.matches(dto.getNovaSenha(), usuario.getSenha())) {
            throw new MsgApiException("A nova senha deve ser diferente da atual");
        }

        /* Conferencia se senha atual é mesma do banco e autoriza a troca de senha */
        if (!passwordEncoder.matches(dto.getSenhaAtual(), usuario.getSenha())) {
            throw new MsgApiException("Senha atual inválida");
        }

        usuario.setSenha(passwordEncoder.encode(dto.getNovaSenha()));
        usuarioRepository.saveAndFlush(usuario);
    }

    public Optional<Usuario> buscarPorId(Long id, Long empresaId) {
        return usuarioRepository.buscarPorId(id, empresaId);
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
