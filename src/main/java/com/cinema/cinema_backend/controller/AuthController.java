package com.cinema.cinema_backend.controller;

import com.cinema.cinema_backend.dto.*;
import com.cinema.cinema_backend.model.CinemaUser;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
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
        System.out.println(request);
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        CinemaUserDetails userDetails;
        try {
            userDetails = cinemaUserDetailsService.loadUserByUsername(request.email());
        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("Invalid username/email or password");
        }
        CinemaUser cinemaUser = userDetails.getCinemaUser();
        UserDto userDto = new UserDto(
                cinemaUser.getId(),
                cinemaUser.getName(),
                cinemaUser.getEmail(),
                cinemaUser.getRole()
        );


        String token = jwtService.generateToken(userDetails);

        AuthResponse authResponse = new AuthResponse(token, userDto);

        ApiResponse<AuthResponse> response = new ApiResponse<>(authResponse, "Login success", null);

        return ResponseEntity.ok(response);
    }
}
