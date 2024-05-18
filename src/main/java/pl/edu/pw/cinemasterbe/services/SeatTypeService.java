package pl.edu.pw.cinemasterbe.services;

import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.edu.pw.cinemasterbe.model.domain.cinema.SeatType;
import pl.edu.pw.cinemasterbe.model.dto.cinema.SeatTypeDto;
import pl.edu.pw.cinemasterbe.model.mappers.SeatTypeMapper;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.repositories.SeatTypeRepository;

import static pl.edu.pw.cinemasterbe.utils.ServiceUtils.buildErrorMessage;

@Service
@RequiredArgsConstructor
public class SeatTypeService {
    private final SeatTypeRepository seatTypeRepository;
    private final Validator validator;
    private final SeatTypeMapper seatTypeMapper;

    public Page<SeatType> getSeatTypes(Pageable pageRequest) {
        return seatTypeRepository.findAll(pageRequest);
    }

    public Iterable<SeatType> getUsableSeatTypes(int perkId) {
        return seatTypeRepository.findAllByPerkIdOrPerkNull(perkId);
    }

    public SeatType getSeatType(int id) {
        return seatTypeRepository.findById(id).orElse(null);
    }

    public ServiceResponse<Integer> createSeatType(SeatTypeDto dto) {
        var response = buildSeatTypeFromDto(dto, -1);

        if (!response.isSuccess()) {
            return ServiceResponse.<Integer>builder().success(false).message(response.getMessage()).build();
        }

        var seatType = seatTypeRepository.save(response.getData());

        return ServiceResponse.<Integer>builder().success(true).data(seatType.getId()).build();
    }

    public boolean hasLinkedPerk(int id) {
        var seatType = getSeatType(id);

        return seatType == null || seatType.getPerk() != null;
    }

    public ServiceResponse<Integer> updateSeatType(SeatTypeDto dto, int id) {
        var oldSeatType = getSeatType(id);

        if (oldSeatType == null) {
            return ServiceResponse.<Integer>builder().success(false).message("Seat type with id %d does not exist.".formatted(id)).build();
        }

        var response = buildSeatTypeFromDto(dto, id);

        if (!response.isSuccess()) {
            return ServiceResponse.<Integer>builder().success(false).message(response.getMessage()).build();
        }

        var newSeatType = response.getData();

        newSeatType.setId(id);
        newSeatType = seatTypeRepository.save(newSeatType);

        return ServiceResponse.<Integer>builder().success(true).data(newSeatType.getId()).build();
    }

    private ServiceResponse<SeatType> buildSeatTypeFromDto(SeatTypeDto dto, int id) {
        var seatType = seatTypeMapper.mapToEntity(dto);
        var violations = validator.validate(seatType);

        if (!violations.isEmpty()) {
            return ServiceResponse.<SeatType>builder().success(false).message(buildErrorMessage(violations)).build();
        }

        if (seatTypeRepository.existsByCodeAndIdNot(seatType.getCode(), id)) {
            return ServiceResponse.<SeatType>builder().success(false).message("Seat type with code %s already exists.".formatted(seatType.getCode())).build();
        }

        return ServiceResponse.<SeatType>builder().success(true).data(seatType).build();
    }
}
