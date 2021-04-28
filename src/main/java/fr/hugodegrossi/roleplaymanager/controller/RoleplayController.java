package fr.hugodegrossi.roleplaymanager.controller;

import fr.hugodegrossi.roleplaymanager.entity.authentication.RegisterRequest;
import fr.hugodegrossi.roleplaymanager.entity.roleplay.Roleplay;
import fr.hugodegrossi.roleplaymanager.entity.roleplay.RoleplayRequest;
import fr.hugodegrossi.roleplaymanager.entity.user.User;
import fr.hugodegrossi.roleplaymanager.repository.RoleplayRepository;
import fr.hugodegrossi.roleplaymanager.repository.UserRepository;
import fr.hugodegrossi.roleplaymanager.util.JwtUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        return this.roleplayRepository.findByGameMasterUsers_Username(userDetails.getUsername());
    }
/*
    @GetMapping("/roleplay/player")
    public List<Roleplay> getUserPlayerRoleplay() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.roleplayRepository.findByPlayerUsers_Username(userDetails.getUsername());
    }*/

    @PostMapping("/roleplay")
    public void postRoleplay(@RequestBody RoleplayRequest roleplayRequest) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());

        Roleplay roleplay = new Roleplay(roleplayRequest.getName());

        roleplayRepository.save(roleplay);
        roleplay.addToGM(user);
        roleplayRepository.save(roleplay);
    }
}
