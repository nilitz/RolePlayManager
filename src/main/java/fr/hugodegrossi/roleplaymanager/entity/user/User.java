package fr.hugodegrossi.roleplaymanager.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.hugodegrossi.roleplaymanager.entity.roleplay.Roleplay;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UserInfo")
public class User {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;

    @JsonIgnore
    @ManyToMany(mappedBy = "gameMasters")
    private List<Roleplay> gameMasterRoleplays =  new ArrayList<>();




    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

}
