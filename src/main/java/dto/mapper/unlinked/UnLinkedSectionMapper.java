package dto.mapper.unlinked;

import dto.unlinked.UnLinkedSectionDto;
import entities.Section;

public class UnLinkedSectionMapper {

    public UnLinkedSectionDto toUnLinkedDto(Section section) {
        long id = section.getId();
        String name = section.getName();
        return new UnLinkedSectionDto(id, name);
    }
}
