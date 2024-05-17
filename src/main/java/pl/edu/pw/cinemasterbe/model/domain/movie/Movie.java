package pl.edu.pw.cinemasterbe.model.domain.movie;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.cinemasterbe.model.enums.AgeRestrictionEnum;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Movie")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min = 1, max = 255)
    private String title;
    @Size(max = 255)
    @Column(name = "original_title")
    private String originalTitle;
    @Size(max = 2)
    @Column(name = "original_language")
    private String originalLanguage;
    @Size(max = 2048)
    private String description;
    @Column(name = "movie_cast")
    @Size(max = 255)
    private String cast;
    @Size(min = 1, max = 255)
    private String director;
    @Size(min = 1, max = 64)
    private String production;
    @Size(min = 1, max = 64)
    private String genre;
    @Column(name = "release_date")
    @NotNull
    private Instant releaseDate;
    @Positive
    private int length;
    @NotNull
    @Column(name = "age_restriction")
    @Enumerated(EnumType.STRING)
    private AgeRestrictionEnum ageRestriction;
    @Column(name = "is_visible")
    private boolean visible;
    @Builder.Default
    @ManyToMany
    @JoinTable(name = "MovieScreeningType", joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "screening_type_id", referencedColumnName = "id"))
    private List<ScreeningType> screeningTypes = new ArrayList<>();

    public void addScreeningType(ScreeningType type) {
        screeningTypes.add(type);
    }
}
