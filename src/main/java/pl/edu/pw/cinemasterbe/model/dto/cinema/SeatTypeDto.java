package pl.edu.pw.cinemasterbe.model.dto.cinema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatTypeDto {
    private int id;
    private String code;
    private String name;
    private String description;
}
