package ra.springboot_security_jwt.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.springboot_security_jwt.model.entity.ERole;
import ra.springboot_security_jwt.model.entity.Roles;

import java.util.Optional;

//@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByRoleName(ERole roleName);

}
