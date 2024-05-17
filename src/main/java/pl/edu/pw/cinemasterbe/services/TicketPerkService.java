package pl.edu.pw.cinemasterbe.services;

import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pw.cinemasterbe.model.domain.ticket.TicketPerk;
import pl.edu.pw.cinemasterbe.model.dto.ticket.TicketPerkDetailsDto;
import pl.edu.pw.cinemasterbe.model.enums.TicketPerkEnum;
import pl.edu.pw.cinemasterbe.model.mappers.TicketPerkMapper;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.repositories.TicketPerkRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TicketPerkService {
    private final TicketPerkRepository ticketPerkRepository;
    private final ScreeningTypeService screeningTypeService;
    private final SeatTypeService seatTypeService;
    private final TicketPerkMapper mapper;
    private final Validator validator;

    public ServiceResponse<Page<TicketPerk>> getTicketPerks(PageRequest pageRequest) {
        var ticketPerks = ticketPerkRepository.findAll(pageRequest);

        return ServiceResponse.<Page<TicketPerk>>builder().success(true).data(ticketPerks).build();
    }

    public ServiceResponse<TicketPerk> getTicketPerkById(int id) {
        var ticketPerk = ticketPerkRepository.findById(id).orElse(null);

        return ServiceResponse.<TicketPerk>builder().success(ticketPerk != null).data(ticketPerk).build();
    }

    public ServiceResponse<Integer> createTicketPerk(TicketPerkDetailsDto dto) {
        var newTicketPerk = mapper.mapToEntity(dto);

        if (newTicketPerk.getType() == TicketPerkEnum.SeatType) {
            var seatType = seatTypeService.getSeatTypeById(dto.getSeatTypeId()).getData();

            if (seatType == null || seatTypeService.hasLinkedPerk(seatType.getId())) {
                return ServiceResponse.<Integer>builder().success(false).message("The seat type is invalid or is already in use.").build();
            }

            seatType.setPerk(newTicketPerk);
            newTicketPerk.setSeatType(seatType);
        } else {
            var screeningType = screeningTypeService.getScreeningTypeById(dto.getScreeningTypeId()).getData();

            if (screeningType == null || screeningTypeService.hasLinkedPerk(screeningType.getId())) {
                return ServiceResponse.<Integer>builder().success(false).message("The screening type is invalid or is already in use.").build();
            }

            screeningType.setPerk(newTicketPerk);
            newTicketPerk.setScreeningType(screeningType);
        }

        var validationResponse = validateTicketPerk(newTicketPerk);

        if (!validationResponse.isSuccess()) {
            return validationResponse;
        }

        var ticketPerk = ticketPerkRepository.save(newTicketPerk);

        return ServiceResponse.<Integer>builder().success(true).data(ticketPerk.getId()).build();
    }

    public ServiceResponse<Integer> updateTicketPerk(TicketPerkDetailsDto dto, int id) {
        var oldTicketPerk = getTicketPerkById(id).getData();

        if (oldTicketPerk == null) {
            return ServiceResponse.<Integer>builder().success(false).message("Ticket perk with this id does not exist.").build();
        }

        var newTicketPerk = mapper.mapToEntity(dto);

        if (newTicketPerk.getType() == TicketPerkEnum.SeatType) {
            var seatType = seatTypeService.getSeatTypeById(dto.getSeatTypeId()).getData();

            if (seatType == null || (seatTypeService.hasLinkedPerk(seatType.getId()) && seatType.getId() != oldTicketPerk.getSeatType().getId())) {
                return ServiceResponse.<Integer>builder().success(false).message("The seat type is invalid or is already in use.").build();
            }

            seatType.setPerk(newTicketPerk);
            newTicketPerk.setSeatType(seatType);
        } else {
            var screeningType = screeningTypeService.getScreeningTypeById(dto.getScreeningTypeId()).getData();

            if (screeningType == null || (screeningTypeService.hasLinkedPerk(screeningType.getId()) && screeningType.getId() != oldTicketPerk.getScreeningType().getId())) {
                return ServiceResponse.<Integer>builder().success(false).message("The screening type is invalid or is already in use.").build();
            }

            screeningType.setPerk(newTicketPerk);
            newTicketPerk.setScreeningType(screeningType);
        }

        var validationResponse = validateTicketPerk(newTicketPerk);

        if (!validationResponse.isSuccess()) {
            return validationResponse;
        }

        newTicketPerk.setId(oldTicketPerk.getId());
        var ticketPerk = ticketPerkRepository.save(newTicketPerk);

        return ServiceResponse.<Integer>builder().success(true).data(ticketPerk.getId()).build();
    }

    private ServiceResponse<Integer> validateTicketPerk(TicketPerk ticketPerk) {
        var violations = validator.validate(ticketPerk);

        if (!violations.isEmpty()) {
            var errorMessage = new StringBuilder();

            for (var v : violations) {
                errorMessage.append(v.getMessage()).append(" ");
            }

            return ServiceResponse.<Integer>builder().success(false).message(errorMessage.toString().strip()).build();
        }

        if (ticketPerk.getCharge().equals(BigDecimal.ZERO)) {
            return ServiceResponse.<Integer>builder().success(false).message("The perk charge must not be zero.").build();
        }

        return ServiceResponse.<Integer>builder().success(true).build();
    }
}
