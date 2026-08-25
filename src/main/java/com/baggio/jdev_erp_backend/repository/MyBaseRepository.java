package com.baggio.jdev_erp_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface MyBaseRepository<T, ID> extends JpaRepository<T, ID> {

    Page<T> listarPaginado(Long empresaId, Pageable pageable);
    long total(Long empresaId);
    Optional<T> buscarPorId(ID id, Long empresaId);
    List<T> listar(Long empresaId);
    boolean existePorId(ID id, Long empresaId);
    List<T> buscarPorIds(Iterable<ID> ids, Long empresaId);
    void deleteAllById(Iterable<ID> ids, Long empresaId);
    long deleteAll(Long empresaId);
    void deleteById(ID id, Long empresaId);
}
