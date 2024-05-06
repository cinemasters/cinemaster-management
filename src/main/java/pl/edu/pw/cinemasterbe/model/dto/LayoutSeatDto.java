package pl.edu.pw.cinemasterbe.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LayoutSeatDto {
    @Positive
    @Max(40)
    private int col;
    @Positive
    @Max(30)
    private int row;
    private boolean isHidden;
    private Integer seatTypeId;
    private String code;
}
