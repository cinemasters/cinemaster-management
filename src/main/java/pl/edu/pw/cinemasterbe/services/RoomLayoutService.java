package pl.edu.pw.cinemasterbe.services;

import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pw.cinemasterbe.model.domain.cinema.LayoutSeat;
import pl.edu.pw.cinemasterbe.model.domain.cinema.RoomLayout;
import pl.edu.pw.cinemasterbe.model.domain.cinema.SeatType;
import pl.edu.pw.cinemasterbe.model.dto.RoomLayoutDetailsDto;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.repositories.RoomLayoutRepository;

import static pl.edu.pw.cinemasterbe.utils.ServiceUtils.buildErrorMessage;

@Service
@RequiredArgsConstructor
public class RoomLayoutService {
    private final RoomLayoutRepository roomLayoutRepository;
    private final SeatTypeService seatTypeService;
    private final Validator validator;

    public ServiceResponse<Page<RoomLayout>> getRoomLayouts(PageRequest pageRequest) {
        var data = roomLayoutRepository.findAll(pageRequest);

        return ServiceResponse.<Page<RoomLayout>>builder().success(true).data(data).build();
    }

    public ServiceResponse<RoomLayout> getRoomLayoutById(int id) {
        var roomLayout = roomLayoutRepository.findById(id).orElse(null);

        return ServiceResponse.<RoomLayout>builder().data(roomLayout).success(roomLayout != null).build();
    }

    public ServiceResponse<Integer> createRoomLayout(RoomLayoutDetailsDto dto) {
        var validationResponse = validateLayoutDto(dto);

        if (!validationResponse.isSuccess()) {
            return validationResponse;
        }

        var roomLayout = RoomLayout.builder()
                .name(dto.getName())
                .columnCount(dto.getColumnCount())
                .rowCount(dto.getRowCount())
                .build();

        int seatCount = 0;

        for (var seatDto : dto.getSeats()) {
            SeatType seatType = null;

            if (seatDto.getSeatTypeId() != null) {
                seatType = seatTypeService.getSeatTypeById(seatDto.getSeatTypeId()).getData();

                if (seatType == null) {
                    return ServiceResponse.<Integer>builder().success(false).message("The seat type id %d does not exist.".formatted(seatDto.getSeatTypeId())).build();
                }
            }

            var seat = LayoutSeat.builder()
                    .type(seatType)
                    .layout(roomLayout)
                    .hidden(seatDto.isHidden())
                    .row(seatDto.getRow())
                    .column(seatDto.getCol())
                    .build();

            if (!seatDto.isHidden()) {
                seatCount++;
            }

            roomLayout.addSeat(seat);
        }

        roomLayout.setSeatCount(seatCount);

        roomLayout = roomLayoutRepository.save(roomLayout);

        return ServiceResponse.<Integer>builder().success(true).data(roomLayout.getId()).build();
    }

    private ServiceResponse<Integer> validateLayoutDto(RoomLayoutDetailsDto dto) {
        var violations = validator.validate(dto);

        if (!violations.isEmpty()) {
            return ServiceResponse.<Integer>builder().success(false).message(buildErrorMessage(violations)).build();
        }

        for (var seatDto : dto.getSeats()) {
            var seatViol = validator.validate(seatDto);

            if (seatDto == null || !violations.isEmpty()) {
                return ServiceResponse.<Integer>builder().success(false).message(buildErrorMessage(seatViol)).build();
            }

            if (seatDto.getCol() > dto.getColumnCount() || seatDto.getRow() > dto.getRowCount()) {
                return ServiceResponse.<Integer>builder().success(false).message("Invalid seat data: row %d col %d".formatted(seatDto.getRow(), seatDto.getCol())).build();
            }
        }

        return ServiceResponse.<Integer>builder().success(true).build();
    }
}
