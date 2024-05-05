package pl.edu.pw.cinemasterbe.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "RoomLayout")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomLayout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min = 1, max = 64)
    private String name;
    @Column(name = "seat_count")
    private int seatCount;
    @Column(name = "row_count")
    private int rowCount;
    @Column(name = "col_count")
    private int columnCount;
    @OneToMany(mappedBy = "layout")
    private List<LayoutSeat> seats;
}
