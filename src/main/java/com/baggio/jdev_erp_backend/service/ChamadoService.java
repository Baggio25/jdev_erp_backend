package com.baggio.jdev_erp_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baggio.jdev_erp_backend.model.Chamado;
import com.baggio.jdev_erp_backend.repository.ChamadoRepository;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public List<Chamado> findAll(Long idEmpresa) {
        return chamadoRepository.findAll(idEmpresa);
    }

    public List<Chamado> buscaPorTitulo(String titulo, Long idEmpresa) {
        return chamadoRepository.buscaPorTitulo(titulo, idEmpresa);
    }

    public boolean existePorTitulo(String titulo, Long idEmpresa) {
        return chamadoRepository.existePorTitulo(titulo, idEmpresa);
    }

    public boolean existePorTituloDiferenteId(Long id, String titulo, Long idEmpresa) {
        return chamadoRepository.existePorTituloDiferenteId(id, titulo, idEmpresa);
    }

    public void deleteById(Long id, Long idEmpresa) {
        chamadoRepository.deleteById(id, idEmpresa);
    }
}
