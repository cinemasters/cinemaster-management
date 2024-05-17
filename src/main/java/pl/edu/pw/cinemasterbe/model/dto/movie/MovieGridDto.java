package pl.edu.pw.cinemasterbe.model.dto.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieGridDto {
    private int id;
    private String title;
    private String production;
    private String genre;
    private Date releaseDate;
    @JsonProperty("isVisible")
    private boolean visible;
}
