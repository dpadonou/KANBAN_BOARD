package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    private List<Board> boards;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Card> fiches;

    public Section(String name) {
        fiches = new ArrayList<>();
        boards = new ArrayList<>();
        this.name = name;
    }

    public void addCarte(Card card) {
        fiches.add(card);
        card.setSection(this);
    }
}
