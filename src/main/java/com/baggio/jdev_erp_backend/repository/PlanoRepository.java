package com.baggio.jdev_erp_backend.repository;


import com.baggio.jdev_erp_backend.anotations.IgnoreEmpresaId;
import com.baggio.jdev_erp_backend.model.Plano;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@IgnoreEmpresaId
@Repository
public interface PlanoRepository extends MyBaseRepository<Plano, Long> {

    /*
     * Busca todos os planos cadastrados
     */
    @Query("select p from Plano p")
    List<Plano> findAll();


    /*Busca os planos por partes ou nome completo passado por parametro*/
    @Query("select p from Plano p where upper(trim(p.nome)) "
            + " like upper(concat('%', trim(:nome) ,'%'))")
    List<Plano> buscaPorNome(@Param("nome") String nome);


    /*Retorna true se já existir plano com o mesmo nome, no caso não podemso deixar salvar para não ficar repetido no banco de dados*/
    @Query("select count(p.id) > 0 from Plano p where upper(trim(p.nome)) "
            + " = upper(trim(:nome))")
    boolean existePorNome(@Param("nome") String nome);

    /*Verifica se existe outro plano no banco de dados com o mesmo nome mas ID diferente da que está tentando atualizar*/
    @Query("select count(p.id) > 0 from Plano p where upper(trim(p.nome)) "
            + " = upper(trim(:nome)) and p.id <> :id")
    boolean existePorNomeDiferenteId(@Param("id") Long id, @Param("nome") String nome);

    /*Delete de um plano*/
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("delete from Plano p where p.id = :id")
    void deleteById(@Param("id") Long id);

}
