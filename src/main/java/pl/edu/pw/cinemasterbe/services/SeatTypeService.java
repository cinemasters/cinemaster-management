package pl.edu.pw.cinemasterbe.services;

import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.edu.pw.cinemasterbe.model.domain.SeatType;
import pl.edu.pw.cinemasterbe.repositories.SeatTypeRepository;

@Service
@RequiredArgsConstructor
public class SeatTypeService {
    private final SeatTypeRepository seatTypeRepository;
    private final Validator validator;

    public Page<SeatType> getSeatTypes(Pageable pageRequest) {
        return seatTypeRepository.findAll(pageRequest);
    }

    public SeatType getSeatTypeById(int id) {
        return seatTypeRepository.findById(id).orElse(null);
    }

    public void createSeatType(SeatType newSeatType) {
        if (!validateSeatType(newSeatType) || seatTypeRepository.existsByCode(newSeatType.getCode())) {
            throw new IllegalArgumentException("Invalid seat type data.");
        }

        newSeatType.setId(-1);
        seatTypeRepository.save(newSeatType);
    }

    public void updateSeatType(SeatType newSeatType, int id) {
        var oldSeatType = seatTypeRepository.findById(id).orElse(null);

        if (oldSeatType == null || !validateSeatType(newSeatType)) {
            throw new IllegalArgumentException("Invalid seat type data.");
        }

        if (newSeatType.getCode().equals(oldSeatType.getCode()) && seatTypeRepository.existsByCode(newSeatType.getCode())) {
            throw new IllegalArgumentException("Seat type with this code already exists.");
        }

        newSeatType.setId(id);
        seatTypeRepository.save(newSeatType);
    }

    private boolean validateSeatType(SeatType elem) {
        var violations = validator.validate(elem);

        if (!violations.isEmpty()) {
            for (var v : violations) {
                System.out.println(v.getMessage());
            }
        }

        return violations.isEmpty();
    }
}
