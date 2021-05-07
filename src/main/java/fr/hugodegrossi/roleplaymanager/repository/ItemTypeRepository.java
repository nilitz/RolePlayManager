package fr.hugodegrossi.roleplaymanager.repository;

import fr.hugodegrossi.roleplaymanager.entity.itemType.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ItemTypeRepository  extends JpaRepository<ItemType, Integer> {
    ArrayList<ItemType> findByRoleplayId(Integer id);
}
