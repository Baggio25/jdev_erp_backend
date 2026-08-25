package com.baggio.jdev_erp_backend.repository;

import com.baggio.jdev_erp_backend.anotations.IgnoreEmpresaId;
import com.baggio.jdev_erp_backend.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MyBaseRepository<Usuario, Long> {

    @IgnoreEmpresaId(ignorar = true, motivo = "Usado para login então a empresa é estabelecida depois do login do usuário")
    @Query("select u from usuario u where u.login = :login")
    Usuario buscaPorLogin(@Param("login")  String login);

}
