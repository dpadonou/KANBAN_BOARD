package dto.mapper;

import dto.add.AddUserDto;
import dto.list.UserDto;
import dto.list.UserSessionDto;
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
    public UserSessionDto toSessionDto(User user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String password = user.getPassword();
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
        return new UserSessionDto(user.getId(),firstName, lastName,email,password, tachesAssigner, tachesCreer);
    }

    public User toUser(AddUserDto userDTO) {
        return new User(userDTO.getFirstName(), userDTO.getLastName(),userDTO.getEmail(),userDTO.getPassword());
    }

}
