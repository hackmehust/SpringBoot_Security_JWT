package ra.springboot_security_jwt.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.springboot_security_jwt.model.entity.Users;

//@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUserName(String userName);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);
}
