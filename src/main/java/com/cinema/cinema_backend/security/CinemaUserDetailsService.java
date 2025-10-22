package com.cinema.cinema_backend.security;

import com.cinema.cinema_backend.model.CinemaUser;
import com.cinema.cinema_backend.repository.CinemaUserRepository;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;


@NullMarked
public class CinemaUserDetailsService implements UserDetailsService {
    private final CinemaUserRepository cinemaUserRepository;

    public CinemaUserDetailsService(CinemaUserRepository cinemaUserRepository) {
        this.cinemaUserRepository = cinemaUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CinemaUser user = cinemaUserRepository
                .findUserByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found by Name"));
        return new CinemaUserDetails(user);
    }

    public void save(CinemaUser user) {
        cinemaUserRepository.save(user);
    }

    public boolean existsByUsername(String name) {
        return cinemaUserRepository.existsByUsername(name);
    }

    public Optional<CinemaUser> findUserByName(String name) {
        return cinemaUserRepository.findUserByName(name);
    }
}
