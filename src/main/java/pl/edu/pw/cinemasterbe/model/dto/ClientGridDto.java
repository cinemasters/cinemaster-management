package pl.edu.pw.cinemasterbe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientGridDto {
    private int id;
    private String name;
    private String surname;
    private String email;
    private boolean offerSubscribed;
    private boolean locked;
}
