package dto.mapper;

import dto.add.AddSectionDto;
import dto.list.SectionDto;
import entities.Board;
import entities.Card;
import entities.Section;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SectionMapper {

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

    public Section toSection(AddSectionDto sectionDto) {
        return new Section(sectionDto.getName());
    }
}
