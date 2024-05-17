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
        var response = seatTypeService.getSeatTypes(pageRequest);

        return ResponseEntity.ok(pageMapper.map(response.getData(), seatTypeMapper::map));
    }

    @RequestMapping(path = "/usable", method = RequestMethod.GET)
    public ResponseEntity<Iterable<SeatTypeDto>> getUsableSeatTypes(@RequestParam(defaultValue = "-1", required = false) int perkId) {
        var response = seatTypeService.getUsableSeatTypes(perkId);

        return ResponseEntity.ok(StreamSupport.stream(response.getData().spliterator(), false).map(seatTypeMapper::map).toList());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SeatTypeDto> getSeatType(@PathVariable int id) {
        var response = seatTypeService.getSeatTypeById(id);

        return response.isSuccess() ? ResponseEntity.ok(seatTypeMapper.map(response.getData())) : ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ServiceResponse<Void>> createSeatType(@RequestBody SeatTypeDto seatType) {
        var response = seatTypeService.createSeatType(seatTypeMapper.mapToEntity(seatType));

        return response.isSuccess() ? ResponseEntity.ok(response) : ResponseEntity.unprocessableEntity().body(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ServiceResponse<Void>> updateSeatType(@RequestBody SeatTypeDto seatType, @PathVariable int id) {
        var response = seatTypeService.updateSeatType(seatTypeMapper.mapToEntity(seatType), id);

        return response.isSuccess() ? ResponseEntity.ok(response) : ResponseEntity.unprocessableEntity().body(response);
    }
}
