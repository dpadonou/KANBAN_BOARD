package dto.mapper;

import dto.BoardDto;
import entities.Board;
import entities.Section;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class BoardMapper {

    public BoardDto toDto(Board board) {
        String title = board.getTitle();
        List<String> sessions = board
                .getSections()
                .stream()
                .map(Section::getName)
                .collect(toList());

        return new BoardDto(title, sessions);
    }

    public Board toBoard(BoardDto boardDto) {
        return new Board(boardDto.getTitle());
    }
}
