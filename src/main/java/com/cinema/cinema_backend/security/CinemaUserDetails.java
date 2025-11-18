package com.cinema.cinema_backend.security;

import com.cinema.cinema_backend.model.CinemaUser;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NullMarked
public class CinemaUserDetails implements UserDetails {
    private final CinemaUser cinemaUser;

    public CinemaUserDetails(CinemaUser cinemaUser){
        this.cinemaUser = cinemaUser;
    }

    @Override
    public Collection <? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(cinemaUser.getRole()));
    }

    @Override
    public String getPassword() {
        return cinemaUser.getPassword();
    }

    @Override
    public String getUsername() {
        return cinemaUser.getEmail();
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public CinemaUser getCinemaUser() {
        return cinemaUser;
    }
}

