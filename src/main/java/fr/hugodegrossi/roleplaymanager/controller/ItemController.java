package fr.hugodegrossi.roleplaymanager.controller;

import fr.hugodegrossi.roleplaymanager.entity.item.Item;
import fr.hugodegrossi.roleplaymanager.entity.itemType.ItemType;
import fr.hugodegrossi.roleplaymanager.entity.roleplay.Roleplay;
import fr.hugodegrossi.roleplaymanager.repository.ItemRepository;
import fr.hugodegrossi.roleplaymanager.repository.ItemTypeRepository;
import fr.hugodegrossi.roleplaymanager.repository.RoleplayRepository;
import fr.hugodegrossi.roleplaymanager.repository.UserRepository;
import fr.hugodegrossi.roleplaymanager.request.generic.TwoStringAndListRequest;
import fr.hugodegrossi.roleplaymanager.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/roleplay")
public class ItemController {

    private final JwtUtil jwtUtil;
    private final ItemRepository itemRepository;
    private final ItemTypeRepository itemTypeRepository;
    private final RoleplayRepository roleplayRepository;
    private final UserRepository userRepository;

    public ItemController(JwtUtil jwtUtil, ItemRepository itemRepository, ItemTypeRepository itemTypeRepository, RoleplayRepository roleplayRepository, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.itemRepository = itemRepository;
        this.itemTypeRepository = itemTypeRepository;
        this.roleplayRepository = roleplayRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}/item")
    public List<Item> getItem(@PathVariable String id) {
        return this.itemRepository.findByRoleplayId(Integer.parseInt(id));
    }

    @PostMapping("/{id}/item")
    public Item postItem(@RequestBody TwoStringAndListRequest itemRequest, @PathVariable String id) {
        Roleplay roleplay = roleplayRepository.findById(Integer.parseInt(id));
        List<ItemType> itemTypes =  new ArrayList<>();
        for(int i = 0; i < itemRequest.getPostedList().size(); i++) {
            itemTypeRepository.findById(itemRequest.getPostedList().get(i)).ifPresent(itemTypes::add);
        }
        Item item = new Item(itemRequest.getPostedStringOne(), itemRequest.getPostedStringTwo(), roleplay, itemTypes);
        itemRepository.save(item);
        return item;
    }

}
