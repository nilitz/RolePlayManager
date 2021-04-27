package fr.hugodegrossi.roleplaymanager.repository;

import fr.hugodegrossi.roleplaymanager.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByUsernameAndEmail(String username, String email);
    User findByEmail(String email);
}
