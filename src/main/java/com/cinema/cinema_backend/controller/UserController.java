package com.cinema.cinema_backend.controller;

import com.cinema.cinema_backend.dto.ApiResponse;
import com.cinema.cinema_backend.dto.OrderWithTicketsDto;
import com.cinema.cinema_backend.dto.RegistrationRequest;
import com.cinema.cinema_backend.dto.UserDto;
import com.cinema.cinema_backend.model.CinemaUser;
import com.cinema.cinema_backend.repository.CinemaUserRepository;
import com.cinema.cinema_backend.security.CinemaUserDetails;
import com.cinema.cinema_backend.service.CinemaUserService;
import io.micrometer.core.instrument.distribution.StepBucketHistogram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class UserController {

    private final CinemaUserService userService;

    public UserController(CinemaUserService userService){
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public ResponseEntity<?> getAllUsers(){
        List<CinemaUser> allUsers = userService.findAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping( path = "/users/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal CinemaUserDetails userDetails){
        CinemaUser user = userService.findUserByEmail(userDetails.getUsername())
                .orElseThrow(()-> new NoSuchElementException("Can not find current user under email " + userDetails.getUsername()));
        UserDto userDto = new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
        ApiResponse<UserDto> response = new ApiResponse<>(userDto, "Fetched current user", null);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/me/orders")
    public ResponseEntity
            <ApiResponse<List<OrderWithTicketsDto>>> getMyOrders(@AuthenticationPrincipal CinemaUserDetails userDetails) {
        try {
            Long userId = userDetails.getCinemaUser().getId();
            List<OrderWithTicketsDto> orders = userService.getMyOrders(userId);
            ApiResponse<List<OrderWithTicketsDto>> response = new ApiResponse<List<OrderWithTicketsDto>>(orders, null, null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse<>(null, e.getMessage(), null));
        }
    }
}
