package com.baggio.jdev_erp_backend.repository;

import com.baggio.jdev_erp_backend.anotations.IgnoreEmpresaId;
import com.baggio.jdev_erp_backend.model.Empresa;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@IgnoreEmpresaId
@Repository
public interface EmpresaRepository extends MyBaseRepository<Empresa, Long> {

    @Query("select c from Empresa c ")
    List<Empresa> findAll();

    @Query("select c from Empresa c where c.id = :id")
    Empresa buscarPorId(@Param("id") Long id);

    /*Busca as empresas por partes ou nome (pessoa.nome) completo passado por parametro*/
    @Query("select e from Empresa e where upper(trim(e.pessoa.nome)) "
            + " like upper(concat('%', trim(:nome) ,'%'))" )
    List<Empresa> buscaPorNome(@Param("nome") String nome);

    /*Retorna true se já existir empresa com o mesmo nome (pessoa.nome), para evitar duplicidade*/
    @Query("select count(e.id) > 0 from Empresa e "
            + " where upper(trim(e.pessoa.nome)) "
            + " = upper(trim(:nome))")
    boolean existePorNome(@Param("nome") String nome);

    /*Verifica se existe outra empresa no banco de dados com o mesmo nome (pessoa.nome) mas ID diferentes da que está tentando atualizar*/
    @Query("select count(e.id) > 0 from Empresa e "
            + " where upper(trim(e.pessoa.nome)) "
            + " = upper(trim(:nome)) and e.id <> :id")
    boolean existePorNomeDiferenteId(@Param("id") Long id, @Param("nome") String nome);

    /*Delete de uma empresa (sem filtro por empresa, pois esta entidade representa a própria empresa)*/
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("delete from Empresa e where e.id = :id")
    void deleteById(@Param("id") Long id);


}
