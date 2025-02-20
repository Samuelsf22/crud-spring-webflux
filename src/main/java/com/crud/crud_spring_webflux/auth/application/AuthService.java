package com.crud.crud_spring_webflux.auth.application;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.crud_spring_webflux.auth.infrastructure.security.JwtProvider;
import com.crud.crud_spring_webflux.exception.domain.CustomException;
import com.crud.crud_spring_webflux.user.domain.Role;
import com.crud.crud_spring_webflux.user.domain.UserRepository;
import com.crud.crud_spring_webflux.user.domain.models.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public Mono<AuthToken> login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> new AuthToken(generateToken(user)))
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Invalid credentials")));
    }

    public Mono<AuthToken> create(User user) {
        return userRepository.existsByUsername(user.getUsername())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "User already exists"));
                    }

                    user.setPublicId(UUID.randomUUID());
                    user.setRoles(Role.USER.name());
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    return userRepository.save(user)
                            .map(savedUser -> new AuthToken(generateToken(savedUser)));
                });
    }

    private String generateToken(User user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles())
                .build();
        return jwtProvider.generateToken(userDetails);
    }

}
