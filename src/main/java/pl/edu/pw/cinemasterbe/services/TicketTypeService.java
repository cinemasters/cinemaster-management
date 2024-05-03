package pl.edu.pw.cinemasterbe.services;

import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.edu.pw.cinemasterbe.model.domain.TicketType;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.repositories.TicketTypeRepository;

@Service
@RequiredArgsConstructor
public class TicketTypeService {
    private final TicketTypeRepository ticketTypeRepository;
    private final Validator validator;

    public ServiceResponse<Page<TicketType>> getTicketTypes(Pageable pageRequest) {
        return ServiceResponse.<Page<TicketType>>builder().success(true).data(ticketTypeRepository.findAll(pageRequest)).build();
    }

    public ServiceResponse<TicketType> getTicketTypeById(int id) {
        var ticketType = ticketTypeRepository.findById(id).orElse(null);

        return ServiceResponse.<TicketType>builder().data(ticketType).success(ticketType != null).build();
    }

    public ServiceResponse<Void> createTicketType(TicketType newTicketType) {
        if (!validateTicketType(newTicketType)) {
            return ServiceResponse.<Void>builder().success(false).message("The ticket type data is invalid.").build();
        }
        if (ticketTypeRepository.existsByName(newTicketType.getName())) {
            return ServiceResponse.<Void>builder().success(false).message("Ticket type with name %s already exists.".formatted(newTicketType.getName())).build();
        }

        newTicketType.setId(-1);
        ticketTypeRepository.save(newTicketType);

        return ServiceResponse.<Void>builder().success(true).build();
    }

    public ServiceResponse<Void> updateTicketType(TicketType newTicketType, int id) {
        var oldTicketType = ticketTypeRepository.findById(id).orElse(null);

        if (oldTicketType == null || !validateTicketType(newTicketType)) {
            return ServiceResponse.<Void>builder().success(false).message("The ticket type data is invalid.").build();
        }

        if (!newTicketType.getName().equals(oldTicketType.getName()) && ticketTypeRepository.existsByName(newTicketType.getName())) {
            return ServiceResponse.<Void>builder().success(false).message("Ticket type with name %s already exists.".formatted(newTicketType.getName())).build();
        }

        newTicketType.setId(id);
        ticketTypeRepository.save(newTicketType);

        return ServiceResponse.<Void>builder().success(true).build();
    }

    private boolean validateTicketType(TicketType elem) {
        var violations = validator.validate(elem);

        return violations.isEmpty();
    }
}
