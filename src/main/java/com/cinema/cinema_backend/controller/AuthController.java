package com.cinema.cinema_backend.controller;

import com.cinema.cinema_backend.dto.RegistrationRequest;
import com.cinema.cinema_backend.repository.CinemaUserRepository;
import com.cinema.cinema_backend.security.CinemaUserDetailsService;
import com.cinema.cinema_backend.service.CinemaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final CinemaUserService cinemaUserService;

    @Autowired
    public AuthController(CinemaUserService cinemaUserService){
        this.cinemaUserService = cinemaUserService;
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
    public String postLogIn(){
        // next step: return JWT token
        return "posting log in info ";
    }
}
