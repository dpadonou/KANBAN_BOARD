package dto.mapper;

import dto.add.AddBoardDto;
import dto.list.BoardDto;
import dto.mapper.unlinked.UnLinkedSectionMapper;
import dto.unlinked.UnLinkedSectionDto;
import entities.Board;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class BoardMapper {

    UnLinkedSectionMapper sectionMapper = new UnLinkedSectionMapper();

    public BoardDto toDto(Board board) {
        long id = board.getId();
        String title = board.getTitle();
        List<UnLinkedSectionDto> sections = board
                .getSections()
                .stream()
                .map(section -> sectionMapper.toUnLinkedDto(section))
                .collect(toList());

        return new BoardDto(id, title, sections);
    }

    public Board toBoard(AddBoardDto boardDto) {
        return new Board(boardDto.getTitle());
    }
}
