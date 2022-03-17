package dto.mapper.unlinked;

import dto.unlinked.UnLinkedCardDto;
import entities.Card;

import java.time.format.DateTimeFormatter;

public class UnLinkedCardMapper {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public UnLinkedCardDto toUnLinkedDto(Card card) {
        long id = card.getId();
        String libelle = card.getLibelle();
        String createdDate = formatter.format(card.getCreatedDate());
        String deadLine = formatter.format(card.getDeadLine());
        long allocatedTime = card.getAllocatedTime();
        String lieu = card.getLieu();
        String url = card.getUrl();
        String note = card.getNote();

        return new UnLinkedCardDto(id, libelle, createdDate, deadLine, allocatedTime, lieu, url, note);
    }
}
