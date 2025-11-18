package com.cinema.cinema_backend.security;

import com.cinema.cinema_backend.model.CinemaUser;
import com.cinema.cinema_backend.repository.CinemaUserRepository;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@NullMarked
@EnableWebSecurity
public class CinemaUserDetailsService implements UserDetailsService {
    private final CinemaUserRepository cinemaUserRepository;

    @Autowired
    public CinemaUserDetailsService(CinemaUserRepository cinemaUserRepository) {
        this.cinemaUserRepository = cinemaUserRepository;
    }

    @Override
    public CinemaUserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<CinemaUser> user = cinemaUserRepository.findUserByEmail(usernameOrEmail);

        if (user.isEmpty()) {
            user = cinemaUserRepository.findUserByName(usernameOrEmail);
        }
        return user.map(CinemaUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));
    }
}

