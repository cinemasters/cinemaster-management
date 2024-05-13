package pl.edu.pw.cinemasterbe.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "Review")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(max = 2048)
    private String comment;
    @Min(0)
    @Max(5)
    private BigDecimal rating;
    @NotNull
    @Column(name = "publication_date")
    private Instant publicationDate;
    @Column(name = "is_hidden")
    private boolean hidden;
    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    public boolean toggleVisibility() {
        hidden = !hidden;

        return hidden;
    }
}
