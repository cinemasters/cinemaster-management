package pl.edu.pw.cinemasterbe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.cinemasterbe.model.enums.ScreeningTypeEnum;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketPerkGridDto {
    private int id;
    private String name;
    private String description;
    private BigDecimal charge;
    private ScreeningTypeEnum type;
    private String seatTypeName;
    private String screeningTypeName;
}
