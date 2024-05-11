package pl.edu.pw.cinemasterbe.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Client")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(max = 64)
    private String name;
    @Size(max = 64)
    private String surname;
    @Size(min = 1, max = 64)
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "password_hash")
    private String password;
    @Size(max = 16)
    @Column(name = "phone_number")
    @Pattern(regexp = "(|[0-9]{9})")
    private String phoneNumber;
    @Column(name = "is_offer_subscribed")
    private boolean offerSubscribed;
    @Column(name = "is_locked")
    private boolean locked;
}
