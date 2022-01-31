package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "utilisateur")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "last_name", length = 55)
    private String lastName;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @ManyToMany(mappedBy = "inCharge")
    private List<Card> taches;

    @OneToMany(mappedBy = "creator")
    private List<Card> owned;

    public User(String lastName, String firstName) {
        owned = new ArrayList<>();
        taches = new ArrayList<>();
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public void addOwned(Card card) {
        owned.add(card);
        card.setCreator(this);
    }

    public void addTache(Card card) {
        taches.add(card);
        card.getInCharge().add(this);
    }

    public String concatName(){
        return firstName + " " + lastName;
    }
}
