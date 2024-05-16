package pl.edu.pw.cinemasterbe.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.DayOfWeek;

@Entity
@Table(name = "CinemaOpeningTime")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CinemaOpeningTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "week_day")
    @NotNull
    private DayOfWeek day;
    @Column(name = "opening_time")
    private Time openingTime;
    @Column(name = "closing_time")
    private Time closingTime;
    @Column(name = "is_closed")
    private boolean closed;
    @ManyToOne
    @JoinColumn(name = "cinema_id", referencedColumnName = "id")
    private Cinema cinema;
}
