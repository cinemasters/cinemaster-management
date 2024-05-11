package pl.edu.pw.cinemasterbe.model.seeders;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.edu.pw.cinemasterbe.model.domain.Client;
import pl.edu.pw.cinemasterbe.repositories.ClientRepository;

@Component
@RequiredArgsConstructor
public class ClientSeeder {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public void seed() {
        if (clientRepository.count() > 0) {
            return;
        }

        var faker = new Faker();

        for (int i = 0; i < 50; i++) {
            var client = Client.builder()
                    .name(faker.name().firstName())
                    .surname(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .password(passwordEncoder.encode(faker.internet().password()))
                    .phoneNumber("603123532")
                    .offerSubscribed(faker.bool().bool())
                    .locked(faker.bool().bool())
                    .build();

            clientRepository.save(client);
        }
    }
}
