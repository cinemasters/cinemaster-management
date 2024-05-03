package pl.edu.pw.cinemasterbe.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.cinemasterbe.model.enums.TicketPerkEnum;

import java.math.BigDecimal;

@Entity
@Table(name = "TicketPerk")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketPerk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min = 1, max = 64)
    private String name;
    @Size(max = 255)
    private String description;
    @NotNull
    private BigDecimal charge;
    @Enumerated(EnumType.STRING)
    @NotNull
    private TicketPerkEnum type;
    @OneToOne
    @JoinColumn(name = "seat_type_id", referencedColumnName = "id")
    private SeatType seatType;
    @OneToOne
    @JoinColumn(name = "screening_type_id", referencedColumnName = "id")
    private ScreeningType screeningType;
}
