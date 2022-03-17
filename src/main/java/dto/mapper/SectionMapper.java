package dto.mapper;

import dto.add.AddSectionDto;
import dto.list.SectionDto;
import dto.mapper.unlinked.UnLinkedBoardMapper;
import dto.mapper.unlinked.UnLinkedCardMapper;
import dto.unlinked.UnLinkedBoardDto;
import dto.unlinked.UnLinkedCardDto;
import entities.Section;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SectionMapper {

    UnLinkedCardMapper cardMapper = new UnLinkedCardMapper();
    UnLinkedBoardMapper boardMapper = new UnLinkedBoardMapper();

    public SectionDto toDto(Section section) {
        long id = section.getId();
        String name = section.getName();
        List<UnLinkedBoardDto> boards = section
                .getBoards()
                .stream()
                .map(board -> boardMapper.toUnLinkedDto(board))
                .collect(toList());

        List<UnLinkedCardDto> cards = section
                .getFiches()
                .stream()
                .map(card -> cardMapper.toUnLinkedDto(card))
                .collect(toList());
        return new SectionDto(id, name, boards, cards);
    }

    public Section toSection(AddSectionDto sectionDto) {
        return new Section(sectionDto.getName());
    }
}
