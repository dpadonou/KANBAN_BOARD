package dto.list;

import dto.unlinked.UnLinkedBoardDto;
import dto.unlinked.UnLinkedCardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SectionDto {
    private long id;
    private String name;
    private List<UnLinkedBoardDto> board;
    private List<UnLinkedCardDto> card;
}
