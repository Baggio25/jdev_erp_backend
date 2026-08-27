package com.baggio.jdev_erp_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baggio.jdev_erp_backend.model.ItemPedido;
import com.baggio.jdev_erp_backend.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> findAll(Long idPedido, Long idEmpresa) {
        return itemPedidoRepository.findAll(idPedido, idEmpresa);
    }

    public List<ItemPedido> buscaPorNome(String nome, Long idPedido, Long idEmpresa) {
        return itemPedidoRepository.buscaPorNome(nome, idPedido, idEmpresa);
    }

    public boolean existePorNome(String nome, Long idPedido, Long idEmpresa) {
        return itemPedidoRepository.existePorNome(nome, idPedido, idEmpresa);
    }

    public boolean existePorNomeDiferenteId(Long id, String nome, Long idPedido, Long idEmpresa) {
        return itemPedidoRepository.existePorNomeDiferenteId(id, nome, idPedido, idEmpresa);
    }

    public void deleteById(Long id, Long idPedido, Long idEmpresa) {
        itemPedidoRepository.deleteById(id, idPedido, idEmpresa);
    }

    public List<ItemPedido> findAllByPedido(Long idPedido, Long idEmpresa) {
        return itemPedidoRepository.findAllByPedido(idPedido, idEmpresa);
    }

    public List<ItemPedido> buscaPorNomePorPedido(String nome, Long idPedido, Long idEmpresa) {
        return itemPedidoRepository.buscaPorNomePorPedido(nome, idPedido, idEmpresa);
    }

    public boolean existePorNomePorPedido(String nome, Long idPedido, Long idEmpresa) {
        return itemPedidoRepository.existePorNomePorPedido(nome, idPedido, idEmpresa);
    }

    public boolean existePorNomeDiferenteIdPorPedido(Long id, String nome, Long idPedido, Long idEmpresa) {
        return itemPedidoRepository.existePorNomeDiferenteIdPorPedido(id, nome, idPedido, idEmpresa);
    }

    public void deleteByIdAndPedido(Long id, Long idPedido, Long idEmpresa) {
        itemPedidoRepository.deleteByIdAndPedido(id, idPedido, idEmpresa);
    }
}
