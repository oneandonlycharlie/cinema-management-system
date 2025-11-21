package com.cinema.cinema_backend.service;

import com.cinema.cinema_backend.dto.OrderWithTicketsDto;
import com.cinema.cinema_backend.dto.mapper.OrderWrapper;
import com.cinema.cinema_backend.dto.RegistrationRequest;
import com.cinema.cinema_backend.model.CinemaUser;
import com.cinema.cinema_backend.model.Order;
import com.cinema.cinema_backend.repository.CinemaUserRepository;
import com.cinema.cinema_backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CinemaUserService {
    private final CinemaUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;

    @Autowired
    public CinemaUserService(CinemaUserRepository userRepository, PasswordEncoder passwordEncoder, OrderRepository orderRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.orderRepository = orderRepository;
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

    public Optional<CinemaUser> findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public Optional<CinemaUser> findUserByName(String name){
        return userRepository.findUserByName(name);
    }

    public List<CinemaUser> findAllUsers(){
        return userRepository.findAll();
    }

    public List<OrderWithTicketsDto> getMyOrders(Long userId) {
        List<Order> orders = orderRepository.findByCinemaUserId(userId);

        return orders.stream()
                .map(OrderWrapper::toDto)
                .collect(Collectors.toList());
    }
}
