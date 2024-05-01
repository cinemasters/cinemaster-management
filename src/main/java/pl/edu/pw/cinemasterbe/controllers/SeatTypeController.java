package pl.edu.pw.cinemasterbe.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.cinemasterbe.model.dto.PageDto;
import pl.edu.pw.cinemasterbe.model.dto.SeatTypeDto;
import pl.edu.pw.cinemasterbe.model.mappers.SeatTypeMapper;
import pl.edu.pw.cinemasterbe.services.SeatTypeService;

import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/seat-types")
public class SeatTypeController {
    private final SeatTypeService seatTypeService;
    private final SeatTypeMapper seatTypeMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDto<SeatTypeDto>> getSeatTypes(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size) {
        var pageRequest = PageRequest.of(page, size);
        var data = seatTypeService.getSeatTypes(pageRequest);

        return ResponseEntity.ok(PageDto.<SeatTypeDto>builder()
                .items(data.getContent().stream().map(seatTypeMapper::map).collect(Collectors.toList()))
                .pageNumber(data.getNumber())
                .totalElements(Math.toIntExact(data.getTotalElements()))
                .totalPages(data.getTotalPages())
                .build());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SeatTypeDto> getSeatType(@PathVariable int id) {
        var seatType = seatTypeService.getSeatTypeById(id);

        return ResponseEntity.ok(seatType != null ? seatTypeMapper.map(seatType) : null);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createSeatType(@RequestBody SeatTypeDto seatType) {
        seatTypeService.createSeatType(seatTypeMapper.mapToEntity(seatType));

        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateSeatType(@RequestBody SeatTypeDto seatType, @PathVariable int id) {
        seatTypeService.updateSeatType(seatTypeMapper.mapToEntity(seatType), id);

        return ResponseEntity.ok().build();
    }
}
