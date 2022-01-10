package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "card_type")
@DiscriminatorValue("classic_card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String libelle;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "dead_line", nullable = false)
    private LocalDateTime deadLine;

    @Column(name = "allocated_time")
    private long allocatedTime;

    private String lieu;

    @Basic(fetch = FetchType.LAZY)
    private String url;

    @Basic(fetch = FetchType.LAZY)
    private String note;

    @ManyToMany
    @JoinColumn(name = "in_charge_id")
    private List<User> inCharge;

    @ManyToOne
    private User creator;

    @ManyToOne
    private Section section;

    public Card(String libelle, LocalDateTime createdDate, LocalDateTime deadLine, String lieu, String url, String note) {
        inCharge = new ArrayList<>();
        this.libelle = libelle;
        this.createdDate = createdDate;
        this.deadLine = deadLine;
        this.allocatedTime = createdDate.until(deadLine, ChronoUnit.HOURS);
        this.lieu = lieu;
        this.url = url;
        this.note = note;
    }
}
