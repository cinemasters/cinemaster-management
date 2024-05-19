package pl.edu.pw.cinemasterbe.model.domain.cinema;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cinema")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min = 1, max = 255)
    private String name;
    @Size(max = 2048)
    private String description;
    @NotNull
    @Size(min = 1, max = 255)
    private String city;
    @NotNull
    @Size(min = 1, max = 255)
    private String street;
    @Pattern(regexp = "^(|[0-9]{2}-[0-9]{3})$")
    @Column(name = "postal_code")
    private String postalCode;
    @Email
    @Size(max = 255)
    private String email;
    @Pattern(regexp = "(|[0-9]{9})")
    @Column(name = "phone_number")
    private String phoneNumber;
    @Builder.Default
    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL)
    private List<CinemaOpeningTime> openingTimes = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL)
    private List<CinemaRoom> rooms = new ArrayList<>();

    public void addOpeningTime(CinemaOpeningTime openingTime) {
        openingTimes.add(openingTime);
    }
}
