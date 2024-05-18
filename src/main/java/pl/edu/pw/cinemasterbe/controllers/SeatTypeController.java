package pl.edu.pw.cinemasterbe.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.cinemasterbe.model.dto.PageDto;
import pl.edu.pw.cinemasterbe.model.dto.cinema.SeatTypeDto;
import pl.edu.pw.cinemasterbe.model.mappers.PageMapper;
import pl.edu.pw.cinemasterbe.model.mappers.SeatTypeMapper;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.services.SeatTypeService;

import java.util.stream.StreamSupport;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/seat-types")
public class SeatTypeController {
    private final SeatTypeService seatTypeService;
    private final SeatTypeMapper seatTypeMapper;
    private final PageMapper pageMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDto<SeatTypeDto>> getSeatTypes(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size) {
        var pageRequest = PageRequest.of(page, size);
        var data = seatTypeService.getSeatTypes(pageRequest);

        return ResponseEntity.ok(pageMapper.map(data, seatTypeMapper::mapToDto));
    }

    @RequestMapping(path = "/usable", method = RequestMethod.GET)
    public ResponseEntity<Iterable<SeatTypeDto>> getUsableSeatTypes(@RequestParam(defaultValue = "-1", required = false) int perkId) {
        var data = seatTypeService.getUsableSeatTypes(perkId);

        return ResponseEntity.ok(StreamSupport.stream(data.spliterator(), false).map(seatTypeMapper::mapToDto).toList());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SeatTypeDto> getSeatType(@PathVariable int id) {
        var seatType = seatTypeService.getSeatType(id);

        return seatType != null ? ResponseEntity.ok(seatTypeMapper.mapToDto(seatType)) : ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ServiceResponse<Integer>> createSeatType(@RequestBody SeatTypeDto seatType) {
        var response = seatTypeService.createSeatType(seatType);

        return response.isSuccess() ? ResponseEntity.ok(response) : ResponseEntity.unprocessableEntity().body(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ServiceResponse<Integer>> updateSeatType(@RequestBody SeatTypeDto seatType, @PathVariable int id) {
        var response = seatTypeService.updateSeatType(seatType, id);

        return response.isSuccess() ? ResponseEntity.ok(response) : ResponseEntity.unprocessableEntity().body(response);
    }
}
