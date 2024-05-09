package pl.edu.pw.cinemasterbe.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.cinemasterbe.model.dto.MovieGridDto;
import pl.edu.pw.cinemasterbe.model.dto.PageDto;
import pl.edu.pw.cinemasterbe.model.mappers.MovieMapper;
import pl.edu.pw.cinemasterbe.model.mappers.PageMapper;
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

}
