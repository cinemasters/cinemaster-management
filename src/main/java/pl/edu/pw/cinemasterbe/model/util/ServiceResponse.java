package pl.edu.pw.cinemasterbe.model.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse<T> {
    private T data;
    private String message;
    private boolean success;
}
