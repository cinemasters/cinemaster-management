package pl.edu.pw.cinemasterbe.model.domain.cinema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LayoutSeat")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LayoutSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "row_num")
    private int row;
    @Column(name = "col_num")
    private int column;
    @Column(name = "is_hidden")
    private boolean hidden;
    @ManyToOne
    @JoinColumn(name = "layout_id", referencedColumnName = "id")
    private RoomLayout layout;
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private SeatType type;
}
