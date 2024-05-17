package pl.edu.pw.cinemasterbe.model.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.cinemasterbe.model.enums.ScreeningTypeEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningTypeDto {
    private int id;
    private ScreeningTypeEnum type;
    private String name;
    private String description;
}
