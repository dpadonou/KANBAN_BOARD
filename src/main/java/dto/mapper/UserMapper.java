package dto.mapper;

import dto.add.AddUserDto;
import dto.list.UserDto;
import dto.mapper.unlinked.UnLinkedCardMapper;
import dto.unlinked.UnLinkedCardDto;
import entities.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UserMapper {
    UnLinkedCardMapper cardMapper = new UnLinkedCardMapper();

    public UserDto toDto(User user) {
        long id = user.getId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        List<UnLinkedCardDto> tachesAssigner = user
                .getTaches()
                .stream()
                .map(card -> cardMapper.toUnLinkedDto(card))
                .collect(toList());
        List<UnLinkedCardDto> tachesCreer = user
                .getOwned()
                .stream()
                .map(card -> cardMapper.toUnLinkedDto(card))
                .collect(toList());
        return new UserDto(id, firstName, lastName, tachesAssigner, tachesCreer);
    }

    public User toUser(AddUserDto userDTO) {
        return new User(userDTO.getFirstName(), userDTO.getLastName());
    }
}
