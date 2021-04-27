package fr.hugodegrossi.roleplaymanager.entity.roleplay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public Roleplay(String name) {
        this.name = name;
    }
}
