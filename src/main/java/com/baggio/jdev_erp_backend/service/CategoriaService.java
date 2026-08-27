package com.baggio.jdev_erp_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baggio.jdev_erp_backend.model.Categoria;
import com.baggio.jdev_erp_backend.repository.CategoriaRepository;


@Service
public class CategoriaService {
  
  @Autowired /* Injeção de depência */
	private CategoriaRepository categoriaRepository;

	/* Os métodos do service serão chamador pelo Controller */
	public List<Categoria> findAll(Long idEmpresa) {
		return categoriaRepository.findAll(idEmpresa);
	}

	List<Categoria> buscaPorNome(String nome, Long idEmpresa) {
		return categoriaRepository.buscaPorNome(nome, idEmpresa);
	}

	boolean existePorNome(String nome, Long idEmpresa) {

		return categoriaRepository.existePorNome(nome, idEmpresa);
	}

	boolean existePorNomeDiferenteId(Long id, String nome, Long idEmpresa) {
		return categoriaRepository.existePorNomeDiferenteId(id, nome, idEmpresa);
	}

	void deleteById(Long id, Long idEmpresa) {
		categoriaRepository.deleteById(id, idEmpresa);
	}

}
