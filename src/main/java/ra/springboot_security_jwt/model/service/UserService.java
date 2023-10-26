package ra.springboot_security_jwt.model.service;

import ra.springboot_security_jwt.model.entity.Users;

public interface UserService {
    Users findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    Users saveOrUpdate(Users user);
}
