package com.cinema.cinema_backend.service;

import com.cinema.cinema_backend.dto.RegistrationRequest;
import com.cinema.cinema_backend.model.CinemaUser;
import com.cinema.cinema_backend.repository.CinemaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CinemaUserService {
    private final CinemaUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CinemaUserService(CinemaUserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegistrationRequest request){
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already registered");
        }
        CinemaUser newUser = new CinemaUser(
                null,
                request.name(),
                request.email(),
                passwordEncoder.encode(request.password()),
                "ROLE_USER"
        );
        userRepository.save(newUser);
    }
}
