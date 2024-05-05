package pl.edu.pw.cinemasterbe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomLayoutDetailsDto {
    private int id;
    private String name;
    private int rowCount;
    private int columnCount;
    private List<LayoutSeatDto> seats;
}
