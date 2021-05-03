package fr.hugodegrossi.roleplaymanager.controller;

import fr.hugodegrossi.roleplaymanager.entity.roleplay.Roleplay;
import fr.hugodegrossi.roleplaymanager.entity.roleplay.RoleplayDescRequest;
import fr.hugodegrossi.roleplaymanager.entity.roleplay.RoleplayPostRequest;
import fr.hugodegrossi.roleplaymanager.entity.user.User;
import fr.hugodegrossi.roleplaymanager.repository.RoleplayRepository;
import fr.hugodegrossi.roleplaymanager.repository.UserRepository;
import fr.hugodegrossi.roleplaymanager.util.JwtUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class RoleplayController {

    private final JwtUtil jwtUtil;
    private final RoleplayRepository roleplayRepository;
    private final UserRepository userRepository;

    public RoleplayController(JwtUtil jwtUtil, RoleplayRepository roleplayRepository, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.roleplayRepository = roleplayRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/roleplay/gm")
    public List<Roleplay> getUserGMRoleplay() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.roleplayRepository.findByGameMasters_Username(userDetails.getUsername());
    }


    @GetMapping("/roleplay/player")
    public List<Roleplay> getUserPlayerRoleplay() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.roleplayRepository.findByPlayers_Username(userDetails.getUsername());
    }

    @PostMapping("/roleplay")
    public void postRoleplay(@RequestBody RoleplayPostRequest roleplayPostRequest) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());

        Roleplay roleplay = new Roleplay(roleplayPostRequest.getName());

        roleplayRepository.save(roleplay);
        roleplay.addToGameMasters(user);
        roleplayRepository.save(roleplay);
    }


    @GetMapping("/roleplay/{id}")
    public Roleplay getRoleplay(@PathVariable String id) {
        return roleplayRepository.findById(Integer.parseInt(id));
    }

    @PostMapping("/roleplay/{id}/desc")
    public void postRoleplayDesc(@RequestBody RoleplayDescRequest roleplayDescRequest, @PathVariable String id) {
        Roleplay roleplay = roleplayRepository.findById(Integer.parseInt(id));
        roleplay.setDescription(roleplayDescRequest.getDescription());
        roleplayRepository.save(roleplay);
    }
}
