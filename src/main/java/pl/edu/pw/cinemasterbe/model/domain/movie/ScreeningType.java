package pl.edu.pw.cinemasterbe.model.domain.movie;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.cinemasterbe.model.domain.ticket.TicketPerk;
import pl.edu.pw.cinemasterbe.model.enums.ScreeningTypeEnum;

@Entity
@Table(name = "ScreeningType")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScreeningType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    @NotNull
    private ScreeningTypeEnum type;
    @NotNull
    @Size(min = 1, max = 64)
    private String name;
    @Size(max = 255)
    private String description;
    @OneToOne(mappedBy = "screeningType")
    private TicketPerk perk;
}
