package com.baggio.jdev_erp_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baggio.jdev_erp_backend.model.Plano;
import com.baggio.jdev_erp_backend.repository.PlanoRepository;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;

    public List<Plano> findAll() {
        return planoRepository.findAll();
    }

    public List<Plano> buscaPorNome(String nome) {
        return planoRepository.buscaPorNome(nome);
    }

    public boolean existePorNome(String nome) {
        return planoRepository.existePorNome(nome);
    }

    public boolean existePorNomeDiferenteId(Long id, String nome) {
        return planoRepository.existePorNomeDiferenteId(id, nome);
    }

    public void deleteById(Long id) {
        planoRepository.deleteById(id);
    }
}
