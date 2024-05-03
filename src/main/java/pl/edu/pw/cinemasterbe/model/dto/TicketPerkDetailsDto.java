package pl.edu.pw.cinemasterbe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.cinemasterbe.model.enums.TicketPerkEnum;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketPerkDetailsDto {
    private int id;
    private String name;
    private String description;
    private BigDecimal charge;
    private TicketPerkEnum type;
    private Integer seatTypeId;
    private Integer screeningTypeId;
}
