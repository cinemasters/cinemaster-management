package pl.edu.pw.cinemasterbe.model.dto.cinema;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("isHidden")
    private boolean hidden;
    private Integer seatTypeId;
    private String code;
}
