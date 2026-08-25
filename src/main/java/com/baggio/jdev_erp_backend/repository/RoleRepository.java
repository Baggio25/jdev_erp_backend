package com.baggio.jdev_erp_backend.repository;

import com.baggio.jdev_erp_backend.anotations.IgnoreEmpresaId;
import com.baggio.jdev_erp_backend.model.Role;
import org.springframework.stereotype.Repository;

@IgnoreEmpresaId
@Repository
public interface RoleRepository extends MyBaseRepository<Role, Long> {
}
