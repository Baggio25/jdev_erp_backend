package com.baggio.jdev_erp_backend.repository;


import com.baggio.jdev_erp_backend.model.MovimentacaoProduto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoProdutoRepository extends MyBaseRepository<MovimentacaoProduto, Long> {

    /*
     * Busca todas as movimentações de produtos da empresa passada como parametro
     */
    @Query("select m from MovimentacaoProduto m where m.empresa.id = :idEmpresa")
    List<MovimentacaoProduto> findAll(@Param("idEmpresa") Long idEmpresa);


    /*Busca as movimentações por tipo ou produto e da empresa passada por parametro*/
    @Query("select m from MovimentacaoProduto m where m.empresa.id = :idEmpresa "
            + " and upper(trim(m.tipoMovimentacaoProduto)) "
            + " like upper(concat('%', trim(:tipoMovimentacaoProduto) ,'%'))")
    List<MovimentacaoProduto> buscaPorTipo(@Param("tipoMovimentacaoProduto") String tipoMovimentacaoProduto, @Param("idEmpresa") Long idEmpresa);


    /*Retorna true se já existir movimentação do mesmo tipo para a mesma empresa, no caso não podemos deixar salvar para não ficar repetido no banco de dados*/
    @Query("select count(m.id) > 0 from MovimentacaoProduto m where m.empresa.id = :idEmpresa "
            + " and upper(trim(m.tipoMovimentacaoProduto)) "
            + " = upper(trim(:tipoMovimentacaoProduto))")
    boolean existePorTipo(@Param("tipoMovimentacaoProduto") String tipoMovimentacaoProduto, @Param("idEmpresa") Long idEmpresa);

    /*Verifica se existe outra movimentação no banco de dados com o mesmo tipo mas ID diferentes da que está tentando atualizar*/
    @Query("select count(m.id) > 0 from MovimentacaoProduto m where m.empresa.id = :idEmpresa "
            + " and upper(trim(m.tipoMovimentacaoProduto)) "
            + " = upper(trim(:tipoMovimentacaoProduto)) and m.id <> :id")
    boolean existePorTipoDiferenteId(@Param("id") Long id, @Param("tipoMovimentacaoProduto") String tipoMovimentacaoProduto, @Param("idEmpresa") Long idEmpresa);

    /*Delete de uma movimentação de um determinado produto de uma determinada empresa*/
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("delete from MovimentacaoProduto m where m.empresa.id = :idEmpresa and m.id = :id")
    void deleteById(@Param("id") Long id, @Param("idEmpresa") Long idEmpresa);

}
