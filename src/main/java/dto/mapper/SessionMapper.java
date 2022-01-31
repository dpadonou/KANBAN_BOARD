package dto.mapper;

import dto.SectionDto;
import entities.Board;
import entities.Card;
import entities.Section;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SessionMapper {

    public SectionDto toDto(Section section) {
        String name = section.getName();
        List<String> boards = section
                .getBoards()
                .stream()
                .map(Board::getTitle)
                .collect(toList());

        List<String> cards = section
                .getFiches()
                .stream()
                .map(Card::getLibelle)
                .collect(toList());
        return new SectionDto(name, boards, cards);
    }

    public Section toUser(SectionDto sectionDto) {
        return new Section(sectionDto.getName());
    }
}
