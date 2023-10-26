package ra.springboot_security_jwt.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.springboot_security_jwt.model.entity.ERole;
import ra.springboot_security_jwt.model.entity.Roles;
import ra.springboot_security_jwt.model.repository.RoleRepository;
import ra.springboot_security_jwt.model.service.RoleService;

import java.util.Optional;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Roles> findByRoleName(ERole roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
