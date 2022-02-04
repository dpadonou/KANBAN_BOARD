package dto.batch;

import dto.add.AddCardDto;
import dto.add.AddUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BatchCardDto {
    List<AddCardDto> cardList;
}
