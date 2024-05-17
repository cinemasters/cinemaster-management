package pl.edu.pw.cinemasterbe.model.dto.cinema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomLayoutGridDto {
    private int id;
    private String name;
    private int seatCount;
    private int rowCount;
    @Builder.Default // TODO
    private int useCount = 0;
}
