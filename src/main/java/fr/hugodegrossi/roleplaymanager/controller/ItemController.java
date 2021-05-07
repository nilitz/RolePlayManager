package fr.hugodegrossi.roleplaymanager.controller;

import fr.hugodegrossi.roleplaymanager.entity.item.Item;
import fr.hugodegrossi.roleplaymanager.entity.itemType.ItemType;
import fr.hugodegrossi.roleplaymanager.entity.roleplay.Roleplay;
import fr.hugodegrossi.roleplaymanager.repository.ItemRepository;
import fr.hugodegrossi.roleplaymanager.repository.ItemTypeRepository;
import fr.hugodegrossi.roleplaymanager.repository.RoleplayRepository;
import fr.hugodegrossi.roleplaymanager.repository.UserRepository;
import fr.hugodegrossi.roleplaymanager.request.generic.StringRequest;
import fr.hugodegrossi.roleplaymanager.request.generic.TwoStringRequest;
import fr.hugodegrossi.roleplaymanager.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/roleplay")
public class ItemController {

    private final JwtUtil jwtUtil;
    private final ItemRepository itemRepository;
    private final RoleplayRepository roleplayRepository;
    private final UserRepository userRepository;

    public ItemController(JwtUtil jwtUtil, ItemRepository itemRepository, RoleplayRepository roleplayRepository, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.itemRepository = itemRepository;
        this.roleplayRepository = roleplayRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}/item")
    public List<Item> getItem(@PathVariable String id) {
        return this.itemRepository.findByRoleplayId(Integer.parseInt(id));
    }

    @PostMapping("/{id}/item")
    public void postItem(@RequestBody TwoStringRequest itemRequest, @PathVariable String id) {
        Roleplay roleplay = roleplayRepository.findById(Integer.parseInt(id));
        Item item = new Item(itemRequest.getPostedStringOne(), itemRequest.getPostedStringTwo(), roleplay);
        itemRepository.save(item);
    }

}
