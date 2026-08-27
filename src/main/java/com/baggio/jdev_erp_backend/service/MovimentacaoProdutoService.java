package com.baggio.jdev_erp_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baggio.jdev_erp_backend.model.MovimentacaoProduto;
import com.baggio.jdev_erp_backend.repository.MovimentacaoProdutoRepository;

@Service
public class MovimentacaoProdutoService {

    @Autowired
    private MovimentacaoProdutoRepository movimentacaoProdutoRepository;

    public List<MovimentacaoProduto> findAll(Long idEmpresa) {
        return movimentacaoProdutoRepository.findAll(idEmpresa);
    }

    public List<MovimentacaoProduto> buscaPorTipo(String tipoMovimentacaoProduto, Long idEmpresa) {
        return movimentacaoProdutoRepository.buscaPorTipo(tipoMovimentacaoProduto, idEmpresa);
    }

    public boolean existePorTipo(String tipoMovimentacaoProduto, Long idEmpresa) {
        return movimentacaoProdutoRepository.existePorTipo(tipoMovimentacaoProduto, idEmpresa);
    }

    public boolean existePorTipoDiferenteId(Long id, String tipoMovimentacaoProduto, Long idEmpresa) {
        return movimentacaoProdutoRepository.existePorTipoDiferenteId(id, tipoMovimentacaoProduto, idEmpresa);
    }

    public void deleteById(Long id, Long idEmpresa) {
        movimentacaoProdutoRepository.deleteById(id, idEmpresa);
    }
}
