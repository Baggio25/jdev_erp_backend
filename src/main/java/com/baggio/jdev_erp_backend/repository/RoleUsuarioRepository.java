package com.baggio.jdev_erp_backend.repository;

import com.baggio.jdev_erp_backend.anotations.IgnoreEmpresaId;
import com.baggio.jdev_erp_backend.model.RoleUsuario;
import org.springframework.stereotype.Repository;

@IgnoreEmpresaId
@Repository
public interface RoleUsuarioRepository extends MyBaseRepository<RoleUsuario, Long> {
}
