package dto.list;

import dto.unlinked.UnLinkedSectionDto;
import dto.unlinked.UnLinkedUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    private long id;
    private String libelle;
    private String createdDate;
    private String deadLine;
    private long allocatedTime;
    private String lieu;
    private String url;
    private String note;
    private List<UnLinkedUserDto> personnesEnCharge;
    private UnLinkedUserDto creator;
    private UnLinkedSectionDto section;
}
