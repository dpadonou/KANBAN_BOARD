package dto.mapper;

import dto.add.AddCardDto;
import dto.list.CardDto;
import dto.mapper.unlinked.UnLinkedSectionMapper;
import dto.mapper.unlinked.UnLinkedUserMapper;
import dto.unlinked.UnLinkedSectionDto;
import dto.unlinked.UnLinkedUserDto;
import entities.Card;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class CardMapper {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    UnLinkedUserMapper userMapper = new UnLinkedUserMapper();
    UnLinkedSectionMapper sectionMapper = new UnLinkedSectionMapper();

    public CardDto toDto(Card card) {
        long id = card.getId();
        String libelle = card.getLibelle();
        String createdDate = formatter.format(card.getCreatedDate());
        String deadLine = formatter.format(card.getDeadLine());
        long allocatedTime = card.getAllocatedTime();
        String lieu = card.getLieu();
        String url = card.getUrl();
        String note = card.getNote();
        List<UnLinkedUserDto> personnesEnCharge = card
                .getInCharge()
                .stream()
                .map(user -> userMapper.toUnLinkedDto(user))
                .collect(Collectors.toList());
        UnLinkedUserDto creator = userMapper.toUnLinkedDto(card.getCreator());
        UnLinkedSectionDto section = sectionMapper.toUnLinkedDto(card.getSection());

        return new CardDto(id, libelle, createdDate, deadLine, allocatedTime, lieu, url, note, personnesEnCharge, creator, section);
    }

    public Card toCard(AddCardDto cardDto) {
        return new Card(cardDto.getLibelle(), LocalDateTime.parse(cardDto.getCreatedDate(), formatter), LocalDateTime.parse(cardDto.getDeadLine(), formatter), cardDto.getLieu(), cardDto.getUrl(), cardDto.getNote());
    }

}
