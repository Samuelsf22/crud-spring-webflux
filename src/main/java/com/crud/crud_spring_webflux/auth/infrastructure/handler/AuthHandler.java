package com.crud.crud_spring_webflux.auth.infrastructure.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.crud.crud_spring_webflux.auth.application.AuthService;
import com.crud.crud_spring_webflux.auth.infrastructure.dto.CreateUserDto;
import com.crud.crud_spring_webflux.auth.infrastructure.dto.LoginDto;
import com.crud.crud_spring_webflux.auth.infrastructure.dto.TokenDto;
import com.crud.crud_spring_webflux.user.domain.models.User;
import com.crud.crud_spring_webflux.validation.ObjectValidator;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthHandler {

    private final AuthService authService;

    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> login(ServerRequest request) {
        return request.bodyToMono(LoginDto.class).doOnNext(objectValidator::validate)
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(authService.login(dto.username(), dto.password()), TokenDto.class));
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(CreateUserDto.class).doOnNext(objectValidator::validate)
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(authService.create(
                                User.builder()
                                        .firstName(dto.firstName())
                                        .lastName(dto.lastName())
                                        .username(dto.username())
                                        .password(dto.password())
                                        .build()),
                                TokenDto.class));
    }

}
