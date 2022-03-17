package dto.list;

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
    //private Long id;
    private String firstName;
    private String lastName;
    private List<String> tachesAssigner;
    private List<String> tachesCreer;
}
