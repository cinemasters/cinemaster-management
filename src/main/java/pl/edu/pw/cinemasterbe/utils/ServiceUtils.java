package pl.edu.pw.cinemasterbe.utils;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class ServiceUtils {
    public static <T> String buildErrorMessage(Set<ConstraintViolation<T>> violations) {
        var builder = new StringBuilder();

        for (var v : violations) {
            builder.append(v.getMessage()).append(" ");
        }

        return builder.toString().strip();
    }
}
