package pl.edu.pw.cinemasterbe.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @NotNull
    @Size(min = 1, max = 64)
    private String name;
    @Positive
    @Max(30)
    private int rowCount;
    @Positive
    @Max(40)
    private int columnCount;
    @NotNull
    private List<LayoutSeatDto> seats;
}
