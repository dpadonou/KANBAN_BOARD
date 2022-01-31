package dto.mapper;

import dto.UserDTO;
import entities.Card;
import entities.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UserMapper {

    public UserDTO toDto(User user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        List<String> tachesAssigner = user
                .getTaches()
                .stream()
                .map(Card::getLibelle)
                .collect(toList());
        List<String> tachesCreer = user
                .getOwned()
                .stream()
                .map(Card::getLibelle)
                .collect(toList());
        return new UserDTO(firstName, lastName, tachesAssigner, tachesCreer);
    }

    public User toUser(UserDTO userDTO) {
        return new User(userDTO.getFirstName(), userDTO.getLastName());
    }
}
