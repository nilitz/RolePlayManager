package fr.hugodegrossi.roleplaymanager.entity.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.hugodegrossi.roleplaymanager.entity.itemType.ItemType;
import fr.hugodegrossi.roleplaymanager.entity.roleplay.Roleplay;
import fr.hugodegrossi.roleplaymanager.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String name;
    @Column(columnDefinition = "TEXT", nullable = true)
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "items_item_types",
            inverseJoinColumns = @JoinColumn(name = "item_type_id"),
            joinColumns = @JoinColumn(name = "item_id")
    )
    private List<ItemType> itemTypes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "roleplay_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Roleplay roleplay;

    public void addToItemTypes(ItemType itemType){
        this.itemTypes.add(itemType);
    }

    public Item(String name, String description, Roleplay roleplay, List<ItemType> itemTypes) {
        this.roleplay = roleplay;
        this.name = name;
        this.description = description;
        this.itemTypes = itemTypes;
    }

}
