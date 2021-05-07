package fr.hugodegrossi.roleplaymanager.repository;

import fr.hugodegrossi.roleplaymanager.entity.roleplay.Roleplay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleplayRepository extends JpaRepository<Roleplay, Integer> {
    Roleplay findByName(String name);
    Roleplay findById(int id);
    List<Roleplay> findByGameMasters_Username(String username);
}
