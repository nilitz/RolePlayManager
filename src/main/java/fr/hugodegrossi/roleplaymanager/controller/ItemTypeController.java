package fr.hugodegrossi.roleplaymanager.controller;

import fr.hugodegrossi.roleplaymanager.entity.itemType.ItemType;
import fr.hugodegrossi.roleplaymanager.entity.roleplay.Roleplay;
import fr.hugodegrossi.roleplaymanager.entity.user.User;
import fr.hugodegrossi.roleplaymanager.repository.ItemTypeRepository;
import fr.hugodegrossi.roleplaymanager.repository.RoleplayRepository;
import fr.hugodegrossi.roleplaymanager.repository.UserRepository;
import fr.hugodegrossi.roleplaymanager.request.generic.StringRequest;
import fr.hugodegrossi.roleplaymanager.util.JwtUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/roleplay")
public class ItemTypeController {

    private final JwtUtil jwtUtil;
    private final ItemTypeRepository itemTypeRepository;
    private final RoleplayRepository roleplayRepository;
    private final UserRepository userRepository;

    public ItemTypeController(JwtUtil jwtUtil, ItemTypeRepository itemTypeRepository, RoleplayRepository roleplayRepository, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.itemTypeRepository = itemTypeRepository;
        this.roleplayRepository = roleplayRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}/item-type")
    public List<ItemType> getItemType(@PathVariable String id) {
        return this.itemTypeRepository.findByRoleplayId(Integer.parseInt(id));
    }

    @PostMapping("/{id}/item-type")
    public ItemType postItemType(@RequestBody StringRequest itemTypeRequest, @PathVariable String id) {
        Roleplay roleplay = roleplayRepository.findById(Integer.parseInt(id));
        ItemType itemType = new ItemType(itemTypeRequest.getPostedString(), roleplay);
        itemTypeRepository.save(itemType);
        return itemType;
    }

}
