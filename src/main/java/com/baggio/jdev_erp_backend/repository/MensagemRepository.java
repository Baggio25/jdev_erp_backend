package com.baggio.jdev_erp_backend.repository;


import com.baggio.jdev_erp_backend.model.Mensagem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemRepository extends MyBaseRepository<Mensagem, Long> {

    /*
     * Busca todas as mensagens da empresa passada como parametro
     */
    @Query("select m from Mensagem m where m.empresa.id = :idEmpresa")
    List<Mensagem> findAll(@Param("idEmpresa") Long idEmpresa);


    /*Busca as mensagens por partes ou conteúdo completo passado por parametro e da empresa passada por parametro*/
    @Query("select m from Mensagem m where m.empresa.id = :idEmpresa "
            + " and upper(trim(m.conteudo)) "
            + " like upper(concat('%', trim(:conteudo) ,'%'))")
    List<Mensagem> buscaPorConteudo(@Param("conteudo") String conteudo, @Param("idEmpresa") Long idEmpresa);


    /*Retorna true se já existir mensagem com o mesmo conteúdo para a mesma empresa, no caso não podemos deixar salvar para não ficar repetido no banco de dados*/
    @Query("select count(m.id) > 0 from Mensagem m where m.empresa.id = :idEmpresa "
            + " and upper(trim(m.conteudo)) "
            + " = upper(trim(:conteudo))")
    boolean existePorConteudo(@Param("conteudo") String conteudo, @Param("idEmpresa") Long idEmpresa);

    /*Verifica se existe outra mensagem no banco de dados com o mesmo conteúdo mas ID diferentes da que está tentando atualizar*/
    @Query("select count(m.id) > 0 from Mensagem m where m.empresa.id = :idEmpresa "
            + " and upper(trim(m.conteudo)) "
            + " = upper(trim(:conteudo)) and m.id <> :id")
    boolean existePorConteudoDiferenteId(@Param("id") Long id, @Param("conteudo") String conteudo, @Param("idEmpresa") Long idEmpresa);

    /*Delete de uma mensagem de uma determinada empresa*/
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("delete from Mensagem m where m.empresa.id = :idEmpresa and m.id = :id")
    void deleteById(@Param("id") Long id, @Param("idEmpresa") Long idEmpresa);

}
