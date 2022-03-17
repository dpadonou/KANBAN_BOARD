package dto.mapper.unlinked;

import dto.unlinked.UnLinkedBoardDto;
import entities.Board;

public class UnLinkedBoardMapper {

    public UnLinkedBoardDto toUnLinkedDto(Board board) {
        long id = board.getId();
        String title = board.getTitle();

        return new UnLinkedBoardDto(id, title);
    }
}
