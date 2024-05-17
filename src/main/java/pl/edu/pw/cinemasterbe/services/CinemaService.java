package pl.edu.pw.cinemasterbe.services;

import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pw.cinemasterbe.model.domain.cinema.Cinema;
import pl.edu.pw.cinemasterbe.model.domain.cinema.CinemaOpeningTime;
import pl.edu.pw.cinemasterbe.model.dto.cinema.CinemaDetailsDto;
import pl.edu.pw.cinemasterbe.model.mappers.CinemaMapper;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.repositories.CinemaRepository;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.HashSet;

import static pl.edu.pw.cinemasterbe.utils.ServiceUtils.buildErrorMessage;

@Service
@RequiredArgsConstructor
public class CinemaService {
    private final CinemaRepository cinemaRepository;
    private final CinemaMapper cinemaMapper;
    private final Validator validator;

    public Page<Cinema> getCinemas(PageRequest pageRequest) {
        return cinemaRepository.findAll(pageRequest);
    }

    public Cinema getCinema(int id) {
        return cinemaRepository.findById(id).orElse(null);
    }

    public ServiceResponse<Integer> createCinema(CinemaDetailsDto dto) {
        var response = buildCinemaFromDto(dto);

        if (!response.isSuccess()) {
            return ServiceResponse.<Integer>builder().success(false).message(response.getMessage()).build();
        }

        var newMovie = cinemaRepository.save(response.getData());

        return ServiceResponse.<Integer>builder().success(true).data(newMovie.getId()).build();
    }

    public ServiceResponse<Integer> updateCinema(CinemaDetailsDto dto, int id) {
        var oldCinema = getCinema(id);

        if (oldCinema == null) {
            return ServiceResponse.<Integer>builder().success(false).message("Cinema with id %d does not exist.".formatted(id)).build();
        }

        var response = buildCinemaFromDto(dto);

        if (!response.isSuccess()) {
            return ServiceResponse.<Integer>builder().success(false).message(response.getMessage()).build();
        }

        var updatedCinema = response.getData();

        updatedCinema.setId(id);
        updatedCinema = cinemaRepository.save(updatedCinema);

        return ServiceResponse.<Integer>builder().success(true).data(updatedCinema.getId()).build();
    }

    private ServiceResponse<Cinema> buildCinemaFromDto(CinemaDetailsDto dto) {
        var cinema = cinemaMapper.mapToEntity(dto);
        var violations = validator.validate(cinema);

        if (!violations.isEmpty()) {
            return ServiceResponse.<Cinema>builder().success(false).message(buildErrorMessage(violations)).build();
        }

        var uniqueDays = new HashSet<DayOfWeek>();

        if (dto.getOpeningHours() == null || dto.getOpeningHours().size() != 7) {
            return ServiceResponse.<Cinema>builder().success(false).message("Opening times for all days of the week must be provided.").build();
        }

        for (var timeDto : dto.getOpeningHours()) {
            var openTime = CinemaOpeningTime.builder()
                    .cinema(cinema)
                    .closed(timeDto.isClosed())
                    .day(timeDto.getDay())
                    .build();

            if (openTime.getDay() == null) {
                return ServiceResponse.<Cinema>builder().success(false).message("The opening time day must be provided.").build();
            }

            uniqueDays.add(openTime.getDay());

            if (openTime.isClosed()) {
                cinema.addOpeningTime(openTime);
                continue;
            }

            if (timeDto.getOpeningTime() == null || timeDto.getClosingTime() == null) {
                return ServiceResponse.<Cinema>builder().success(false).message("Time must be provided for non-closed day.").build();
            }

            var opening = Time.valueOf(timeDto.getOpeningTime());
            var closing = Time.valueOf(timeDto.getClosingTime());

            if (opening.compareTo(closing) >= 0) {
                return ServiceResponse.<Cinema>builder().success(false).message("Time range must be valid.").build();
            }

            openTime.setOpeningTime(opening);
            openTime.setClosingTime(closing);
            cinema.addOpeningTime(openTime);
        }

        if (uniqueDays.size() != 7) {
            return ServiceResponse.<Cinema>builder().success(false).message("The opening time for all days must be provided.").build();
        }

        return ServiceResponse.<Cinema>builder().success(true).data(cinema).build();
    }
}
