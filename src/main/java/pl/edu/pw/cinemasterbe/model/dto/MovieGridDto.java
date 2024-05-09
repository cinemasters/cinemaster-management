package pl.edu.pw.cinemasterbe.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class MovieGridDto {
    private int id;
    private String title;
    private String production;
    private String genre;
    private Date releaseDate;
    @JsonProperty("isVisible")
    private boolean visible;
}
