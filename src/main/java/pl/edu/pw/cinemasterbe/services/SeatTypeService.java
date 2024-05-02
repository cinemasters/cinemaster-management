package pl.edu.pw.cinemasterbe.services;

import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.edu.pw.cinemasterbe.model.domain.SeatType;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.repositories.SeatTypeRepository;

@Service
@RequiredArgsConstructor
public class SeatTypeService {
    private final SeatTypeRepository seatTypeRepository;
    private final Validator validator;

    public ServiceResponse<Page<SeatType>> getSeatTypes(Pageable pageRequest) {
        return ServiceResponse.<Page<SeatType>>builder().success(true).data(seatTypeRepository.findAll(pageRequest)).build();
    }

    public ServiceResponse<SeatType> getSeatTypeById(int id) {
        var seatType = seatTypeRepository.findById(id).orElse(null);

        return ServiceResponse.<SeatType>builder().data(seatType).success(seatType != null).build();
    }

    public ServiceResponse<Void> createSeatType(SeatType newSeatType) {
        if (!validateSeatType(newSeatType)) {
            return ServiceResponse.<Void>builder().success(false).message("The seat type data is invalid.").build();
        }
        if (seatTypeRepository.existsByCode(newSeatType.getCode())) {
            return ServiceResponse.<Void>builder().success(false).message("Seat type with code %s already exists.".formatted(newSeatType.getCode())).build();
        }

        newSeatType.setId(-1);
        seatTypeRepository.save(newSeatType);

        return ServiceResponse.<Void>builder().success(true).build();
    }

    public ServiceResponse<Void> updateSeatType(SeatType newSeatType, int id) {
        var oldSeatType = seatTypeRepository.findById(id).orElse(null);

        if (oldSeatType == null || !validateSeatType(newSeatType)) {
            return ServiceResponse.<Void>builder().success(false).message("The seat type data is invalid.").build();
        }

        if (!newSeatType.getCode().equals(oldSeatType.getCode()) && seatTypeRepository.existsByCode(newSeatType.getCode())) {
            return ServiceResponse.<Void>builder().success(false).message("Seat type with code %s already exists.".formatted(newSeatType.getCode())).build();
        }

        newSeatType.setId(id);
        seatTypeRepository.save(newSeatType);

        return ServiceResponse.<Void>builder().success(true).build();
    }

    private boolean validateSeatType(SeatType elem) {
        var violations = validator.validate(elem);

        return violations.isEmpty();
    }
}
