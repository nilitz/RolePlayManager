package fr.hugodegrossi.roleplaymanager;

import fr.hugodegrossi.roleplaymanager.entity.User;
import fr.hugodegrossi.roleplaymanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@CrossOrigin("*")
public class RolePlayManagerApplication {

    private final UserRepository userRepository;

    public RolePlayManagerApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(101, "hugo", "password", "contact@hugo.degrossi.fr")
        ).collect(Collectors.toList());
        userRepository.saveAll(users);
    }



    public static void main(String[] args) {
        SpringApplication.run(RolePlayManagerApplication.class, args);
    }

}
