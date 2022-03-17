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

public class UserSessionDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<String> tachesAssigner;
    private List<String> tachesCreer;
}
