package pl.edu.pw.cinemasterbe.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.cinemasterbe.model.dto.ClientDetailsDto;
import pl.edu.pw.cinemasterbe.model.dto.ClientGridDto;
import pl.edu.pw.cinemasterbe.model.dto.PageDto;
import pl.edu.pw.cinemasterbe.model.mappers.ClientMapper;
import pl.edu.pw.cinemasterbe.model.mappers.PageMapper;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.services.ClientService;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(path = "/clients")
public class ClientController {
    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final PageMapper pageMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDto<ClientGridDto>> getClients(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size) {
        var pageRequest = PageRequest.of(page, size);
        var data = clientService.getClients(pageRequest);

        return ResponseEntity.ok(pageMapper.map(data, clientMapper::mapToGridDto));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClientDetailsDto> getClient(@PathVariable int id) {
        var data = clientService.getClientById(id);

        return data == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(clientMapper.mapToDetailsDto(data));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ServiceResponse<Integer>> updateClient(@PathVariable int id, @RequestBody ClientDetailsDto dto) {
        var response = clientService.updateClient(dto, id);

        return response.isSuccess() ? ResponseEntity.ok(response) : ResponseEntity.unprocessableEntity().body(response);
    }
}
