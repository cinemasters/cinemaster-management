package pl.edu.pw.cinemasterbe.services;

import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.edu.pw.cinemasterbe.model.domain.ticket.TicketType;
import pl.edu.pw.cinemasterbe.model.dto.ticket.TicketTypeDto;
import pl.edu.pw.cinemasterbe.model.mappers.TicketTypeMapper;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.repositories.TicketTypeRepository;

import static pl.edu.pw.cinemasterbe.utils.ServiceUtils.buildErrorMessage;

@Service
@RequiredArgsConstructor
public class TicketTypeService {
    private final TicketTypeRepository ticketTypeRepository;
    private final Validator validator;
    private final TicketTypeMapper ticketTypeMapper;

    public Page<TicketType> getTicketTypes(Pageable pageRequest) {
        return ticketTypeRepository.findAll(pageRequest);
    }

    public TicketType getTicketTypeById(int id) {
        return ticketTypeRepository.findById(id).orElse(null);
    }

    public ServiceResponse<Integer> createTicketType(TicketTypeDto dto) {
        var response = buildTicketTypeFromDto(dto, -1);

        if (!response.isSuccess()) {
            return ServiceResponse.<Integer>builder().success(false).message(response.getMessage()).build();
        }

        var ticketType = ticketTypeRepository.save(response.getData());

        return ServiceResponse.<Integer>builder().success(true).data(ticketType.getId()).build();
    }

    public ServiceResponse<Integer> updateTicketType(TicketTypeDto dto, int id) {
        var oldTicketType = ticketTypeRepository.findById(id).orElse(null);

        if (oldTicketType == null) {
            return ServiceResponse.<Integer>builder().success(false).message("Ticket type with id %d does not exist".formatted(id)).build();
        }

        var response = buildTicketTypeFromDto(dto, id);

        if (!response.isSuccess()) {
            return ServiceResponse.<Integer>builder().success(false).message(response.getMessage()).build();
        }

        var newTicketType = response.getData();

        newTicketType.setId(id);
        newTicketType = ticketTypeRepository.save(newTicketType);

        return ServiceResponse.<Integer>builder().success(true).data(newTicketType.getId()).build();
    }

    private ServiceResponse<TicketType> buildTicketTypeFromDto(TicketTypeDto dto, int id) {
        var ticketType = ticketTypeMapper.mapToEntity(dto);
        var violations = validator.validate(ticketType);

        if (!violations.isEmpty()) {
            return ServiceResponse.<TicketType>builder().success(false).message(buildErrorMessage(violations)).build();
        }

        if (ticketTypeRepository.existsByNameAndIdNot(ticketType.getName(), id)) {
            return ServiceResponse.<TicketType>builder().success(false).message("Ticket type with name %s already exists.".formatted(ticketType.getName())).build();
        }

        return ServiceResponse.<TicketType>builder().success(true).data(ticketType).build();
    }
}
