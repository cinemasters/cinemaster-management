package pl.edu.pw.cinemasterbe.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.cinemasterbe.model.dto.PageDto;
import pl.edu.pw.cinemasterbe.model.dto.cinema.CinemaGridDto;
import pl.edu.pw.cinemasterbe.model.mappers.CinemaMapper;
import pl.edu.pw.cinemasterbe.services.CinemaService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/cinemas")
public class CinemaController {
    private final CinemaService cinemaService;
    private final CinemaMapper cinemaMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDto<CinemaGridDto>> getMovies(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size) {
        var pageRequest = PageRequest.of(page, size);
        var data = cinemaService.getCinemas(pageRequest);
        var pageDto = PageDto.<CinemaGridDto>builder()
                .pageNumber(data.getNumber())
                .totalPages(data.getTotalPages())
                .totalElements(Math.toIntExact(data.getTotalElements()))
                .build();
        var cinemaDtos = new ArrayList<CinemaGridDto>();

        for (var cinema : data.getContent()) {
            var dto = cinemaMapper.mapToGridDto(cinema);
            var currentDay = LocalDate.now().getDayOfWeek();
            var currentOpening = cinema.getOpeningTimes().stream().filter((el) -> el.getDay() == currentDay).findFirst().orElseThrow();

            if (currentOpening.isClosed()) {
                dto.setOpen(false);
            } else {
                var currentTime = Instant.now();
                var opening = currentTime.atZone(ZoneId.systemDefault())
                        .withHour(currentOpening.getOpeningTime().getHours()).withMinute(currentOpening.getOpeningTime().getMinutes()).withSecond(0).toInstant();
                var closing = currentTime.atZone(ZoneId.systemDefault())
                        .withHour(currentOpening.getClosingTime().getHours()).withMinute(currentOpening.getClosingTime().getMinutes()).withSecond(0).toInstant();

                dto.setOpen(currentTime.isAfter(opening) && currentTime.isBefore(closing));
            }

            cinemaDtos.add(dto);
        }

        pageDto.setItems(cinemaDtos);

        return ResponseEntity.ok(pageDto);
    }
}
