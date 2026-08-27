package com.baggio.jdev_erp_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baggio.jdev_erp_backend.model.Role;
import com.baggio.jdev_erp_backend.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public List<Role> buscaPorAcesso(String acesso) {
        return roleRepository.buscaPorAcesso(acesso);
    }

    public boolean existePorAcesso(String acesso) {
        return roleRepository.existePorAcesso(acesso);
    }

    public boolean existePorAcessoDiferenteId(Long id, String acesso) {
        return roleRepository.existePorAcessoDiferenteId(id, acesso);
    }

    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
