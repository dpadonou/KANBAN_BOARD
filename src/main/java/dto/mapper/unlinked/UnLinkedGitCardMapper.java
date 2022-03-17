package dto.mapper.unlinked;

import dto.unlinked.UnLinkedCardDto;
import dto.unlinked.UnLinkedGitCardDto;
import entities.GitCard;

public class UnLinkedGitCardMapper extends UnLinkedCardMapper {

    public UnLinkedGitCardDto toUnLinkedDto(GitCard card) {
        UnLinkedCardDto c = super.toUnLinkedDto(card);

        String urlIssue = card.getUrlIssue();
        String gitHash = card.getGitHash();

        return new UnLinkedGitCardDto(c.getId(), c.getLibelle(), c.getCreatedDate(), c.getDeadLine(), c.getAllocatedTime(), c.getLieu(), c.getUrl(), c.getNote(), urlIssue, gitHash);
    }
}
