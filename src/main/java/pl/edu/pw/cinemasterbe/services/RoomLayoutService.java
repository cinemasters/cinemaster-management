package pl.edu.pw.cinemasterbe.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pw.cinemasterbe.model.domain.RoomLayout;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.repositories.RoomLayoutRepository;

@Service
@RequiredArgsConstructor
public class RoomLayoutService {
    private final RoomLayoutRepository roomLayoutRepository;

    public ServiceResponse<Page<RoomLayout>> getRoomLayouts(PageRequest pageRequest) {
        var data = roomLayoutRepository.findAll(pageRequest);

        return ServiceResponse.<Page<RoomLayout>>builder().success(true).data(data).build();
    }

    public ServiceResponse<RoomLayout> getRoomLayoutById(int id) {
        var roomLayout = roomLayoutRepository.findById(id).orElse(null);

        return ServiceResponse.<RoomLayout>builder().data(roomLayout).success(roomLayout != null).build();
    }
}
