package pl.edu.pw.cinemasterbe.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.cinemasterbe.model.dto.PageDto;
import pl.edu.pw.cinemasterbe.model.dto.RoomLayoutDetailsDto;
import pl.edu.pw.cinemasterbe.model.dto.RoomLayoutGridDto;
import pl.edu.pw.cinemasterbe.model.mappers.PageMapper;
import pl.edu.pw.cinemasterbe.model.mappers.RoomLayoutMapper;
import pl.edu.pw.cinemasterbe.services.RoomLayoutService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/room-layouts")
public class RoomLayoutController {
    private final RoomLayoutService roomLayoutService;
    private final PageMapper pageMapper;
    private final RoomLayoutMapper roomLayoutMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDto<RoomLayoutGridDto>> getRoomLayouts(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size) {
        var pageRequest = PageRequest.of(page, size);
        var roomLayouts = roomLayoutService.getRoomLayouts(pageRequest).getData();

        return ResponseEntity.ok(pageMapper.map(roomLayouts, roomLayoutMapper::mapToGridDto));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RoomLayoutDetailsDto> getRoomLayoutById(@PathVariable int id) {
        var response = roomLayoutService.getRoomLayoutById(id);

        return response.isSuccess() ? ResponseEntity.ok(roomLayoutMapper.mapToDetailsDto(response.getData()))
                : ResponseEntity.noContent().build();
    }
}
