package pl.edu.pw.cinemasterbe.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.cinemasterbe.model.domain.ScreeningType;
import pl.edu.pw.cinemasterbe.model.dto.MovieDto;
import pl.edu.pw.cinemasterbe.model.dto.MovieGridDto;
import pl.edu.pw.cinemasterbe.model.dto.PageDto;
import pl.edu.pw.cinemasterbe.model.enums.ScreeningTypeEnum;
import pl.edu.pw.cinemasterbe.model.mappers.MovieMapper;
import pl.edu.pw.cinemasterbe.model.mappers.PageMapper;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.services.MovieService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/movies")
public class MovieController {
    private final MovieService movieService;
    private final PageMapper pageMapper;
    private final MovieMapper movieMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDto<MovieGridDto>> getMovies(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size) {
        var pageRequest = PageRequest.of(page, size);
        var data = movieService.getMovies(pageRequest);

        return ResponseEntity.ok(pageMapper.map(data, movieMapper::mapToGridDto));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MovieDto> getMovie(@PathVariable int id) {
        var movie = movieService.getMovie(id);

        if (movie == null) {
            return ResponseEntity.noContent().build();
        }

        var dto = movieMapper.mapToDetailsDto(movie);

        dto.setAudioTypes(movie.getScreeningTypes().stream().filter(el -> el.getType() == ScreeningTypeEnum.Audio).map(ScreeningType::getId).toList());
        dto.setVideoTypes(movie.getScreeningTypes().stream().filter(el -> el.getType() == ScreeningTypeEnum.Video).map(ScreeningType::getId).toList());

        return ResponseEntity.ok(dto);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ServiceResponse<Integer>> createMovie(@RequestBody MovieDto movie) {
        var response = movieService.createMovie(movie);

        return response.isSuccess() ? ResponseEntity.ok(response) : ResponseEntity.unprocessableEntity().body(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ServiceResponse<Integer>> updateMovie(@RequestBody MovieDto movie, @PathVariable int id) {
        var response = movieService.updateMovie(movie, id);

        return response.isSuccess() ? ResponseEntity.ok(response) : ResponseEntity.unprocessableEntity().body(response);
    }


}
