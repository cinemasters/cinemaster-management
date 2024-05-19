package pl.edu.pw.cinemasterbe.model.dto.cinema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CinemaRoomDto {
    private int layoutId;
    private String name;
}
