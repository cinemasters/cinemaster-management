package pl.edu.pw.cinemasterbe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewGridDto {
    private int id;
    private String movie;
    private String client;
    private BigDecimal rating;
    private Instant publicationDate;
    private boolean hidden;
}
