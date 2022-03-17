package dto.mapper.unlinked;

import dto.unlinked.UnLinkedUserDto;
import entities.User;

public class UnLinkedUserMapper {

    public UnLinkedUserDto toUnLinkedDto(User user) {
        long id = user.getId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        return new UnLinkedUserDto(id, firstName, lastName);
    }
}
