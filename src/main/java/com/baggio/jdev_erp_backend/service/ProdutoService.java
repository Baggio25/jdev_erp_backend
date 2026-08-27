package com.baggio.jdev_erp_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baggio.jdev_erp_backend.model.Produto;
import com.baggio.jdev_erp_backend.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll(Long idEmpresa) {
        return produtoRepository.findAll(idEmpresa);
    }

    public List<Produto> buscaPorNome(String nome, Long idEmpresa) {
        return produtoRepository.buscaPorNome(nome, idEmpresa);
    }

    public boolean existePorNome(String nome, Long idEmpresa) {
        return produtoRepository.existePorNome(nome, idEmpresa);
    }

    public boolean existePorNomeDiferenteId(Long id, String nome, Long idEmpresa) {
        return produtoRepository.existePorNomeDiferenteId(id, nome, idEmpresa);
    }

    public void deleteById(Long id, Long idEmpresa) {
        produtoRepository.deleteById(id, idEmpresa);
    }
}
