package ra.springboot_security_jwt.model.service;

import ra.springboot_security_jwt.model.entity.ERole;
import ra.springboot_security_jwt.model.entity.Roles;

import java.util.Optional;

public interface RoleService {
    Optional<Roles> findByRoleName(ERole roleName);
}
