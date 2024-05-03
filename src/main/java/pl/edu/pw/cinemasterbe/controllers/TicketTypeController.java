package pl.edu.pw.cinemasterbe.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.cinemasterbe.model.dto.PageDto;
import pl.edu.pw.cinemasterbe.model.dto.TicketTypeDto;
import pl.edu.pw.cinemasterbe.model.mappers.PageMapper;
import pl.edu.pw.cinemasterbe.model.mappers.TicketTypeMapper;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.services.TicketTypeService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/ticket-types")
public class TicketTypeController {
    private final TicketTypeMapper ticketTypeMapper;
    private final PageMapper pageMapper;
    private final TicketTypeService ticketTypeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDto<TicketTypeDto>> getTicketTypes(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size) {
        var pageRequest = PageRequest.of(page, size);
        var response = ticketTypeService.getTicketTypes(pageRequest);

        return ResponseEntity.ok(pageMapper.map(response.getData(), ticketTypeMapper::mapToDto));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TicketTypeDto> getTicketType(@PathVariable int id) {
        var response = ticketTypeService.getTicketTypeById(id);

        return response.isSuccess() ? ResponseEntity.ok(ticketTypeMapper.mapToDto(response.getData())) : ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ServiceResponse<Void>> createTicketType(@RequestBody TicketTypeDto seatType) {
        var response = ticketTypeService.createTicketType(ticketTypeMapper.mapToEntity(seatType));

        return response.isSuccess() ? ResponseEntity.ok(response) : ResponseEntity.unprocessableEntity().body(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ServiceResponse<Void>> updateTicketType(@RequestBody TicketTypeDto seatType, @PathVariable int id) {
        var response = ticketTypeService.updateTicketType(ticketTypeMapper.mapToEntity(seatType), id);

        return response.isSuccess() ? ResponseEntity.ok(response) : ResponseEntity.unprocessableEntity().body(response);
    }
}
