package fr.hugodegrossi.roleplaymanager.entity.roleplay;

import fr.hugodegrossi.roleplaymanager.entity.user.User;
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
@Table(name = "Roleplay")
public class Roleplay {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "gm_users",
            inverseJoinColumns = @JoinColumn(name = "user_info_id"),
            joinColumns = @JoinColumn(name = "roleplay_id")
    )
    private List<User> gameMasterUsers = new ArrayList<>();

    public void addToGM(User user){
        this.gameMasterUsers.add(user);
    }


    public Roleplay(String name) {
        this.name = name;
    }
}
