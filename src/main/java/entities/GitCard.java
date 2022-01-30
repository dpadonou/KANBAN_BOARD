package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("git_card")
public class GitCard extends Card implements Serializable {

    public GitCard(String libelle, LocalDateTime createdDate, LocalDateTime deadLine, String lieu, String url, String note, String urlIssue, String gitHash) {
        super(libelle, createdDate, deadLine, lieu, url, note);
        this.urlIssue = urlIssue;
        this.gitHash = gitHash;
    }

    @Column(name = "url_issue")
    @Basic(fetch = FetchType.LAZY)
    private String urlIssue;

    @Column(name = "git_hash")
    @Basic(fetch = FetchType.LAZY)
    private String gitHash;
}
