package pl.edu.pw.cinemasterbe.model.domain.cinema;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.cinemasterbe.model.domain.ticket.TicketPerk;

@Entity
@Table(name = "SeatType")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Pattern(regexp = "[A-Za-z0-9]")
    @Size(min = 1, max = 1)
    @NotNull
    private String code;
    @Size(min = 1, max = 64)
    @NotNull
    private String name;
    @Size(max = 255)
    private String description;
    @OneToOne(mappedBy = "seatType")
    private TicketPerk perk;
}
