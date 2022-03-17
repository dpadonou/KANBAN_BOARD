package dto.unlinked;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnLinkedCardDto {
    private long id;
    private String libelle;
    private String createdDate;
    private String deadLine;
    private long allocatedTime;
    private String lieu;
    private String url;
    private String note;
}
