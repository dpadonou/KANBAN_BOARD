package dto.mapper;

import dto.add.AddUserDto;
import dto.list.UserDto;
import entities.Card;
import entities.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UserMapper {

    public UserDto toDto(User user) {
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
        return new UserDto(firstName, lastName, tachesAssigner, tachesCreer);
    }

    public User toUser(AddUserDto userDTO) {
        return new User(userDTO.getFirstName(), userDTO.getLastName());
    }
}
