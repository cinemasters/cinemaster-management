package pl.edu.pw.cinemasterbe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LayoutSeatDto {
    private int col;
    private int row;
    private boolean isHidden;
    private Integer seatTypeId;
    private String code;
}
