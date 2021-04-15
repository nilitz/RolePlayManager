package fr.hugodegrossi.roleplaymanager.controller;

import fr.hugodegrossi.roleplaymanager.entity.AuthRequest;
import fr.hugodegrossi.roleplaymanager.entity.RegisterRequest;
import fr.hugodegrossi.roleplaymanager.entity.User;
import fr.hugodegrossi.roleplaymanager.repository.UserRepository;
import fr.hugodegrossi.roleplaymanager.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthenticationController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "401 : Invalid username / password");

        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) throws Exception {
        if (userRepository.findByUsernameAndEmail(registerRequest.getUsername(), registerRequest.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "409 : Username or email already exist");
        }

        String hashedPassword = passwordEncoder.encode(registerRequest.getPassword());
        User user = new User(registerRequest.getUsername(), hashedPassword, registerRequest.getEmail());

        userRepository.save(user);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerRequest.getUsername(), registerRequest.getPassword())
        );

        return jwtUtil.generateToken(registerRequest.getUsername());
    }
}
