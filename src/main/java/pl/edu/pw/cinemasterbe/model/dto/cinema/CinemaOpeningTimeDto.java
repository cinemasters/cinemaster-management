package pl.edu.pw.cinemasterbe.model.dto.cinema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CinemaOpeningTimeDto {
    private DayOfWeek day;
    private String openingHour;
    private String closingHour;
    private boolean closed;
}
