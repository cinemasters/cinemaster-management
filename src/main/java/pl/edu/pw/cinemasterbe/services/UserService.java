package pl.edu.pw.cinemasterbe.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.pw.cinemasterbe.model.domain.UserEntity;
import pl.edu.pw.cinemasterbe.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User %s was not found".formatted(username)));
    }

    public UserEntity getUserByUsername(String username) {
        var user = repository.findByUsername(username).orElse(null);

        if (user == null) {
            throw new EntityNotFoundException("User %s was not found".formatted(username));
        }

        return user;
    }
}
