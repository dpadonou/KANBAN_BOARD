package dto.add;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCardDto {
    private String libelle;
    private String createdDate;
    private String deadLine;
    private long allocatedTime;
    private String lieu;
    private String url;
    private String note;
}
