package pl.edu.pw.cinemasterbe.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.pw.cinemasterbe.model.domain.Client;
import pl.edu.pw.cinemasterbe.model.dto.ClientDetailsDto;
import pl.edu.pw.cinemasterbe.model.mappers.ClientMapper;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.repositories.ClientRepository;

import java.util.Set;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final Validator validator;
    private final PasswordEncoder passwordEncoder;
    private final ClientMapper clientMapper;

    public Page<Client> getClients(PageRequest pageRequest) {
        return clientRepository.findAll(pageRequest);
    }

    public Client getClientById(int id) {
        return clientRepository.findById(id).orElse(null);
    }

    public ServiceResponse<Integer> updateClient(ClientDetailsDto dto, int id) {
        var oldClient = clientRepository.findById(id).orElse(null);

        if (oldClient == null) {
            return ServiceResponse.<Integer>builder().success(false).message("Client with id %d does not exist.".formatted(id)).build();
        }

        var response = buildClientFromDto(dto, oldClient.getPassword());

        if (!response.isSuccess()) {
            return ServiceResponse.<Integer>builder().success(false).message(response.getMessage()).build();
        }

        var newClient = response.getData();
        newClient.setId(oldClient.getId());

        newClient = clientRepository.save(newClient);

        return ServiceResponse.<Integer>builder().success(true).data(newClient.getId()).build();
    }

    private ServiceResponse<Client> buildClientFromDto(ClientDetailsDto dto, String oldPassword) {
        var client = clientMapper.mapToEntity(dto);

        if (!isNullOrEmpty(dto.getPassword())) {
            if (!arePasswordsValid(dto.getPassword(), dto.getConfirmPassword())) {
                return ServiceResponse.<Client>builder().success(false).message("The provided passwords are invalid.").build();
            }

            client.setPassword(passwordEncoder.encode(dto.getPassword()));
        } else {
            client.setPassword(oldPassword);
        }

        var violations = validator.validate(client);

        if (!violations.isEmpty()) {
            return ServiceResponse.<Client>builder().success(false).message(buildErrorMessage(violations)).build();
        }

        // TODO: E-mail should be unique

        return ServiceResponse.<Client>builder().success(true).data(client).build();
    }

    private boolean arePasswordsValid(String password, String confirmPassword) {
        if (password == null || !password.equals(confirmPassword)) {
            return false;
        }

        return Pattern.compile("[A-Za-z0-9!@#$%]{4,16}").matcher(password).matches();
    }

    private <T> String buildErrorMessage(Set<ConstraintViolation<T>> violations) {
        var builder = new StringBuilder();

        for (var v : violations) {
            builder.append(v.getMessage()).append(" ");
        }

        return builder.toString().strip();
    }

    private boolean isNullOrEmpty(String elem) {
        return elem == null || elem.isEmpty();
    }
}
