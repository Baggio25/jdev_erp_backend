package com.baggio.jdev_erp_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baggio.jdev_erp_backend.model.RoleUsuario;
import com.baggio.jdev_erp_backend.repository.RoleUsuarioRepository;

@Service
public class RoleUsuarioService {

    @Autowired
    private RoleUsuarioRepository roleUsuarioRepository;

    public List<RoleUsuario> findAllByUsuario(Long idUsuario, Long idEmpresa) {
        return roleUsuarioRepository.findAllByUsuario(idUsuario, idEmpresa);
    }

    public List<RoleUsuario> findAllByRoleAndEmpresa(Long idRole, Long idEmpresa) {
        return roleUsuarioRepository.findAllByRoleAndEmpresa(idRole, idEmpresa);
    }

    public boolean existePorUsuarioERole(Long idUsuario, Long idRole, Long idEmpresa) {
        return roleUsuarioRepository.existePorUsuarioERole(idUsuario, idRole, idEmpresa);
    }

    public void deleteById(Long id) {
        roleUsuarioRepository.deleteById(id);
    }

    public void deleteByUsuarioAndRole(Long idUsuario, Long idRole, Long idEmpresa) {
        roleUsuarioRepository.deleteByUsuarioAndRole(idUsuario, idRole, idEmpresa);
    }
}
