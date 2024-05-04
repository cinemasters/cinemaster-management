package pl.edu.pw.cinemasterbe.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pw.cinemasterbe.model.domain.TicketPerk;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.repositories.TicketPerkRepository;

@Service
@RequiredArgsConstructor
public class TicketPerkService {
    private final TicketPerkRepository ticketPerkRepository;

    public ServiceResponse<Page<TicketPerk>> getTicketPerks(PageRequest pageRequest) {
        var ticketPerks = ticketPerkRepository.findAll(pageRequest);

        return ServiceResponse.<Page<TicketPerk>>builder().success(true).data(ticketPerks).build();
    }

    public ServiceResponse<TicketPerk> getTicketPerkById(int id) {
        var ticketPerk = ticketPerkRepository.findById(id).orElse(null);

        return ServiceResponse.<TicketPerk>builder().success(ticketPerk != null).data(ticketPerk).build();
    }
}
