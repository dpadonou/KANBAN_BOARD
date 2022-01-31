package dto.mapper;

import dto.CardDto;
import entities.Card;
import entities.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class CardMapper {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public CardDto toDto(Card card) {
        String libelle = card.getLibelle();
        String createdDate = formatter.format(card.getCreatedDate());
        String deadLine = formatter.format(card.getDeadLine());
        long allocatedTime = card.getAllocatedTime();
        String lieu = card.getLieu();
        String url = card.getUrl();
        String note = card.getNote();
        List<String> personnesEnCharge = card
                .getInCharge()
                .stream()
                .map(User::concatName)
                .collect(Collectors.toList());
        String creator = card.getCreator().concatName();
        String section = card.getSection().getName();

        return new CardDto(libelle, createdDate, deadLine, allocatedTime, lieu, url, note, personnesEnCharge, creator, section);
    }

    public Card toCard(CardDto cardDto) {
        return new Card(cardDto.getLibelle(), LocalDateTime.parse(cardDto.getCreatedDate(), formatter), LocalDateTime.parse(cardDto.getDeadLine(), formatter), cardDto.getLieu(), cardDto.getUrl(), cardDto.getNote());
    }

}
