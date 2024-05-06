package pl.edu.pw.cinemasterbe.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    private String name;
    @Column(name = "seat_count")
    private int seatCount;
    @Column(name = "row_count")
    private int rowCount;
    @Column(name = "col_count")
    private int columnCount;
    @OneToMany(mappedBy = "layout", cascade = CascadeType.ALL)
    @Builder.Default
    private List<LayoutSeat> seats = new ArrayList<>();

    public void addSeat(LayoutSeat seat) {
        seats.add(seat);
    }
}
