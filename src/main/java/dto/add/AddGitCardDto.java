package dto.add;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddGitCardDto extends AddCardDto {
    private String urlIssue;
    private String gitHash;

    public AddGitCardDto(String libelle, String createdDate, String deadLine, long allocatedTime, String lieu, String url, String note, String urlIssue, String gitHash) {
        super(libelle, createdDate, deadLine, allocatedTime, lieu, url, note);
        this.urlIssue = urlIssue;
        this.gitHash = gitHash;
    }
}
