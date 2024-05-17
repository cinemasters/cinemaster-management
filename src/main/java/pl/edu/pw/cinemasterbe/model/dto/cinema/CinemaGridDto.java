package pl.edu.pw.cinemasterbe.model.dto.cinema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CinemaGridDto {
    private int id;
    private String name;
    private String city;
    private String email;
    private String phoneNumber;
    private boolean open;
}
