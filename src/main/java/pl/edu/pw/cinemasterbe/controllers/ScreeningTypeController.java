package pl.edu.pw.cinemasterbe.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.cinemasterbe.model.dto.PageDto;
import pl.edu.pw.cinemasterbe.model.dto.movie.ScreeningTypeDto;
import pl.edu.pw.cinemasterbe.model.mappers.PageMapper;
import pl.edu.pw.cinemasterbe.model.mappers.ScreeningTypeMapper;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.services.ScreeningTypeService;

import java.util.stream.StreamSupport;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/screening-types")
public class ScreeningTypeController {
    private final ScreeningTypeMapper screeningTypeMapper;
    private final PageMapper pageMapper;
    private final ScreeningTypeService screeningTypeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDto<ScreeningTypeDto>> getScreeningTypes(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size) {
        var pageRequest = PageRequest.of(page, size);
        var data = screeningTypeService.getScreeningTypes(pageRequest);

        return ResponseEntity.ok(pageMapper.map(data, screeningTypeMapper::mapToDto));
    }

    @RequestMapping(path = "/usable", method = RequestMethod.GET)
    public ResponseEntity<Iterable<ScreeningTypeDto>> getUsableScreeningTypes(@RequestParam(defaultValue = "-1", required = false) int perkId) {
        var data = screeningTypeService.getUsableScreeningTypes(perkId);

        return ResponseEntity.ok(StreamSupport.stream(data.spliterator(), false).map(screeningTypeMapper::mapToDto).toList());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ScreeningTypeDto> getScreeningType(@PathVariable int id) {
        var screeningType = screeningTypeService.getScreeningType(id);

        return screeningType != null ? ResponseEntity.ok(screeningTypeMapper.mapToDto(screeningType)) : ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ServiceResponse<Integer>> createScreeningType(@RequestBody ScreeningTypeDto seatType) {
        var response = screeningTypeService.createScreeningType(seatType);

        return response.isSuccess() ? ResponseEntity.ok(response) : ResponseEntity.unprocessableEntity().body(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ServiceResponse<Integer>> updateScreeningType(@RequestBody ScreeningTypeDto seatType, @PathVariable int id) {
        var response = screeningTypeService.updateScreeningType(seatType, id);

        return response.isSuccess() ? ResponseEntity.ok(response) : ResponseEntity.unprocessableEntity().body(response);
    }
}
