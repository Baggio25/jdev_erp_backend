package com.baggio.jdev_erp_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baggio.jdev_erp_backend.model.Pedido;
import com.baggio.jdev_erp_backend.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findAll(Long idEmpresa) {
        return pedidoRepository.findAll(idEmpresa);
    }

    public List<Pedido> buscaPorNumeroPedido(String numeroPedido, Long idEmpresa) {
        return pedidoRepository.buscaPorNumeroPedido(numeroPedido, idEmpresa);
    }

    public boolean existePorNumeroPedido(String numeroPedido, Long idEmpresa) {
        return pedidoRepository.existePorNumeroPedido(numeroPedido, idEmpresa);
    }

    public boolean existePorNumeroPedidoDiferenteId(Long id, String numeroPedido, Long idEmpresa) {
        return pedidoRepository.existePorNumeroPedidoDiferenteId(id, numeroPedido, idEmpresa);
    }

    public void deleteById(Long id, Long idEmpresa) {
        pedidoRepository.deleteById(id, idEmpresa);
    }
}
