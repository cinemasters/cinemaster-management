package pl.edu.pw.cinemasterbe.services;

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

    public Page<SeatType> getSeatTypes(Pageable pageRequest) {
        return seatTypeRepository.findAll(pageRequest);
    }
}
