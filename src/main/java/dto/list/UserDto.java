package dto.list;

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
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private List<UnLinkedCardDto> tachesAssigner;
    private List<UnLinkedCardDto> tachesCreer;
}
