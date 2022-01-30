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
public class Board implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @ManyToMany(mappedBy = "boards")
    private List<Section> sections;

    public Board(String title) {
        sections = new ArrayList<>();
        this.title = title;
    }

    public void addSection(Section section) {
        sections.add(section);
        section.getBoards().add(this);
    }
}