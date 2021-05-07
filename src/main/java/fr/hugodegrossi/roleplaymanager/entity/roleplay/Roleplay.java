package fr.hugodegrossi.roleplaymanager.entity.roleplay;

import fr.hugodegrossi.roleplaymanager.entity.item.Item;
import fr.hugodegrossi.roleplaymanager.entity.itemType.ItemType;
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
    @Column(columnDefinition = "TEXT", nullable = true)
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "gm_users",
            inverseJoinColumns = @JoinColumn(name = "user_info_id"),
            joinColumns = @JoinColumn(name = "roleplay_id")
    )
    private List<User> gameMasters = new ArrayList<>();
    public void addToGameMasters(User user){
        this.gameMasters.add(user);
    }

    public Roleplay(String name) {
        this.name = name;
    }
}
