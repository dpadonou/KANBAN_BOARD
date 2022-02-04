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
public class CardDto {
    private String libelle;
    private String createdDate;
    private String deadLine;
    private long allocatedTime;
    private String lieu;
    private String url;
    private String note;
    private List<String> personnesEnCharge;
    private String creator;
    private String section;
}
