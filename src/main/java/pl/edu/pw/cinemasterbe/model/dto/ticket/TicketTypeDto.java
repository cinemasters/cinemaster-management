package pl.edu.pw.cinemasterbe.model.dto.ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketTypeDto {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
}
