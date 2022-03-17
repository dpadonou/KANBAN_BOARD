package dto.unlinked;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnLinkedGitCardDto extends UnLinkedCardDto {
    private String urlIssue;
    private String gitHash;

    public UnLinkedGitCardDto(long id, String libelle, String createdDate, String deadLine, long allocatedTime, String lieu, String url, String note, String urlIssue, String gitHash) {
        super(id, libelle, createdDate, deadLine, allocatedTime, lieu, url, note);
        this.urlIssue = urlIssue;
        this.gitHash = gitHash;
    }
}
