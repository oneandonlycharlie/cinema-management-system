package com.cinema.cinema_backend.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthProvider implements AuthenticationProvider {
    private final CinemaUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthProvider(CinemaUserDetailsService userDetailsService,
                                        PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String usernameOrEmail = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (usernameOrEmail == null || usernameOrEmail.isBlank()) {
            throw new BadCredentialsException("Username or email cannot be empty");
        }
        if (password == null || password.isBlank()) {
            throw new BadCredentialsException("Password cannot be empty");
        }

        CinemaUserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(usernameOrEmail);
        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("Invalid username/email or password");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username/email or password");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
