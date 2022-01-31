package dto.mapper;

import dto.CardDto;
import dto.GitCardDto;
import entities.GitCard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GitCardMapper extends CardMapper {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public CardDto toDto(GitCard card) {
        CardDto c = super.toDto(card);

        String urlIssue = card.getUrlIssue();
        String gitHash = card.getGitHash();

        return new GitCardDto(c.getLibelle(), c.getCreatedDate(), c.getDeadLine(), c.getAllocatedTime(), c.getLieu(), c.getUrl(), c.getNote(), c.getPersonnesEnCharge(), c.getCreator(), c.getSection(), urlIssue, gitHash);
    }

    public GitCard toCard(GitCardDto cardDto) {
        return new GitCard(cardDto.getLibelle(), LocalDateTime.parse(cardDto.getCreatedDate(), formatter), LocalDateTime.parse(cardDto.getDeadLine(), formatter), cardDto.getLieu(), cardDto.getUrl(), cardDto.getNote(), cardDto.getUrlIssue(), cardDto.getGitHash());
    }
}
