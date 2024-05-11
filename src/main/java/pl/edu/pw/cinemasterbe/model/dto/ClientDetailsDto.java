package pl.edu.pw.cinemasterbe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDetailsDto {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String password;
    private String confirmPassword;
    private boolean offerSubscribed;
    private boolean locked;
}
