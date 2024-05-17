package pl.edu.pw.cinemasterbe.model.dto.cinema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CinemaDetailsDto {
    private int id;
    private String name;
    private String description;
    private String city;
    private String street;
    private String postalCode;
    private String email;
    private String phoneNumber;
    private List<CinemaOpeningTimeDto> openingHours;
}
