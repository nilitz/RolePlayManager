package fr.hugodegrossi.roleplaymanager.controller;

import fr.hugodegrossi.roleplaymanager.entity.authentication.RegisterRequest;
import fr.hugodegrossi.roleplaymanager.entity.roleplay.Roleplay;
import fr.hugodegrossi.roleplaymanager.entity.roleplay.RoleplayRequest;
import fr.hugodegrossi.roleplaymanager.repository.RoleplayRepository;
import fr.hugodegrossi.roleplaymanager.util.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class RoleplayController {

    private final JwtUtil jwtUtil;
    private final RoleplayRepository roleplayRepository;

    public RoleplayController(JwtUtil jwtUtil, RoleplayRepository roleplayRepository) {
        this.jwtUtil = jwtUtil;
        this.roleplayRepository = roleplayRepository;
    }

    @PostMapping("/roleplay")
    public void postRoleplay(@RequestBody RoleplayRequest roleplayRequest) {

        Roleplay roleplay = new Roleplay(roleplayRequest.getName());

        roleplayRepository.save(roleplay);
    }
}
