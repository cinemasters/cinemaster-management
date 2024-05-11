package pl.edu.pw.cinemasterbe.model.seeders;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.edu.pw.cinemasterbe.model.domain.UserEntity;
import pl.edu.pw.cinemasterbe.repositories.UserRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserSeeder {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void seed() {
        if (userRepository.count() > 0) {
            return;
        }

        var users = List.of(UserEntity.builder().name("Jacek").surname("Jaworek").username("jawor").password(passwordEncoder.encode("jawor1")).build(),
                UserEntity.builder().name("Jan").surname("Nowak").username("jnowak").password(passwordEncoder.encode("nowakj")).build(),
                UserEntity.builder().name("Jarosław").surname("Kaczyński").username("kaczor").password(passwordEncoder.encode("smolensk")).build());

        userRepository.saveAll(users);
    }
}
