package pl.edu.pw.cinemasterbe.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.cinemasterbe.model.enums.AgeRestrictionEnum;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private int id;
    private String title;
    private String originalTitle;
    private String originalLanguage;
    private String description;
    private String cast;
    private String director;
    private String production;
    private String genre;
    private Date releaseDate;
    private int length;
    private AgeRestrictionEnum ageRestriction;
    @JsonProperty("isVisible")
    private boolean visible;
    private List<Integer> videoTypes;
    private List<Integer> audioTypes;
}
