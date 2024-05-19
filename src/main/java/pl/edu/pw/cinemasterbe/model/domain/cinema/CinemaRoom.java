package pl.edu.pw.cinemasterbe.model.domain.cinema;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CinemaRoom")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CinemaRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min = 1, max = 64)
    private String name;
    @ManyToOne
    @JoinColumn(name = "layout_id", referencedColumnName = "id")
    private RoomLayout layout;
    @ManyToOne
    @JoinColumn(name = "cinema_id", referencedColumnName = "id")
    private Cinema cinema;
}
