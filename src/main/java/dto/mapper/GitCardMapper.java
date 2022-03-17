package dto.mapper;

import dto.add.AddGitCardDto;
import dto.list.CardDto;
import dto.list.GitCardDto;
import entities.GitCard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GitCardMapper extends CardMapper {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public GitCardDto toDto(GitCard card) {
        CardDto c = super.toDto(card);

        String urlIssue = card.getUrlIssue();
        String gitHash = card.getGitHash();

        return new GitCardDto(c.getId(), c.getLibelle(), c.getCreatedDate(), c.getDeadLine(), c.getAllocatedTime(), c.getLieu(), c.getUrl(), c.getNote(), c.getPersonnesEnCharge(), c.getCreator(), c.getSection(), urlIssue, gitHash);
    }

    public GitCard toGitCard(AddGitCardDto cardDto) {
        return new GitCard(cardDto.getLibelle(), LocalDateTime.parse(cardDto.getCreatedDate(), formatter), LocalDateTime.parse(cardDto.getDeadLine(), formatter), cardDto.getLieu(), cardDto.getUrl(), cardDto.getNote(), cardDto.getUrlIssue(), cardDto.getGitHash());
    }
}
