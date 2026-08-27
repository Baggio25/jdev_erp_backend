package com.baggio.jdev_erp_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baggio.jdev_erp_backend.model.Empresa;
import com.baggio.jdev_erp_backend.repository.EmpresaRepository;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    public Empresa buscarPorId(Long id) {
        return empresaRepository.buscarPorId(id);
    }

    public List<Empresa> buscaPorNome(String nome) {
        return empresaRepository.buscaPorNome(nome);
    }

    public boolean existePorNome(String nome) {
        return empresaRepository.existePorNome(nome);
    }

    public boolean existePorNomeDiferenteId(Long id, String nome) {
        return empresaRepository.existePorNomeDiferenteId(id, nome);
    }

    public void deleteById(Long id) {
        empresaRepository.deleteById(id);
    }
}
