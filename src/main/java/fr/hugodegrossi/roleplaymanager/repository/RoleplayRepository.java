package fr.hugodegrossi.roleplaymanager.repository;

import fr.hugodegrossi.roleplaymanager.entity.roleplay.Roleplay;
import fr.hugodegrossi.roleplaymanager.entity.roleplay.RoleplayRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleplayRepository extends JpaRepository<Roleplay, Integer> {
    Roleplay findByName(String name);
    List<Roleplay> findByGameMasterUsers_Username(String username);
}
