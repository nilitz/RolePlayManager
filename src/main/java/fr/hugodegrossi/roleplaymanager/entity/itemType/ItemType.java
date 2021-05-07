package fr.hugodegrossi.roleplaymanager.entity.itemType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.hugodegrossi.roleplaymanager.entity.item.Item;
import fr.hugodegrossi.roleplaymanager.entity.roleplay.Roleplay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ItemType")
public class ItemType {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "roleplay_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Roleplay roleplay;

    @JsonIgnore
    @ManyToMany(mappedBy = "itemTypes")
    private List<Item> items =  new ArrayList<>();

    public ItemType(String name, Roleplay roleplay) {
        this.name = name;
        this.roleplay = roleplay;
    }

}
