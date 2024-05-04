package pl.edu.pw.cinemasterbe.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.cinemasterbe.model.dto.PageDto;
import pl.edu.pw.cinemasterbe.model.dto.TicketPerkDetailsDto;
import pl.edu.pw.cinemasterbe.model.dto.TicketPerkGridDto;
import pl.edu.pw.cinemasterbe.model.mappers.PageMapper;
import pl.edu.pw.cinemasterbe.model.mappers.TicketPerkMapper;
import pl.edu.pw.cinemasterbe.services.TicketPerkService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/ticket-perks")
public class TicketPerkController {
    private final TicketPerkService ticketPerkService;
    private final PageMapper pageMapper;
    private final TicketPerkMapper ticketPerkMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDto<TicketPerkGridDto>> getTicketPerks(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size) {
        var pageRequest = PageRequest.of(page, size);
        var response = ticketPerkService.getTicketPerks(pageRequest);

        return ResponseEntity.ok(pageMapper.map(response.getData(), ticketPerkMapper::mapToGridDto));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TicketPerkDetailsDto> getTicketPerk(@PathVariable int id) {
        var response = ticketPerkService.getTicketPerkById(id);

        return response.isSuccess() ? ResponseEntity.ok(ticketPerkMapper.mapToDetailsDto(response.getData())) : ResponseEntity.noContent().build();
    }
}
