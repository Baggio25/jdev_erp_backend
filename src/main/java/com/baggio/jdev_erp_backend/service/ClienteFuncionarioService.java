package com.baggio.jdev_erp_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baggio.jdev_erp_backend.model.ClienteFuncionario;
import com.baggio.jdev_erp_backend.repository.ClienteFuncionarioRepository;

@Service
public class ClienteFuncionarioService {

    @Autowired
    private ClienteFuncionarioRepository clienteFuncionarioRepository;

    public List<ClienteFuncionario> findAll(Long idEmpresa) {
        return clienteFuncionarioRepository.findAll(idEmpresa);
    }

    public ClienteFuncionario findByPessoa(Long idPessoa, Long idEmpresa) {
        return clienteFuncionarioRepository.findByPessoa(idPessoa, idEmpresa);
    }

    public List<ClienteFuncionario> buscaPorNome(String nome, Long idEmpresa) {
        return clienteFuncionarioRepository.buscaPorNome(nome, idEmpresa);
    }

    public boolean existePorNome(String nome, Long idEmpresa) {
        return clienteFuncionarioRepository.existePorNome(nome, idEmpresa);
    }

    public boolean existePorNomeDiferenteId(Long id, String nome, Long idEmpresa) {
        return clienteFuncionarioRepository.existePorNomeDiferenteId(id, nome, idEmpresa);
    }

    public void deleteById(Long id, Long idEmpresa) {
        clienteFuncionarioRepository.deleteById(id, idEmpresa);
    }
}
