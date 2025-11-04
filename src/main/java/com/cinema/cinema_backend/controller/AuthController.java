package com.cinema.cinema_backend.controller;

import com.cinema.cinema_backend.dto.AuthResponse;
import com.cinema.cinema_backend.dto.LoginRequest;
import com.cinema.cinema_backend.dto.RegistrationRequest;
import com.cinema.cinema_backend.model.CinemaUser;
import com.cinema.cinema_backend.repository.CinemaUserRepository;
import com.cinema.cinema_backend.security.CinemaUserDetails;
import com.cinema.cinema_backend.security.CinemaUserDetailsService;
import com.cinema.cinema_backend.security.JwtService;
import com.cinema.cinema_backend.service.CinemaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {

    private final CinemaUserService cinemaUserService;
    private final CinemaUserDetailsService cinemaUserDetailsService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(CinemaUserService cinemaUserService, AuthenticationManager authenticationManager, JwtService jwtService, CinemaUserDetailsService cinemaUserDetailsService){
        this.cinemaUserService = cinemaUserService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.cinemaUserDetailsService = cinemaUserDetailsService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> postSignup(@RequestBody RegistrationRequest request) {
        try {
            // register user
            cinemaUserService.register(request);
            return ResponseEntity.ok("User register success");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping(path = "/login")
    public ResponseEntity<?> postLogIn(@RequestBody LoginRequest request){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.usernameOrEmail(), request.password())
        );

        CinemaUserDetails userDetails;
        try {
            userDetails = cinemaUserDetailsService.loadUserByUsername(request.usernameOrEmail());
        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("Invalid username/email or password");
        }


        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
