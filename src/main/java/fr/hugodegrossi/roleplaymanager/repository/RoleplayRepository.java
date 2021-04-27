package fr.hugodegrossi.roleplaymanager.repository;

import fr.hugodegrossi.roleplaymanager.entity.roleplay.Roleplay;
import fr.hugodegrossi.roleplaymanager.entity.roleplay.RoleplayRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleplayRepository extends JpaRepository<Roleplay, Integer> {
    Roleplay findByName(String name);
}
