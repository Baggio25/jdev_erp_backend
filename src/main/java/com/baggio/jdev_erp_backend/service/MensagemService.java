package com.baggio.jdev_erp_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baggio.jdev_erp_backend.model.Mensagem;
import com.baggio.jdev_erp_backend.repository.MensagemRepository;

@Service
public class MensagemService {

    @Autowired
    private MensagemRepository mensagemRepository;

    public List<Mensagem> findAll(Long idEmpresa) {
        return mensagemRepository.findAll(idEmpresa);
    }

    public List<Mensagem> buscaPorConteudo(String conteudo, Long idEmpresa) {
        return mensagemRepository.buscaPorConteudo(conteudo, idEmpresa);
    }

    public boolean existePorConteudo(String conteudo, Long idEmpresa) {
        return mensagemRepository.existePorConteudo(conteudo, idEmpresa);
    }

    public boolean existePorConteudoDiferenteId(Long id, String conteudo, Long idEmpresa) {
        return mensagemRepository.existePorConteudoDiferenteId(id, conteudo, idEmpresa);
    }

    public void deleteById(Long id, Long idEmpresa) {
        mensagemRepository.deleteById(id, idEmpresa);
    }
}
