package dto.list;

import dto.unlinked.UnLinkedSectionDto;
import dto.unlinked.UnLinkedUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GitCardDto extends CardDto {
    private String urlIssue;
    private String gitHash;

    public GitCardDto(long id, String libelle, String createdDate, String deadLine, long allocatedTime, String lieu, String url, String note, List<UnLinkedUserDto> personnesEnCharge, UnLinkedUserDto creator, UnLinkedSectionDto section, String urlIssue, String gitHash) {
        super(id, libelle, createdDate, deadLine, allocatedTime, lieu, url, note, personnesEnCharge, creator, section);
        this.urlIssue = urlIssue;
        this.gitHash = gitHash;
    }
}
